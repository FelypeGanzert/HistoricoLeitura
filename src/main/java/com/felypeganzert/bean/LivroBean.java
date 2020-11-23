package com.felypeganzert.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.felypeganzert.model.Livro;
import com.felypeganzert.repository.Livros;
import com.felypeganzert.service.LivroCadastroServico;
import com.felypeganzert.service.LivroException;

@Named("livroBean")
@ViewScoped
public class LivroBean implements Serializable {
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
			boolean isNewBook = this.livro.getId() == null;
			this.cadastroServico.save(this.livro);
			if(!isNewBook) {
				context.addMessage(null, new FacesMessage("Livro - " + livro.getTitulo() + " - ATUALIZADO com sucesso!", ""));
			} else {
				context.addMessage(null, new FacesMessage("Livro - " + livro.getTitulo() + " - SALVO com sucesso!", ""));
			}
			this.limpar();
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

	public void prepararCadastro() {
		if (this.livro == null) {
			this.livro = new Livro();
		}
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.livros.delete(this.livro);
			this.limpar();
			context.addMessage(null,
					new FacesMessage("Livro excluído com sucesso!", ""));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
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

	public HtmlCommandButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(HtmlCommandButton botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}	
	
}
