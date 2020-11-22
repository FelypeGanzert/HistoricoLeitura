package repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

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
		return entityManager.createQuery("from Livro", Livro.class).getResultList();
	}
	
	public List<Livro> AllFromAuthor(String author){
		return entityManager.createQuery("from Livro where autor = " + author, Livro.class).getResultList();
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
