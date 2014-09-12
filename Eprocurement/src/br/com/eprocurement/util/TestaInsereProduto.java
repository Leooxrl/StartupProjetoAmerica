package br.com.eprocurement.util;

import java.sql.SQLException;

import javax.naming.NamingException;

import br.com.eprocurement.beans.Esmalte;
import br.com.eprocurement.dao.EsmalteDAO;
import br.com.eprocurement.entities.Produto;

public class TestaInsereProduto {

	/*
	 * public static void main(String[] args) {
	 * 
	 * EntityManagerFactory factory = Persistence
	 * .createEntityManagerFactory("tccRuonLeandroPU"); EntityManager em =
	 * factory.createEntityManager();
	 * 
	 * Produto p = new Produto(); p.setDescricao("Açucar alto alegre 5kg");
	 * 
	 * em.getTransaction().begin(); em.persist(p); em.getTransaction().commit();
	 * }
	 */

	public static void main(String[] args) {

		Produto p = new Produto();
		p.setDescricao("Salgadinho ruffles original 50g");

		Esmalte esmalte = new Esmalte("vermelha", "LEO", 2);

		try {

			EsmalteDAO.inserirEsmate(esmalte);
			// ProdutoDAO.inserir(p);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}