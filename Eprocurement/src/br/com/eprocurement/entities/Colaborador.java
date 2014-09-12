package br.com.eprocurement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Colaborador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "colaborador_id")
	private Long id;
	private String email;
	private String nome;
	private Usuario usuario;
	private Mercado mercado;

	public Colaborador() {

		setEmail("");
		setId(0L);
		setMercado(new Mercado());
		setNome("");
		setUsuario(new Usuario());

	}

	public Colaborador(String email, String nome, Usuario usuario,
			Mercado mercado) {
		super();
		this.email = email;
		this.nome = nome;
		this.usuario = usuario;
		this.mercado = mercado;
	}

	public Colaborador(Long id, String email, String nome, Usuario usuario,
			Mercado mercado) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.usuario = usuario;
		this.mercado = mercado;
	}

	public Colaborador(Long id, String email, String nome, Mercado mercado) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.mercado = mercado;
	}

	public Colaborador(Long id, String email, String nome) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mercado == null) ? 0 : mercado.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Colaborador other = (Colaborador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mercado == null) {
			if (other.mercado != null)
				return false;
		} else if (!mercado.equals(other.mercado))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Mercado getMercado() {
		return mercado;
	}

	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
