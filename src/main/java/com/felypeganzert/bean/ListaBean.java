package com.felypeganzert.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.felypeganzert.model.Livro;
import com.felypeganzert.repository.Livros;

@Named("listaBean")
@RequestScoped
public class ListaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Livro> livrosCadastrados;

	@Inject
	private Livros livros;

	private Livro livroSelecionado;

	@PostConstruct
	public void init() {
		livrosCadastrados = livros.findAll();
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.livros.delete(this.livroSelecionado);
			this.getLivrosCadastrados();
			context.addMessage(null, new FacesMessage("Livro excluído com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}

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
