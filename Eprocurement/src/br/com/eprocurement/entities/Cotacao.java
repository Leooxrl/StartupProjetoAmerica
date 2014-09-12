package br.com.eprocurement.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cotacao {

	private Long id;
	private Date dataTermino;
	private boolean encerrada;
	private boolean comprada;
	private Mercado mercado;
	private List<CotacaoItem> itens = new ArrayList<CotacaoItem>();

	public Cotacao() {

		this.id = 0L;
		this.dataTermino = new Date();
		this.encerrada = false;
		this.comprada = false;
		this.mercado = new Mercado();
		this.itens = new ArrayList<CotacaoItem>();
	}

	public Cotacao(Long id, Date dataTermino, boolean encerrada,
			boolean comprada, Mercado mercado, List<CotacaoItem> itens) {
		super();
		this.id = id;
		this.dataTermino = dataTermino;
		this.encerrada = encerrada;
		this.comprada = comprada;
		this.mercado = mercado;
		this.itens = itens;
	}

	public Cotacao(Long id, Date dataTermino) {
		super();
		this.id = id;
		this.dataTermino = dataTermino;
		this.encerrada = false;
		this.comprada = false;
		this.mercado = new Mercado();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (comprada ? 1231 : 1237);
		result = prime * result
				+ ((dataTermino == null) ? 0 : dataTermino.hashCode());
		result = prime * result + (encerrada ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result + ((mercado == null) ? 0 : mercado.hashCode());
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
		Cotacao other = (Cotacao) obj;
		if (comprada != other.comprada)
			return false;
		if (dataTermino == null) {
			if (other.dataTermino != null)
				return false;
		} else if (!dataTermino.equals(other.dataTermino))
			return false;
		if (encerrada != other.encerrada)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		if (mercado == null) {
			if (other.mercado != null)
				return false;
		} else if (!mercado.equals(other.mercado))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public boolean isEncerrada() {
		return encerrada;
	}

	public void setEncerrada(boolean encerrada) {
		this.encerrada = encerrada;
	}

	public boolean isComprada() {
		return comprada;
	}

	public void setComprada(boolean comprada) {
		this.comprada = comprada;
	}

	public Mercado getMercado() {
		return mercado;
	}

	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}

	public List<CotacaoItem> getItens() {
		return itens;
	}

	public void setItens(List<CotacaoItem> itens) {
		this.itens = itens;
	}

}
