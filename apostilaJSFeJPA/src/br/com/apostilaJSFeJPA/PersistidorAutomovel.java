package br.com.apostilaJSFeJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistidorAutomovel {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("apostilaJSFeJPAUI");
		EntityManager em = emf.createEntityManager();

		Automovel fiatUno = new Automovel("Fiat", "Uno mille", 1996);

		EntityTransaction et = em.getTransaction();
		et.begin();

		em.persist(fiatUno);
		et.commit();
		em.close();
		emf.close();

	}

}
