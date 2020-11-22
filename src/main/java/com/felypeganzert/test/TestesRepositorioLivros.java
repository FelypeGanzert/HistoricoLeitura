package com.felypeganzert.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.felypeganzert.db.EntityManagerProducer;
import com.felypeganzert.model.Livro;

import repository.Livros;

public class TestesRepositorioLivros {

	public static void main(String[] args) {
		EntityManager manager = EntityManagerProducer.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		Livros livros = new Livros(manager);

		// INSERT (Inserção)
		tx.begin();
		insert(livros);
		tx.commit();;

	}

	public static void insert(Livros livros) {
		Livro l1 = new Livro("Fundação", "Isaac Asimov");
		Livro l2 = new Livro(null, "A culpa é das Estrelas", "John Green", new Date(), null, 300, 140, null);
		livros.insert(l1);
		livros.insert(l2);
	}

}
