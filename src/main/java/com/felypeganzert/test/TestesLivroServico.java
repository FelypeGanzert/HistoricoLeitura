package com.felypeganzert.test;

import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.felypeganzert.db.EntityManagerProducer;
import com.felypeganzert.model.Livro;
import com.felypeganzert.repository.Livros;
import com.felypeganzert.service.LivroServico;

public class TestesLivroServico {

	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	// Os testes (métodos) estão comentados para não serem executados mais de uma vez
	
	public static void main(String[] args) {
		EntityManager manager = EntityManagerProducer.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		Livros livros = new Livros(manager);
		LivroServico livroServico = new LivroServico(livros);
		
		// ===== Testes feitos com a intenção de gerar todas as exceções do método save
		
		// 1- Verifica se o título do livro está vazio
		//saveException1(livroServico, tx);
		
		// 2- Verifica se foi colocado o número de páginas lidas, mas não o total
		// saveException2(livroServico, tx);
		
		// 3- Verifica se a quantidade de páginas lidas é maior que o número total de páginas
		// saveException3(livroServico, tx);
		
		// 4- Verifica se a data de início está no futuro
		// saveException4(livroServico, tx);
		
		// 5- Verifica se a data de término está no futuro
		// saveException5(livroServico, tx);
		
		// 6- Veriifica se a data de término é anterior a data de início
		// saveException6(livroServico, tx);
		
		// 7- ADICIONAL: datas iguais a null (não informadas), é uma coisa que deve ser permitida
		saveException7(livroServico, tx);
		
	}
	
	public static void saveException1(LivroServico livroServico, EntityTransaction tx) {
		System.out.println("#1"); // titulo é null
		try {
			tx.begin();
			Livro l = new Livro();
			l.setAutor("Autor");
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
		System.out.println("#2"); // título está vazio
		try {
			tx.begin();
			Livro l = new Livro();
			l.setAutor("Autor");
			l.setTitulo("");
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
	}
	
	public static void saveException2(LivroServico livroServico, EntityTransaction tx) {
		System.out.println("#1"); // so foi inserido o número de páginas lidas
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(100);
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
	}
	
	public static void saveException3(LivroServico livroServico, EntityTransaction tx) {
		System.out.println("#1");
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(100);
			l.setPaginas(50);
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
	}
	
	public static void saveException4(LivroServico livroServico, EntityTransaction tx) {
		System.out.println("#1"); // Hoje (23/11/2020) - não deu erro (ok)
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(70);
			l.setPaginas(100);
			l.setDataInicio(sdf.parse("22/11/2020"));
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
		System.out.println("#2"); // Amanhã (24/11/2020) - aqui já foi lançado uma exception (ok)
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(70);
			l.setPaginas(100);
			l.setDataInicio(sdf.parse("23/11/2020"));
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
	}
	
	public static void saveException5(LivroServico livroServico, EntityTransaction tx) {
		System.out.println("#1"); // Hoje (23/11/2020) - não deu erro (ok)
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(70);
			l.setPaginas(100);
			l.setDataInicio(sdf.parse("01/01/2020"));
			l.setDataTermino(sdf.parse("22/11/2020"));
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
		System.out.println("#2"); // Amanhã (24/11/2020) - aqui já foi lançado uma exception (ok)
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(70);
			l.setPaginas(100);
			l.setDataInicio(sdf.parse("01/01/2020"));
			l.setDataTermino(sdf.parse("23/11/2020"));
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
	}
	
	public static void saveException6(LivroServico livroServico, EntityTransaction tx) {
		System.out.println("#1"); // Mesma data de inicio e termino - não deu erro (ok)
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(70);
			l.setPaginas(100);
			l.setDataInicio(sdf.parse("22/11/2020"));
			l.setDataTermino(sdf.parse("22/11/2020"));
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
		System.out.println("#2"); // data de inicio superior a de termino - aqui já foi lançado uma exception (ok)
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(70);
			l.setPaginas(100);
			l.setDataInicio(sdf.parse("21/11/2020"));
			l.setDataTermino(sdf.parse("01/11/2020"));
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
	}
	
	public static void saveException7(LivroServico livroServico, EntityTransaction tx) {
		// sem data de inicio, mas com data de término (não deve gerar excessão)
		System.out.println("#1");
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(70);
			l.setPaginas(100);
			l.setDataInicio(null);
			l.setDataTermino(sdf.parse("22/11/2020"));
			livroServico.save(l);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(tx != null & tx.isActive()) {
				tx.commit();
			}
		}
		// sem data de termino, mas com data de inicio (não deve gerar excessão)
		System.out.println("#2");
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(70);
			l.setPaginas(100);
			l.setDataInicio(sdf.parse("22/11/2020"));
			l.setDataTermino(null);
			livroServico.save(l);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tx != null & tx.isActive()) {
				tx.commit();
			}
		}
		// sem nenhuma data (não deve gerar excessão)
		System.out.println("#3");
		try {
			tx.begin();
			Livro l = new Livro();
			l.setTitulo("Titulo");
			l.setAutor("Autor");
			l.setPaginasLidas(70);
			l.setPaginas(100);
			l.setDataInicio(null);
			l.setDataTermino(null);
			livroServico.save(l);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tx != null & tx.isActive()) {
				tx.commit();
			}
		}
	}

}
