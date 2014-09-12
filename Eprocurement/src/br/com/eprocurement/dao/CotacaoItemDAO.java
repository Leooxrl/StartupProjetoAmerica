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
import br.com.eprocurement.entities.CotacaoItem;
import br.com.eprocurement.entities.Produto;
import br.com.eprocurement.entities.Vendedor;

public class CotacaoItemDAO {

	public static void inserir(Long idCotacao, CotacaoItem cotacaoItem)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into item_cotacao (cotacao_id, item_cotacao_id, quantidade, produto_id) values (?,?,?,?)");

		try {
			ps.setLong(1, idCotacao);
			ps.setLong(2, cotacaoItem.getId());
			ps.setDouble(3, cotacaoItem.getQuantidade());
			ps.setLong(4, cotacaoItem.getProduto().getId());
			ps.execute();

		} finally {
			conn.close();
		}

	}

	public static List<CotacaoItem> consultarItensPorCotacao(Long idCotacao)
			throws NamingException, SQLException {

		List<CotacaoItem> listaRegistro = new ArrayList<CotacaoItem>();
		CotacaoItem registro;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select item_cotacao_id, quantidade, produto_id from item_cotacao where cotacao_id = "
								+ idCotacao);

				while (rs.next()) {

					Produto produto = ProdutoDAO.consultarPorId(rs
							.getLong("produto_id"));

					registro = new CotacaoItem(rs.getLong("item_cotacao_id"),
							rs.getDouble("quantidade"), produto);

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

	public static List<Vendedor> consultarVendedoresPorItem(Long idItemCotacao)
			throws NamingException, SQLException {

		List<Vendedor> listaRegistro = new ArrayList<Vendedor>();
		Vendedor registro;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select item_cotacao_vendedor_id from Item_cotacao_Item_cotacao_vendedor where item_cotacao_id = "
								+ idItemCotacao);

				while (rs.next()) {

					registro = VendedorDAO.consultarPorId(rs
							.getLong("item_cotacao_vendedor_id"));
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

	public static List<List<Object>> consultarItensPorCotacaoDetalhes(
			Long idCotacao) throws NamingException, SQLException {

		List<List<Object>> listaRegistro = new ArrayList<>();
		List<Object> registro = new ArrayList<>();

		Connection c = BaseDados.getConexao();
		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select item_cotacao_id, quantidade, descricao from item_cotacao, produto where item_cotacao.produto_id = produto.produto_id and cotacao_id = "
								+ idCotacao);

				while (rs.next()) {
					registro = new ArrayList<>();
					registro.add(rs.getLong("item_cotacao_id"));
					registro.add(rs.getDouble("quantidade"));
					registro.add(rs.getString("descricao"));

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
