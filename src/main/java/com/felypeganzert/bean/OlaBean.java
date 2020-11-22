package com.felypeganzert.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class OlaBean {

	private String text;
	
	public void dizerOla() {
		System.out.println("Olá");
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
