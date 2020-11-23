package com.felypeganzert.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.felypeganzert.model.Livro;
import com.felypeganzert.repository.Livros;

@FacesConverter(forClass = Livro.class)
public class LivroConverter implements Converter{

	@Inject
	private Livros livros;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Livro retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.livros.findById(Integer.valueOf(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Livro livro = ((Livro) value); 
			return livro.getId() == null ? null : livro.getId().toString();
		}
		return null;
	}
	
}
