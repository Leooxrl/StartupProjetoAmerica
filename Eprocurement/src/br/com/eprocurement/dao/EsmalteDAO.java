package br.com.eprocurement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.eprocurement.beans.Esmalte;
import br.com.eprocurement.conexao.BaseDados;

public class EsmalteDAO {

	public static Long inserirEsmate(Esmalte esmalte) throws NamingException,
			SQLException, ClassNotFoundException {
		Long codEsm = 0L;
		Connection c = BaseDados.getConexao();

		try {
			PreparedStatement ps = c
					.prepareStatement("insert into esmalte (codigo_esm, cor, marca, valor) values (?, ?, ?, ?)");
			try {

				codEsm = BaseDados.getProximoCodigo("esmalte", "codigo_esm");
				ps.setLong(1, codEsm);
				ps.setString(2, esmalte.getCor());
				ps.setString(3, esmalte.getMarca());
				ps.setDouble(4, esmalte.getValor());
				ps.execute();

			} finally {

				ps.close();
			}
		} finally {
			c.close();
		}
		return codEsm;
	}

	public static Esmalte consultarEsmalte(int codigo_esm)
			throws NamingException, SQLException {

		Esmalte esmalte = new Esmalte();

		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s
						.executeQuery("select * from esmalte where codigo_esm = "
								+ codigo_esm);

				esmalte = new Esmalte(rs.getString("cor"),
						rs.getString("marca"), rs.getDouble("valor"));
				esmalte.setCodigo_esm(codigo_esm);

			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return esmalte;

	}

	public static List<Esmalte> consultarTodosEsmaltes()
			throws NamingException, SQLException {

		Esmalte esmalte = new Esmalte();
		List<Esmalte> listaEsmaltes = new ArrayList<Esmalte>();

		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				ResultSet rs = s.executeQuery("select * from esmalte");

				while (rs.next()) {

					esmalte = new Esmalte(rs.getString("cor"),
							rs.getString("marca"), rs.getDouble("valor"));
					esmalte.setCodigo_esm(rs.getInt("codigo_esm"));
					listaEsmaltes.add(esmalte);
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return listaEsmaltes;

	}
}
