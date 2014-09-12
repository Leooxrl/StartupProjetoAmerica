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
import br.com.eprocurement.entities.Mercado;

public class MercadoDAO {

	public static void inserir(Mercado mercado) throws ClassNotFoundException,
			SQLException, NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into mercado (mercado_id,cnpj,nome_fantasia,endereco,razao_social,telefone) values (?,?,?,?,?,?)");

		try {
			ps.setLong(1, mercado.getId());
			ps.setString(2, mercado.getCnpj());
			ps.setString(3, mercado.getNomeFantasia());
			ps.setString(4, mercado.getEndereco());
			ps.setString(5, mercado.getRazaoSocial());
			ps.setString(6, mercado.getTelefone());
			ps.execute();

		} finally {
			conn.close();
		}

	}

	public static List<Mercado> consultarTodos() throws NamingException,
			SQLException {

		List<Mercado> listaMercado = new ArrayList<Mercado>();
		Mercado mercado;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select mercado_id, cnpj, nome_fantasia, endereco, razao_social, telefone from mercado");

				while (rs.next()) {

					mercado = new Mercado(rs.getLong("mercado_id"),
							rs.getString("cnpj"),
							rs.getString("nome_fantasia"),
							rs.getString("endereco"),
							rs.getString("razao_social"),
							rs.getString("telefone"));

					listaMercado.add(mercado);
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return listaMercado;
	}

	public static Mercado consultarPorId(Long id) throws NamingException,
			SQLException {

		Mercado registro = new Mercado();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select mercado_id, cnpj, nome_fantasia, endereco, razao_social, telefone from mercado where mercado_id ="
								+ id);

				if (rs.next()) {

					registro = new Mercado(rs.getLong("mercado_id"),
							rs.getString("cnpj"),
							rs.getString("nome_fantasia"),
							rs.getString("endereco"),
							rs.getString("razao_social"),
							rs.getString("telefone"));

				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return registro;
	}

	public static String consultarNomePorCotacaoId(Long id)
			throws NamingException, SQLException {

		Connection c = BaseDados.getConexao();

		String registro = "";
		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select nome_fantasia from mercado, cotacao where mercado.mercado_id = cotacao.mercado_id and cotacao_id ="
								+ id);

				if (rs.next()) {

					registro = rs.getString("nome_fantasia");

				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return registro;
	}

	public static void atualizar(Mercado mercado)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();
		PreparedStatement ps = conn
				.prepareStatement("update mercado set nome_fantasia = ?, razao_social = ?, cnpj = ?, endereco = ?, telefone = ? where mercado_id = ?");

		try {
			ps.setString(1, mercado.getNomeFantasia());
			ps.setString(2, mercado.getRazaoSocial());
			ps.setString(3, mercado.getCnpj());
			ps.setString(4, mercado.getEndereco());
			ps.setString(5, mercado.getTelefone());
			ps.setLong(6, mercado.getId());
			ps.execute();

		} finally {
			conn.close();
		}
	}

}
