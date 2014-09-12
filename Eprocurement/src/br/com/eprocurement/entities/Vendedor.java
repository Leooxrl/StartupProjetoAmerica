package br.com.eprocurement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vendedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendedor_id")
	private Long id;

	private String nome;
	private String email;
	private String telefone;
	private Usuario usuario;
	private Fornecedor fornecedor;

	public Vendedor() {
		setId(0L);
		setEmail("");
		setFornecedor(new Fornecedor());
		setTelefone("");
		setUsuario(new Usuario());
	}

	public Vendedor(String email, String telefone, String nome,
			Usuario usuario, Fornecedor fornecedor) {
		super();
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.usuario = usuario;
		this.fornecedor = fornecedor;
	}

	public Vendedor(Long id, String email, String telefone, String nome,
			Usuario usuario, Fornecedor fornecedor) {
		super();
		this.id = id;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.usuario = usuario;
		this.fornecedor = fornecedor;
	}

	public Vendedor(Long id, String email, String telefone, String nome,
			Fornecedor fornecedor) {
		super();
		this.id = id;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Vendedor other = (Vendedor) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
