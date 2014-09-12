package br.com.eprocurement.dao;

import javax.persistence.EntityManager;

public class EntityDAO {

	private EntityManager em;

	public EntityDAO(EntityManager em) {
		this.em = em;
	}

	public <T> void atualizar(T o) {
		em.refresh(em.merge(o));
	}

	public <T> void salvar(T o) {
		em.getTransaction().begin();
		em.persist(em.merge(o));
		em.getTransaction().commit();
	}

}
