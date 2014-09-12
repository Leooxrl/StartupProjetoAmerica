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
import br.com.eprocurement.entities.Fornecedor;
import br.com.eprocurement.entities.Vendedor;

public class VendedorDAO {

	public static void inserir(Vendedor vendedor)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into vendedor (vendedor_id, nome, email, telefone, fornecedor_id, usuario_id) values (?,?,?,?,?,?)");

		try {
			ps.setLong(1, vendedor.getId());
			ps.setString(2, vendedor.getNome());
			ps.setString(3, vendedor.getEmail());
			ps.setString(4, vendedor.getTelefone());
			ps.setLong(5, vendedor.getFornecedor().getId());
			ps.setLong(6, vendedor.getUsuario().getId());
			ps.execute();

		} finally {
			conn.close();
		}
	}

	public static List<Vendedor> consultarTodos() throws NamingException,
			SQLException {

		List<Vendedor> listaRegistros = new ArrayList<Vendedor>();
		Vendedor registro;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select vendedor_id, email, nome, telefone, fornecedor.nome_fantasia, vendedor.fornecedor_id from vendedor, fornecedor where vendedor.fornecedor_id = fornecedor.fornecedor_id");

				while (rs.next()) {

					Fornecedor fornecedor = new Fornecedor(
							rs.getLong("fornecedor_id"),
							rs.getString("nome_fantasia"));

					registro = new Vendedor(rs.getLong("vendedor_id"),
							rs.getString("email"), rs.getString("telefone"),
							rs.getString("nome"), fornecedor);
					listaRegistros.add(registro);
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return listaRegistros;
	}

	public static Vendedor consultarPorId(Long id) throws NamingException,
			SQLException {

		Vendedor registro = new Vendedor();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select vendedor_id, email, nome, telefone, fornecedor.nome_fantasia, vendedor.fornecedor_id from vendedor, fornecedor where vendedor.fornecedor_id = fornecedor.fornecedor_id and vendedor_id = "
								+ id);

				while (rs.next()) {

					Fornecedor fornecedor = new Fornecedor(
							rs.getLong("fornecedor_id"),
							rs.getString("nome_fantasia"));

					registro = new Vendedor(rs.getLong("vendedor_id"),
							rs.getString("email"), rs.getString("telefone"),
							rs.getString("nome"), fornecedor);
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return registro;
	}

	public static Long consultarIdPorUsuarioId(Long id) throws NamingException,
			SQLException {

		Long registro = 0L;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select vendedor_id from vendedor where usuario_id = "
								+ id);

				while (rs.next()) {

					registro = rs.getLong("vendedor_id");
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return registro;
	}

	public static void atualizar(Vendedor vendedor)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();
		PreparedStatement ps = conn
				.prepareStatement("update vendedor set nome = ?, email = ?, telefone = ? where vendedor_id = ?");

		try {
			ps.setString(1, vendedor.getNome());
			ps.setString(2, vendedor.getEmail());
			ps.setString(3, vendedor.getTelefone());
			ps.setLong(4, vendedor.getId());
			ps.execute();

		} finally {
			conn.close();
		}
	}

}
