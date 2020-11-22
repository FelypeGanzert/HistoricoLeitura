package com.felypeganzert.test;

import com.felypeganzert.db.EntityManagerProducer;

public class CriaTabelas {

	public static void main(String[] args) {
		(new EntityManagerProducer()).createEntityManager();
	}

}
