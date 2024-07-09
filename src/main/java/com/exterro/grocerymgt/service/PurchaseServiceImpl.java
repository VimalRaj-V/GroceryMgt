package com.exterro.grocerymgt.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exterro.grocerymgt.dto.CartItemDto;
import com.exterro.grocerymgt.dto.MessageDto;
import com.exterro.grocerymgt.dto.ProductPerformaceDto;
import com.exterro.grocerymgt.model.Cart;
import com.exterro.grocerymgt.model.CartItem;
import com.exterro.grocerymgt.model.Customer;
import com.exterro.grocerymgt.model.GroceryItem;
import com.exterro.grocerymgt.model.Purchase;
import com.exterro.grocerymgt.repository.CartRepo;
import com.exterro.grocerymgt.repository.CustomerRepo;
import com.exterro.grocerymgt.repository.GroceryRepo;
import com.exterro.grocerymgt.repository.PurchaseRepo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/*
Author name : vimalraj.vijayakumar
Creation Date : 26-June-2024
*/
@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private PurchaseRepo purchaseRepo;

	@Autowired
	private GroceryRepo groceryRepo;

	@Autowired
	private EmailSenderService emailSenderService;

	@Override
	public MessageDto purchase(String customerUserName) {
		try {
			Customer customer = customerRepo.findByCustomerUserName(customerUserName).get();
			Cart cart = cartRepo.findByCustomer(customer).get();
			List<CartItem> list = new ArrayList<CartItem>();
			list.addAll(cart.getItemList());
			generateInvoice(list);
			Purchase purchase = new Purchase(0, customer.getCustomerId(), list);
			cart.getItemList().clear();
			purchaseRepo.save(purchase);
			emailSenderService.sendEmail(customer.getCustomerMailId(), "Online Grocery Shopping - Reg",
					buildMessage(customer.getCustomerName()));
			return new MessageDto("Purchase Successful");
		} catch (Exception e) {
			return new MessageDto("Purchase Unsuccessful " + e.getMessage());
		}

	}

	@Override
	public List<CartItemDto> viewCart(String customerUserName) {
		Customer customer = customerRepo.findByCustomerUserName(customerUserName).get();
		Cart customerCart = cartRepo.findByCustomer(customer).get();
		List<CartItem> cartItems = customerCart.getItemList();
		List<CartItemDto> cartItemsDto = new ArrayList<CartItemDto>();
		for (CartItem cartItem : cartItems) {
			GroceryItem groceryItem = groceryRepo.findByItemId(cartItem.getItemId()).get();
			CartItemDto cartItemDto = new CartItemDto(cartItem.getItemId(), groceryItem.getItemName(),
					groceryItem.getItemPrice(), groceryItem.getItemBrand(), cartItem.getQuantity(),
					cartItem.getTotalPrice());
			cartItemsDto.add(cartItemDto);
		}
		return cartItemsDto;
	}

	public String buildMessage(String userName) {
		return "Dear " + userName + ",\r\n\r\n"
				+ "Thank you for your recent purchase in our Online Shopping Portal. You have successfully made the purchase and your products will be delivered in the next two days. \r\n"
				+ "\r\nThanks, and we hope to serve you again soon.\r\n\r\nAdmin,\r\n" + "Online Shopping Portal.";
	}

	@Override
	public ArrayList<ProductPerformaceDto> checkHighPurchased() {
		List<Purchase> purchaseList = purchaseRepo.findAll();
		Map<String, Integer> pCount = new HashMap<String, Integer>();
		ArrayList<ProductPerformaceDto> productPerformaceDto = new ArrayList<ProductPerformaceDto>();

		for (Purchase p : purchaseList) {
			for (CartItem cartItem : p.getItemList()) {
				String key = cartItem.getItemId();
				int val = cartItem.getQuantity();
				if (pCount.containsKey(key)) {
					pCount.put(key, pCount.get(key) + val);
				} else {
					pCount.put(key, val);
				}
			}
		}
		for (Map.Entry<String, Integer> entry : pCount.entrySet()) {
			Optional<GroceryItem> opGroceryItem = groceryRepo.findByItemId(entry.getKey());
			if (opGroceryItem.isPresent()) {
				GroceryItem groceryitem = opGroceryItem.get();
				ProductPerformaceDto product = new ProductPerformaceDto(groceryitem, entry.getValue());
				productPerformaceDto.add(product);
			}

		}

		Collections.sort(productPerformaceDto, new Comparator<ProductPerformaceDto>() {
			public int compare(ProductPerformaceDto o1, ProductPerformaceDto o2) {
				return o2.getQuantitySold() - o1.getQuantitySold();
			}
		});
		return productPerformaceDto;
	}

	public void generateInvoice(List<CartItem> list) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("Invoice.pdf"));
			document.open();
			
			PdfPTable table = new PdfPTable(5);
			Stream.of("Sl No.", "Item Name", "Price", "Quantity", "Cost").forEach(columnTitle -> {
				PdfPCell header = new PdfPCell();
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(columnTitle));
				table.addCell(header);
			});
			int i=1;
			float totalCost = 0;
			for (CartItem cartItem: list) {
				GroceryItem groceryItem = groceryRepo.findByItemId(cartItem.getItemId()).get();
				System.out.println(cartItem.getItemId());
				table.addCell(Integer.toString(i));
				table.addCell(groceryItem.getItemName());
				table.addCell(Float.toString(groceryItem.getItemPrice()));
				table.addCell(Integer.toString(cartItem.getQuantity()));
				float cost = cartItem.getQuantity() * groceryItem.getItemPrice();
				totalCost += cost;
				table.addCell(Float.toString(cost));
				i+=1;
			}
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("Total");
			table.addCell(Float.toString(totalCost));

			document.add(table);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		document.close();
	}

}
