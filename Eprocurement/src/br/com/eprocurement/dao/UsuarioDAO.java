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
import br.com.eprocurement.entities.Usuario;

public class UsuarioDAO {
	public static void inserir(Usuario usuario) throws ClassNotFoundException,
			SQLException, NamingException {

		Connection conn = BaseDados.getConexao();

		PreparedStatement ps = conn
				.prepareStatement("insert into usuario (usuario_id, login, senha, perfil) values (?,?,?,?)");

		try {
			ps.setLong(1, usuario.getId());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setLong(4, usuario.getPerfil());
			ps.execute();

		} finally {
			conn.close();
		}
	}

	public static List<Usuario> consultarTodos() throws NamingException,
			SQLException {

		List<Usuario> listaRegistro = new ArrayList<Usuario>();
		Usuario registro;
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select usuario_id, login, perfil from usuario");

				while (rs.next()) {

					registro = new Usuario(rs.getLong("usuario_id"),
							rs.getString("login"), rs.getLong("perfil"));
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

	public static Usuario consultarPorId(Long id) throws NamingException,
			SQLException {

		Usuario registro = new Usuario();
		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select usuario_id, login, perfil, senha from usuario where usuario_id = "
								+ id);

				while (rs.next()) {

					registro = new Usuario(rs.getLong("usuario_id"),
							rs.getString("login"), rs.getString("senha"),
							rs.getLong("perfil"));
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return registro;
	}

	public static boolean isUsuarioColaborador(Long id) throws NamingException,
			SQLException {

		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select 1 from usuario where usuario_id = "
								+ id + " and perfil = 1");

				return rs.next();

			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

	}

	public static Long consultarPorId(String usuarioLogin, String senha)
			throws NamingException, SQLException {

		Connection c = BaseDados.getConexao();
		Long id = 0L;
		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select usuario_id from usuario where login = '"
								+ usuarioLogin
								+ "' and senha = '"
								+ senha
								+ "';");

				if (rs.next()) {

					id = rs.getLong("usuario_id");
				}
				return id;

			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

	}

	public static void atualizar(Usuario usuario)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();
		PreparedStatement ps = conn
				.prepareStatement("update usuario set login = ?, senha = ?, perfil = ? where usuario_id = ?");

		try {
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			ps.setLong(3, usuario.getPerfil());
			ps.setLong(4, usuario.getId());
			ps.execute();

		} finally {
			conn.close();
		}
	}

	public static void atualizarColaborador(Long idUsuario, Long idColaborador)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();
		PreparedStatement ps = conn
				.prepareStatement("update colaborador set usuario_id = ? where colaborador_id = ?");

		try {
			ps.setLong(1, idUsuario);
			ps.setLong(2, idColaborador);
			ps.execute();

		} finally {
			conn.close();
		}
	}

	public static void atualizarVendedor(Long idUsuario, Long idVendedor)
			throws ClassNotFoundException, SQLException, NamingException {

		Connection conn = BaseDados.getConexao();
		PreparedStatement ps = conn
				.prepareStatement("update vendedor set usuario_id = ? where vendedor_id = ?");

		try {
			ps.setLong(1, idUsuario);
			ps.setLong(2, idVendedor);
			ps.execute();

		} finally {
			conn.close();
		}
	}

}
