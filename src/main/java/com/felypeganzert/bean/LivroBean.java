package com.felypeganzert.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.felypeganzert.model.Livro;
import com.felypeganzert.service.LivroCadastroServico;
import com.felypeganzert.service.LivroException;

@Named("livroBean")
@RequestScoped
public class LivroBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroCadastroServico cadastroServico;	
	
	private Livro livro = new Livro();
	
	public void save() {
		System.out.println(this.livro);
		System.out.println(this.livro.getTitulo());
		System.out.println(this.cadastroServico);
		try {
			this.cadastroServico.save(this.livro);
		} catch(LivroException e) {
			System.out.println(e);
		}
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

}
