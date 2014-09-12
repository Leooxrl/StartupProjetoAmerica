package br.com.eprocurement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mercado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mercado_id")
	private Long id;

	private String cnpj;
	private String nomeFantasia;
	private String endereco;
	@Column(name = "razao_social")
	private String razaoSocial;
	private String telefone;

	public Mercado() {
		setId(1L);
		setCnpj("");
		setEndereco("");
		setRazaoSocial("");
		setNomeFantasia("");
		setTelefone("");
	}

	public Mercado(String cnpj, String nomeFantasia, String endereco,
			String razaoSocial, String telefone) {
		super();
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.endereco = endereco;
		this.razaoSocial = razaoSocial;
		this.telefone = telefone;
	}

	public Mercado(Long id, String cnpj, String nomeFantasia, String endereco,
			String razaoSocial, String telefone) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.endereco = endereco;
		this.razaoSocial = razaoSocial;
		this.telefone = telefone;
	}

	public Mercado(Long id, String nomeFantasia) {
		super();
		this.id = id;
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		Mercado other = (Mercado) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
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
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
