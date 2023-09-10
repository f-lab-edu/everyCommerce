package com.every.commerce;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaTest {
	@PersistenceContext
	private EntityManager entityManager;
}
