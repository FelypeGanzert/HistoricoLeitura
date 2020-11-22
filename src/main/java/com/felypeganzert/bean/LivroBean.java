package com.felypeganzert.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.felypeganzert.model.Livro;
import com.felypeganzert.repository.Livros;
import com.felypeganzert.service.LivroCadastroServico;
import com.felypeganzert.service.LivroException;

@Named("livroBean")
@RequestScoped
public class LivroBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroCadastroServico cadastroServico;	
	@Inject
	private Livros livros;
	private Livro livro = new Livro();
	
	private HtmlCommandButton botaoSalvar;
	
	public void save() {
		botaoSalvar.setDisabled(true);
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			System.out.println(this.livro.getId());
			this.cadastroServico.save(this.livro);
			context.addMessage(null, new FacesMessage("Livro salvo com sucesso!", livro.getTitulo() + " salvo!"));
			this.livro = new Livro();
		} catch (LivroException e) {
			System.out.println(e.getMessage());
			FacesMessage mensagem = new FacesMessage(e.getMessage(), "");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} finally {
			botaoSalvar.setDisabled(false);
		}
	}
	
	public void limpar() {
		this.livro = new Livro();
	}
	
	public List<String> getAutores(String text) {
		return livros.findAllDistinctAuthorNameLike(text + "%");
		
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public void test() {
		System.out.println("ola");
	}

	public HtmlCommandButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(HtmlCommandButton botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	
	
}
