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

public class FornecedorDAO {

	public static void inserir(Fornecedor fornecedor)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into fornecedor (fornecedor_id,cnpj,nome_fantasia,razao_social) values (?,?,?,?)");

		try {
			ps.setLong(1, fornecedor.getId());
			ps.setString(2, fornecedor.getCnpj());
			ps.setString(3, fornecedor.getNomeFantasia());
			ps.setString(4, fornecedor.getRazaoSocial());
			ps.execute();

		} finally {
			conn.close();
		}

	}

	public static List<Fornecedor> consultarTodos() throws NamingException,
			SQLException {

		List<Fornecedor> listaRegistro = new ArrayList<Fornecedor>();
		Fornecedor fornecedor;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select fornecedor_id, cnpj, nome_fantasia, razao_social from fornecedor");

				while (rs.next()) {

					fornecedor = new Fornecedor(rs.getLong("fornecedor_id"),
							rs.getString("cnpj"),
							rs.getString("nome_fantasia"),
							rs.getString("razao_social"));

					listaRegistro.add(fornecedor);
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}
		return listaRegistro;
	}

	public static Fornecedor consultarPorId(Long id) throws NamingException,
			SQLException {

		Fornecedor registro = new Fornecedor();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select fornecedor_id, cnpj, nome_fantasia, razao_social from fornecedor where fornecedor_id ="
								+ id);

				if (rs.next()) {

					registro = new Fornecedor(rs.getLong("fornecedor_id"),
							rs.getString("nome_fantasia"),
							rs.getString("razao_social"), rs.getString("cnpj"));

				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return registro;
	}

	public static void atualizar(Fornecedor fornecedor)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();
		PreparedStatement ps = conn
				.prepareStatement("update fornecedor set nome_fantasia = ?, razao_social = ?, cnpj = ? where fornecedor_id = ?");

		try {
			ps.setString(1, fornecedor.getNomeFantasia());
			ps.setString(2, fornecedor.getRazaoSocial());
			ps.setString(3, fornecedor.getCnpj());
			ps.setLong(4, fornecedor.getId());
			ps.execute();

		} finally {
			conn.close();
		}
	}

}
