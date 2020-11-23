package com.felypeganzert.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "livro")
public class Livro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(nullable = false)
	private String titulo;
	
	@Size(max = 50)
	@Column(length = 60)
	private String autor;
	
	@Temporal(TemporalType.DATE)
	@Column(columnDefinition = "DATETIME default null")
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(columnDefinition = "DATETIME default null")
	private Date dataTermino;
	private Integer paginas;
	private Integer paginasLidas;
	private String urlCapa;
	
	public Livro() {
	}
	
	public Livro(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
	}

	public Livro(Integer id, String titulo, String autor, Date dataInicio, Date dataTermino,
			Integer paginas, Integer paginasLidas, String urlCapa) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.paginas = paginas;
		this.paginasLidas = paginasLidas;
		this.urlCapa = urlCapa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Integer getPaginasLidas() {
		return paginasLidas;
	}

	public void setPaginasLidas(Integer paginasLidas) {
		this.paginasLidas = paginasLidas;
	}

	public String getUrlCapa() {
		return urlCapa;
	}

	public void setUrlCapa(String urlCapa) {
		this.urlCapa = urlCapa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String getProgresso() {
		String txt = "";
		if(paginasLidas != null) {
			txt += paginasLidas;
		} else {
			txt += "-";
		}
		txt += " / ";
		if(paginas != null) {
			txt += paginas;
		} else {
			txt += "-";
		}
		return txt;
		
	}
	
}