package br.com.apostilaJSFeJPA;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Transaction;


@ManagedBean
public class AutomovelBean {

	private Automovel automovel = new Automovel();
	private List<Automovel> automoveis;

	public void salvar(Automovel auto) {

		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(auto);
		em.getTransaction().commit();
		em.close();

	}

	public List<Automovel> getAutomoveis() {
		
		// precisa desse if porque o JSF executa o getAutomoveis para cada linha da tabela, com o if garantimos a performance
		if (this.automoveis == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("select a from Automovel a",
					Automovel.class);
			this.automoveis = q.getResultList();
			em.close();
		}
		return automoveis;
	}
	
	public void excluir(Automovel automovel){
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		automovel = em.merge(automovel);
		em.remove(automovel);
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
