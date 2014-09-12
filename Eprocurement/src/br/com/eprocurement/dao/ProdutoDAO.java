package br.com.eprocurement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.eprocurement.conexao.BaseDados;
import br.com.eprocurement.entities.Produto;

public class ProdutoDAO {

	public static void inserir(Produto produto) throws ClassNotFoundException,
			SQLException, NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into produto (produto_id, descricao) values (?, ?)");

		try {
			ps.setLong(1, produto.getId());
			ps.setString(2, produto.getDescricao());
			ps.execute();

		} finally {
			conn.close();
		}

	}

	public static List<Produto> consultarTodos() throws NamingException,
			SQLException {

		List<Produto> listaRegistro = new ArrayList<Produto>();
		Produto registro;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select produto_id, descricao from produto");

				while (rs.next()) {

					registro = new Produto(rs.getLong("produto_id"),
							rs.getString("descricao"));

					listaRegistro.add(registro);
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return listaRegistro;
	}

	public static Produto consultarPorId(Long id) throws NamingException,
			SQLException {

		Produto registro = new Produto();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select produto_id, descricao from produto where produto_id ="
								+ id);

				if (rs.next()) {

					registro = new Produto(rs.getLong("produto_id"),
							rs.getString("descricao"));

				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return registro;
	}

	public static void atualizar(Produto produto)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();
		PreparedStatement ps = conn
				.prepareStatement("update produto set descricao = ? where produto_id = ?");

		try {
			ps.setString(1, produto.getDescricao());
			ps.setLong(2, produto.getId());
			ps.execute();

		} finally {
			conn.close();
		}
	}

	// private EntityManager em = Persistence.createEntityManagerFactory(
	// "EprocurementtccPU").createEntityManager();
	// private EntityDAO dao = new EntityDAO(em);

	/*
	 * public void inserirJPA(Produto produto) {
	 * 
	 * dao.salvar(produto); }
	 */
}
