package com.felypeganzert.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {
	
	private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory("Leituras");
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
