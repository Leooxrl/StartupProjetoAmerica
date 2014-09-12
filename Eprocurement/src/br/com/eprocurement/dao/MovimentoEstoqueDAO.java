package br.com.eprocurement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.eprocurement.beans.Esmalte;
import br.com.eprocurement.beans.MovimentoEstoque;
import br.com.eprocurement.conexao.BaseDados;

public class MovimentoEstoqueDAO {

	public static void inserirMovimentoEstoque(MovimentoEstoque movEst)
			throws NamingException, SQLException, ClassNotFoundException {

		Connection c = BaseDados.getConexao();

		try {
			PreparedStatement ps = c
					.prepareStatement("insert into movimento_estoque (codigo_mov, observacao, quantidade, codigo_esm) values (?, ?, ?, ?)");
			try {
				ps.setLong(1, BaseDados.getProximoCodigo("movimento_estoque",
						"codigo_mov"));
				ps.setString(2, movEst.getObservacao());
				ps.setDouble(3, movEst.getQuantidade());
				ps.setInt(4, movEst.getEsmalte().getCodigo_esm());
				ps.execute();

			} finally {

				ps.close();
			}
		} finally {
			c.close();
		}
	}

	public static List<MovimentoEstoque> consultarTodos()
			throws NamingException, SQLException {

		List<MovimentoEstoque> listaMoves = new ArrayList<MovimentoEstoque>();
		MovimentoEstoque movEst;

		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				// Esmalte esm;

				ResultSet rs = s
						.executeQuery("select movimento_estoque.*, (esmalte.valor * movimento_estoque.quantidade) total from movimento_estoque,esmalte where movimento_estoque.codigo_esm = esmalte.codigo_esm");

				while (rs.next()) {
					Esmalte esm = new Esmalte();

					esm = EsmalteDAO.consultarEsmalte(rs.getInt("codigo_esm"));

					movEst = new MovimentoEstoque(rs.getString("observacao"),
							rs.getDouble("quantidade"), esm);
					movEst.setCodigo(rs.getInt("codigo_mov"));

					DecimalFormat df = new DecimalFormat("0.00");
					double total = Double.parseDouble(df.format(
							rs.getDouble("total")).replace(",", "."));

					movEst.setTotal(total);

					listaMoves.add(movEst);
				}
			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

		return listaMoves;

	}

	public static void excluirMovimento(int codigo_mov) throws NamingException,
			SQLException {

		Connection c = BaseDados.getConexao();

		try {

			Statement s = c.createStatement();

			try {

				s.execute("delete from movimento_estoque where codigo_mov="
						+ codigo_mov);

			} finally {
				s.close();
			}

		} finally {
			c.close();
		}

	}

}
