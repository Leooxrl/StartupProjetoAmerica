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
import br.com.eprocurement.entities.Colaborador;
import br.com.eprocurement.entities.Mercado;

public class ColaboradorDAO {

	public static void inserir(Colaborador colaborador)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into colaborador (colaborador_id, nome, email, mercado_id, usuario_id) values (?,?,?,?,?)");

		try {
			ps.setLong(1, colaborador.getId());
			ps.setString(2, colaborador.getNome());
			ps.setString(3, colaborador.getEmail());
			ps.setLong(4, colaborador.getMercado().getId());
			ps.setLong(5, colaborador.getUsuario().getId());
			ps.execute();

		} finally {
			conn.close();
		}

	}

	public static List<Colaborador> consultarTodos() throws NamingException,
			SQLException {

		List<Colaborador> listaRegistros = new ArrayList<Colaborador>();
		Colaborador registro;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select colaborador_id, email, nome, mercado.nome_fantasia, colaborador.mercado_id from colaborador, mercado where colaborador.mercado_id = mercado.mercado_id");

				while (rs.next()) {

					Mercado mercado = new Mercado(rs.getLong("mercado_id"),
							rs.getString("nome_fantasia"));
					registro = new Colaborador(rs.getLong("colaborador_id"),
							rs.getString("email"), rs.getString("nome"),
							mercado);
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

	public static Colaborador consultarPorId(Long id) throws NamingException,
			SQLException {

		Colaborador registro = new Colaborador();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select colaborador_id, email, nome, mercado.nome_fantasia, colaborador.mercado_id from colaborador, mercado where colaborador.mercado_id = mercado.mercado_id and colaborador_id = "
								+ id);

				if (rs.next()) {

					Mercado mercado = new Mercado(rs.getLong("mercado_id"),
							rs.getString("nome_fantasia"));
					registro = new Colaborador(rs.getLong("colaborador_id"),
							rs.getString("email"), rs.getString("nome"),
							mercado);
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return registro;
	}

	public static void atualizar(Colaborador colaborador)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();
		PreparedStatement ps = conn
				.prepareStatement("update colaborador set nome = ?, email = ? where colaborador_id = ?");

		try {
			ps.setString(1, colaborador.getNome());
			ps.setString(2, colaborador.getEmail());
			ps.setLong(3, colaborador.getId());
			ps.execute();

		} finally {
			conn.close();
		}
	}

	public static Long consultarIdPorUsuarioId(Long id) throws NamingException,
			SQLException {

		Long registro = 0L;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select colaborador_id from colaborador where usuario_id = "
								+ id);

				while (rs.next()) {

					registro = rs.getLong("colaborador_id");
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return registro;
	}

}
