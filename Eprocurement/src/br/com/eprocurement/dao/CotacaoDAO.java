package br.com.eprocurement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.eprocurement.conexao.BaseDados;
import br.com.eprocurement.entities.Cotacao;

public class CotacaoDAO {

	public static void inserir(Cotacao cotacao) throws ClassNotFoundException,
			SQLException, NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into cotacao (cotacao_id, data_termino, encerrada, comprada, mercado_id) values (?,?,?,?,?)");

		try {
			ps.setLong(1, cotacao.getId());
			ps.setDate(2, new Date(cotacao.getDataTermino().getTime()));
			ps.setBoolean(3, cotacao.isEncerrada());
			ps.setBoolean(4, cotacao.isComprada());
			ps.setLong(5, cotacao.getMercado().getId());
			ps.execute();

		} finally {
			conn.close();
		}

	}

	public static boolean consultarPorIdExiste(Long id) throws NamingException,
			SQLException {

		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select 1 from cotacao where cotacao_id ="
								+ id);

				return rs.next();

			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

	}

	public static void updateEncerradaPorId(Long id) throws NamingException,
			SQLException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("update cotacao set encerrada = 1 where cotacao_id = ?");

		try {
			ps.setLong(1, id);
			ps.execute();

		} finally {
			conn.close();
		}
	}

	public static List<Cotacao> consultarTodos() throws NamingException,
			SQLException {

		List<Cotacao> listaRegistro = new ArrayList<Cotacao>();
		Cotacao registro;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select cotacao_id, data_termino from cotacao where encerrada = 0");

				while (rs.next()) {

					registro = new Cotacao(rs.getLong("cotacao_id"),
							rs.getDate("data_termino"));
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

	public static List<Long> consultarCotacoesPorVendedor(Long idVendedor)
			throws NamingException, SQLException {

		List<Long> listaRegistro = new ArrayList<>();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s.executeQuery("select distinct(cotacao_id) "
						+ "from cotacao, Item_cotacao_Item_cotacao_vendedor "
						+ "where encerrada = 0 and item_cotacao_vendedor_id = "
						+ idVendedor);

				while (rs.next()) {

					listaRegistro.add(rs.getLong("cotacao_id"));
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return listaRegistro;
	}

	public static String consultarPorIdDetalhes(Long id)
			throws NamingException, SQLException {

		java.util.Date registro = new java.util.Date();
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select data_termino from cotacao where cotacao_id ="
								+ id);

				if (rs.next()) {

					try {
						registro = formataData.parse(rs.getDate("data_termino")
								.toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		formataData = new SimpleDateFormat("dd/MM/yyyy");
		String sRegistro = formataData.format(registro);

		return sRegistro;
	}
}
