package br.com.eprocurement.beans;

public class MovimentoEstoque {

	private int codigo = 0;
	private String observacao = "";
	private double quantidade = 0;
	private Esmalte esmalte;
	private double total = 0;

	public MovimentoEstoque(String observacao, double quantidade,
			Esmalte esmalte) {

		this.observacao = observacao;
		this.quantidade = quantidade;
		this.esmalte = esmalte;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public Esmalte getEsmalte() {
		return esmalte;
	}

	public void setEsmalte(Esmalte esmalte) {
		this.esmalte = esmalte;
	}

}
