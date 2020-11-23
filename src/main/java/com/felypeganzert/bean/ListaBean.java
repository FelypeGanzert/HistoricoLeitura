package com.felypeganzert.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.felypeganzert.model.Livro;
import com.felypeganzert.repository.Livros;

@Named("listaBean")
@ViewScoped
public class ListaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Livro> livrosCadastrados;

	@Inject
	private Livros livros;

	private Livro livroSelecionado;

	@PostConstruct
	public void init() {
		this.list();
	}
	
	public void list() {
		livrosCadastrados = livros.findAll();
	}

	public List<Livro> getLivrosCadastrados() {
		return livrosCadastrados;
	}

	public void setLivrosCadastrados(List<Livro> livrosCadastrados) {
		this.livrosCadastrados = livrosCadastrados;
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

}
