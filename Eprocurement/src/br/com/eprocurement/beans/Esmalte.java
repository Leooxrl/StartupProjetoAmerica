package br.com.eprocurement.beans;

public class Esmalte {

	private int codigo_esm = 0;
	private String cor = "";
	private String marca = "";
	private double valor = 0;
	
	public Esmalte(){
		this.cor = "sem cor";
		this.marca = "sem marca";
		this.valor = 0;
	}
	
	public Esmalte(String cor, String marca, double valor){
		this.cor = cor;
		this.marca = marca;
		this.valor = valor;
	}

	public int getCodigo_esm() {
		return codigo_esm;
	}

	public void setCodigo_esm(int codigo_esm) {
		this.codigo_esm = codigo_esm;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
