package com.exterro.grocerymgt.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.exterro.grocerymgt.model.CartItem;
import com.exterro.grocerymgt.model.Purchase;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

/*
Author name : vimalraj.vijayakumar
Creation Date : 29-Jun-2024
*/

@Repository
public class PurchaseCustomRepoImpl implements PurchaseCustomRepo {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Purchase> bestSellingProducts() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Purchase> cq = cb.createQuery(Purchase.class);
        Root<Purchase> root = cq.from(Purchase.class);
        Join<Purchase, CartItem> purchaseJoin = root.join("itemList", JoinType.LEFT);
        
        cq.multiselect(purchaseJoin, cb.count(purchaseJoin)).groupBy(purchaseJoin);
        
		return em.createQuery(cq).getResultList();
	}
}
