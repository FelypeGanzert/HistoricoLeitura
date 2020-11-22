package com.felypeganzert.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.felypeganzert.model.Livro;

public class Livros implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public Livros(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Livro findById(Integer id) {
		return entityManager.find(Livro.class, id);
	}

	public List<Livro> findAll() {
		return entityManager.createQuery("FROM Livro", Livro.class).getResultList();
	}
	
	public List<Livro> findAllFromAuthorNameLike(String author){
		return entityManager.createQuery("FROM Livro WHERE autor LIKE'" + author + "'", Livro.class).getResultList();
	}
	
	public List<String> findAllDistinctAuthorNameLike(String author){
		TypedQuery<String> query = entityManager.createQuery("SELECT DISTINCT autor FROM Livro WHERE autor LIKE '" + author + "'", String.class); 
		return query.getResultList();
	}

	public void insert(Livro livro) {
		entityManager.persist(livro);
	}

	public void update(Livro livro) {
		livro = entityManager.merge(livro);
	}

	public void delete(Livro livro) {
		livro = entityManager.find(Livro.class, livro.getId());
		entityManager.remove(livro);
	}

	public void deleteById(Integer id) {
		Livro livro = entityManager.find(Livro.class, id);
		entityManager.remove(livro);
	}

}