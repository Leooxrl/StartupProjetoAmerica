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

public class ItemCotacaoVendedorDAO {

	public static void inserir(Long id, Long itemCotacaoId, Long vendedorId)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into Item_cotacao_Item_cotacao_vendedor (id, item_cotacao_id, item_cotacao_vendedor_id) values (?, ?, ?)");

		try {
			ps.setLong(1, id);
			ps.setLong(2, itemCotacaoId);
			ps.setLong(3, vendedorId);
			ps.execute();

		} finally {
			conn.close();
		}

	}

	public static void inserirResposta(Long itemCotacaoVendedorId, Long id,
			Double preco) throws ClassNotFoundException, SQLException,
			NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into Item_cotacao_vendedor (item_cotacao_vendedor_id, preco, id, isAprovado) values (?, ?, ?, ?)");

		try {
			ps.setLong(1, itemCotacaoVendedorId);
			ps.setDouble(2, preco);
			ps.setLong(3, id);
			ps.setLong(4, 0L);
			ps.execute();

		} finally {
			conn.close();
		}

	}

	public static void atualizarResposta(Double preco, Long id)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();
		PreparedStatement ps = conn
				.prepareStatement("update Item_cotacao_vendedor set preco = ? where id = ?");

		try {
			ps.setDouble(1, preco);
			ps.setLong(2, id);
			ps.execute();

		} finally {
			conn.close();
		}
	}

	public static List<List<Object>> consultarVendedorPorCotacaoDetalhes(
			Long idCotacao) throws NamingException, SQLException {

		List<List<Object>> listaRegistro = new ArrayList<>();
		List<Object> registro = new ArrayList<>();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select item_cotacao.item_cotacao_id, nome, Item_cotacao_Item_cotacao_vendedor.id, vendedor.email from Item_cotacao_Item_cotacao_vendedor, "
								+ "vendedor, item_cotacao where Item_cotacao_Item_cotacao_vendedor.item_cotacao_vendedor_id ="
								+ " vendedor.vendedor_id and Item_cotacao_Item_cotacao_vendedor.item_cotacao_id ="
								+ " item_cotacao.item_cotacao_id and cotacao_id = "
								+ idCotacao);

				while (rs.next()) {

					registro = new ArrayList<>();
					registro.add(rs.getLong("item_cotacao_id"));
					registro.add(rs.getString("nome"));
					registro.add(rs.getLong("id"));
					registro.add(rs.getString("email"));
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

	public static List<List<Object>> consultarPrecosVendedorPorCotacao(
			Long idCotacao, Long idVendedor) throws NamingException,
			SQLException {

		List<List<Object>> listaRegistro = new ArrayList<>();
		List<Object> registro = new ArrayList<>();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select preco, Item_cotacao_Item_cotacao_vendedor.id "
								+ "      from Item_cotacao_vendedor,"
								+ "           Item_cotacao_Item_cotacao_vendedor,"
								+ "           item_cotacao"
								+ " where Item_cotacao_vendedor.id = Item_cotacao_Item_cotacao_vendedor.id and"
								+ "       Item_cotacao_Item_cotacao_vendedor.item_cotacao_id = item_cotacao.item_cotacao_id and"
								+ "       item_cotacao.cotacao_id = "
								+ idCotacao
								+ " and "
								+ "Item_cotacao_Item_cotacao_vendedor.item_cotacao_vendedor_id = "
								+ idVendedor);
				while (rs.next()) {

					registro = new ArrayList<>();
					registro.add(rs.getDouble("preco"));
					registro.add(rs.getLong("id"));
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

	public static List<List<Object>> consultarPrecosPorCotacao(Long idCotacao)
			throws NamingException, SQLException {

		List<List<Object>> listaRegistro = new ArrayList<>();
		List<Object> registro = new ArrayList<>();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select preco, Item_cotacao_Item_cotacao_vendedor.id "
								+ "      from Item_cotacao_vendedor,"
								+ "           Item_cotacao_Item_cotacao_vendedor,"
								+ "           item_cotacao"
								+ " where Item_cotacao_vendedor.id = Item_cotacao_Item_cotacao_vendedor.id and"
								+ "       Item_cotacao_Item_cotacao_vendedor.item_cotacao_id = item_cotacao.item_cotacao_id and"
								+ "       item_cotacao.cotacao_id = "
								+ idCotacao);
				while (rs.next()) {

					registro = new ArrayList<>();
					registro.add(rs.getDouble("preco"));
					registro.add(rs.getLong("id"));
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

	public static boolean isPrecoItemRespondidoVendedor(Long id)
			throws NamingException, SQLException {

		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("SELECT 1 FROM Item_cotacao_vendedor "
								+ "WHERE id = " + id);

				return rs.next();

			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

	}

	public static List<String> consultarEmailVendedorPorCotacao(Long idCotacao)
			throws NamingException, SQLException {

		List<String> listaRegistro = new ArrayList<>();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("SELECT DISTINCT (email) email "
								+ "FROM Item_cotacao_Item_cotacao_vendedor "
								+ ",vendedor "
								+ ",item_cotacao "
								+ "WHERE Item_cotacao_Item_cotacao_vendedor.item_cotacao_vendedor_id = vendedor.vendedor_id "
								+ "AND Item_cotacao_Item_cotacao_vendedor.item_cotacao_id = item_cotacao.item_cotacao_id "
								+ "AND cotacao_id = " + idCotacao);

				while (rs.next()) {

					listaRegistro.add(rs.getString("email"));

				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return listaRegistro;
	}

}
