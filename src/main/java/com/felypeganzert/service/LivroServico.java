package com.felypeganzert.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.felypeganzert.model.Livro;
import com.felypeganzert.repository.Livros;

public class LivroServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Livros livros;
	
	public LivroServico(Livros livros) {
		this.livros = livros;
	}
	
	public void save(Livro livro) throws LivroException {
		if(livro == null) {
			throw new LivroException("Livro está nulo");
		}
		// Verifica se o título do livro está vazio
		if(livro.getTitulo() == null || livro.getTitulo().isEmpty()) {
			throw new LivroException("Título não pode estar vazio");
		}
		// Verifica se foi colocado o número de páginas lidas, mas não o total
		if(livro.getPaginas() == null && livro.getPaginasLidas() != null) {
			throw new LivroException("O total de páginas é necessário para ser possível inserir as páginas lidas");
		}
		// Verifica se a quantidade de páginas lidas é maior que o número total de páginas
		if(livro.getPaginasLidas() > livro.getPaginas()) {
			throw new LivroException("A quantidade de páginas lidas não pode ser maior que a quantidade de páginas totais");
		}
		// Verifica se a data de início está no futuro
		if(livro.getDataInicio() != null && livro.getDataInicio().after(new Date())) {
			throw new LivroException("Data de ínicio precisa ser hoje ou em alguma data passada.");
		}
		// Verifica se a data de término está no futuro
		if(livro.getDataTermino() != null && livro.getDataTermino().after(new Date())) {
			throw new LivroException("Data de término precisa ser hoje ou em alguma data passada.");
		}
		// Veriifica se a data de término é anterior a data de início
		if(livro.getDataTermino() != null && livro.getDataInicio() != null && livro.getDataTermino().before(livro.getDataInicio())) {
			throw new LivroException("Data de início precisa ser anterior à data de término.");
		}
		// Checa se o Livro já estava no banco de dados
		if(livro.getId() != null) {
			livros.update(livro);
		} else {
			livros.insert(livro);
		}
	}
	

	public Livro findById(Integer id) {
		return livros.findById(id);
	}

	public List<Livro> findAll() {
		return livros.findAll();
	}
	
	public List<Livro> findAllFromAuthorNameLike(String author){
		return livros.findAllFromAuthorNameLike(author);
	}
	
	public List<String> findAllDistinctAuthorNameLike(String author){
		return livros.findAllDistinctAuthorNameLike(author);
	}
	

	public void delete(Livro livro) {
		livros.delete(livro);
	}

	public void deleteById(Integer id) {
		livros.deleteById(id);
	}
	


}
