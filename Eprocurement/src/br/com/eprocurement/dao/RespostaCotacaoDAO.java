package br.com.eprocurement.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.eprocurement.conexao.BaseDados;

public class RespostaCotacaoDAO {

	public static List<List<Object>> consultaritensporCotacaoVendedor(
			Long idCotacao, Long idVendedor) throws NamingException,
			SQLException {

		List<List<Object>> listaRegistro = new ArrayList<>();
		List<Object> registro;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select "
								+ "             item_cotacao.item_cotacao_id,"
								+ "             quantidade,"
								+ "             item_cotacao.produto_id,"
								+ "             descricao, "
								+ "             Item_cotacao_Item_cotacao_vendedor.id"

								+ " from"
								+ "             produto,"
								+ "             item_cotacao,"
								+ "             Item_cotacao_Item_cotacao_vendedor "
								+ " where"
								+ "           Item_cotacao_Item_cotacao_vendedor.item_cotacao_id = item_cotacao.item_cotacao_id "
								+ "       and produto.produto_id = item_cotacao.produto_id "
								+ "       and cotacao_id = "
								+ idCotacao
								+ "       and Item_cotacao_Item_cotacao_vendedor.item_cotacao_vendedor_id = "
								+ idVendedor
								+ " order by item_cotacao.produto_id");

				while (rs.next()) {
					registro = new ArrayList<>();
					registro.add(rs.getLong("item_cotacao_id"));
					registro.add(rs.getDouble("quantidade"));
					registro.add(rs.getLong("produto_id"));
					registro.add(rs.getString("descricao"));
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

	public static List<List<Object>> consultaritensporCotacao(Long idCotacao)
			throws NamingException, SQLException {

		List<List<Object>> listaRegistro = new ArrayList<>();
		List<Object> registro;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select item_cotacao_vendedor_id, item_cotacao.item_cotacao_id, quantidade,"
								+ " item_cotacao.produto_id, produto.descricao, vendedor.nome, Item_cotacao_Item_cotacao_vendedor.id from item_cotacao, Item_cotacao_Item_cotacao_vendedor, produto, vendedor where"
								+ " Item_cotacao_Item_cotacao_vendedor.item_cotacao_id = item_cotacao.item_cotacao_id "
								+ " and cotacao_id = "
								+ idCotacao
								+ " and produto.produto_id = item_cotacao.produto_id and vendedor.vendedor_id = "
								+ "Item_cotacao_Item_cotacao_vendedor.item_cotacao_vendedor_id"
								+ " order by item_cotacao_vendedor_id");

				while (rs.next()) {
					registro = new ArrayList<>();
					registro.add(rs.getLong("item_cotacao_vendedor_id"));
					registro.add(rs.getLong("item_cotacao_id"));
					registro.add(rs.getDouble("quantidade"));
					registro.add(rs.getLong("produto_id"));
					registro.add(rs.getString("descricao"));
					registro.add(rs.getString("nome"));
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

}
