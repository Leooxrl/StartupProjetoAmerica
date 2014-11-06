package br.com.apostilaJSFeJPA;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

@ManagedBean
public class AutomovelBean {

	private Automovel automovel = new Automovel();

	public void salvar(Automovel auto) {

		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(auto);
		em.getTransaction().commit();
		em.close();

	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

}
