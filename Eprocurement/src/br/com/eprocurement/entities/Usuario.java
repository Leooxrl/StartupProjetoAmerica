package br.com.eprocurement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usuario_id")
	private Long id;

	private String login;
	private String senha;
	private Long perfil;

	public Usuario() {
		setId(0L);
		setLogin("");
		setSenha("");
	}

	public Usuario(String login, String senha, Long perfil) {
		super();
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public Usuario(Long id, String login, String senha, Long perfil) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public Usuario(Long id, String login, Long perfil) {
		super();
		this.id = id;
		this.login = login;
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getPerfil() {
		return perfil;
	}

	public void setPerfil(Long perfil) {
		this.perfil = perfil;
	}

}