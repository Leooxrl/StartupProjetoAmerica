package br.com.eprocurement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fornecedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fornecedor_id")
	private Long id;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	@Column(name = "razao_social")
	private String razaoSocial;
	private String cnpj;

	public Fornecedor() {
		setCnpj("");
		setId(0L);
		setNomeFantasia("");
		setRazaoSocial("");
	}

	public Fornecedor(String nomeFantasia, String razaoSocial, String cnpj) {
		super();
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}

	public Fornecedor(Long id, String nomeFantasia, String razaoSocial,
			String cnpj) {
		super();
		this.id = id;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}

	public Fornecedor(Long id, String nomeFantasia) {
		super();
		this.id = id;
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
