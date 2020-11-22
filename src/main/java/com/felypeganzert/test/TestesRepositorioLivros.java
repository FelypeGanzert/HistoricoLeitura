package com.felypeganzert.test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.felypeganzert.db.EntityManagerProducer;
import com.felypeganzert.model.Livro;
import com.felypeganzert.repository.Livros;

public class TestesRepositorioLivros {

	public static void main(String[] args) {
		EntityManager manager = (new EntityManagerProducer()).createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		Livros livros = new Livros(manager);

		// Eu fui comentando o que foi concluído para não ser executado
		// mais de uma vez
		
		// ===== INSERT (Inserção)
		  insert(livros, tx);

		// ===== FIND (Procura)
		// findById()
		// findAll(livros);
		
		// ==== UPDATE (Atualiza)
		// update(livros, tx);
		
		// ==== DELETE (Exclusão)
		// deleteByBook(livros, tx);
		//deleteById(livros, tx);

		// FIND BY AUTHOR NAME (Método adicional para encontrar todos os livros de um autor)
		//findFromAuthor(livros);
		//findAuthors(livros);
		
	}

	public static void insert(Livros livros, EntityTransaction tx) {
		tx.begin();
		Livro l1 = new Livro("Gatos Guerreiros - Na Floresta", "Erin Hunter");
		Livro l2 = new Livro("Segunda Fundação", "Isaac Asimov");
		Livro l3 = new Livro(null, "1984", "George Orwell", new Date(), null, 300, 140, null);
		livros.insert(l1);
		livros.insert(l2);
		livros.insert(l3);
		tx.commit();
	}
	
	public static void findById(Livros livros) {
		Livro livroById = livros.findById(1);
		printLivro(livroById);
	}
	
	public static void findAll(Livros livros) {
		List<Livro> list = livros.findAll();
		list.forEach(l -> printLivro(l));
	}
	
	public static void update(Livros livros, EntityTransaction tx) {
		Livro l = livros.findById(1);
		System.out.print("Antes: ");
		printLivro(l);
		l.setTitulo("Fundação, primeiro Livro");
		tx.begin();
		livros.update(l);
		tx.commit();
		System.out.print("Depois: ");
		printLivro(l);
	}
	
	public static void deleteByBook(Livros livros, EntityTransaction tx) {
		Livro l = livros.findById(1);
		printLivro(l);
		tx.begin();
		livros.delete(l);
		tx.commit();
	}
	
	public static void deleteById(Livros livros, EntityTransaction tx) {
		tx.begin();
		livros.deleteById(2);
		tx.commit();
	}
	
	public static void findFromAuthor(Livros livros) {
		List<Livro> list = livros.findAllFromAuthorNameLike("John%");
		list.forEach(l -> printLivro(l));
	}
	
	public static void findAuthors(Livros livros) {
		List<String> list = livros.findAllDistinctAuthorNameLike("John%");
		list.forEach(a -> System.out.println("[ " + a + " ]"));
	}

	public static void printLivro(Livro l) {
		System.out.println("[ " + l.getId() + ", " + l.getTitulo() + ", " + l.getAutor() + " ]");
	}

}
