package br.com.eprocurement.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDados {

	private final static String CRIA_TB_USUARIO = "CREATE TABLE Usuario (login varchar(16),senha varchar(16), perfil integer, usuario_id integer AUTO_INCREMENT PRIMARY KEY );";
	private final static String CRIA_TB_COLABORADOR = "CREATE TABLE Colaborador ( colaborador_id integer AUTO_INCREMENT PRIMARY KEY, email varchar(50), nome varchar(50), usuario_id integer, mercado_id integer, FOREIGN KEY(usuario_id) REFERENCES Usuario (usuario_id) );";
	private final static String CRIA_TB_MERCADO = "CREATE TABLE Mercado ( cnpj varchar(40), mercado_id integer AUTO_INCREMENT PRIMARY KEY, nome_fantasia varchar(60), endereco varchar(100), razao_social varchar(60), telefone varchar(20) );";
	private final static String CRIA_TB_PRODUTO = "CREATE TABLE Produto ( descricao varchar(100), produto_id integer AUTO_INCREMENT PRIMARY KEY );";
	private final static String CRIA_TB_FORNECEDOR = "CREATE TABLE Fornecedor (cnpj varchar(40), nome_fantasia varchar(60), razao_social varchar(60), fornecedor_id integer AUTO_INCREMENT PRIMARY KEY);";
	private final static String CRIA_TB_COTACAO = "CREATE TABLE Cotacao ( cotacao_id integer AUTO_INCREMENT PRIMARY KEY,data_termino date, encerrada integer(1),comprada integer(1), mercado_id integer, FOREIGN KEY(mercado_id) REFERENCES Mercado (mercado_id) );";
	private final static String CRIA_TB_VENDEDOR = "CREATE TABLE Vendedor (nome varchar(50), email varchar(50), telefone varchar(20), vendedor_id integer AUTO_INCREMENT PRIMARY KEY, usuario_id integer, fornecedor_id integer, FOREIGN KEY(usuario_id) REFERENCES Usuario (usuario_id), FOREIGN KEY(fornecedor_id) REFERENCES Fornecedor (fornecedor_id) );";
	private final static String CRIA_TB_ITEM_COTACAO = "CREATE TABLE item_cotacao (item_cotacao_id integer AUTO_INCREMENT PRIMARY KEY, quantidade double, produto_id integer, cotacao_id integer,FOREIGN KEY(produto_id) REFERENCES Produto (produto_id), FOREIGN KEY(cotacao_id) REFERENCES Cotacao (cotacao_id));";
	private final static String CRIA_TB_ITEM_COTACAO_VENDEDOR = "CREATE TABLE Item_cotacao_vendedor (item_cotacao_vendedor_id integer AUTO_INCREMENT PRIMARY KEY, preco double, isAprovado integer(1) );";
	private final static String CRIA_TB_PRODUTO_FORNECEDOR = "CREATE TABLE Produto_Fornecedor (fornecedor_id integer,produto_id integer,FOREIGN KEY(fornecedor_id) REFERENCES Fornecedor (fornecedor_id),FOREIGN KEY(produto_id) REFERENCES Produto (produto_id));";
	private final static String CRIA_TB_VENDEDOR_ITEM_COTACAO = "CREATE TABLE Vendedor_item_cotacao (item_cotacao_vendedor_id integer,vendedor_id integer,FOREIGN KEY(item_cotacao_vendedor_id) REFERENCES Item_cotacao_vendedor (item_cotacao_vendedor_id),FOREIGN KEY(vendedor_id) REFERENCES Vendedor (vendedor_id));";
	private final static String CRIA_TB_ITEM_COTACAO_ITEM_COTACAO_VENDEDOR = "CREATE TABLE Item_cotacao_Item_cotacao_vendedor (id integer AUTO_INCREMENT PRIMARY KEY, item_cotacao_vendedor_id integer,item_cotacao_id integer,FOREIGN KEY(item_cotacao_vendedor_id) REFERENCES Item_cotacao_vendedor (item_cotacao_vendedor_id),FOREIGN KEY(item_cotacao_id) REFERENCES item_cotacao (item_cotacao_id));";
	private final static String ALTERA_COLABORADOR = "ALTER TABLE Colaborador ADD FOREIGN KEY(mercado_id) REFERENCES Mercado (mercado_id);";
	private final static String ALTERA_ITEM_COTACAO_VENDEDOR = "ALTER TABLE Item_cotacao_vendedor ADD COLUMN id INTEGER;";

	public static Connection getConexao() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/eprocurementtcc");
		return ds.getConnection();
	}

	static {
		try {
			try (Connection c = getConexao()) {

				try (Statement s = c.createStatement()) {

					s.executeUpdate(CRIA_TB_USUARIO);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_COLABORADOR);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_MERCADO);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_PRODUTO);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_FORNECEDOR);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_COTACAO);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_VENDEDOR);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_ITEM_COTACAO);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_ITEM_COTACAO_VENDEDOR);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_PRODUTO_FORNECEDOR);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_VENDEDOR_ITEM_COTACAO);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(CRIA_TB_ITEM_COTACAO_ITEM_COTACAO_VENDEDOR);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(ALTERA_COLABORADOR);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
				try (Statement s = c.createStatement()) {
					s.executeUpdate(ALTERA_ITEM_COTACAO_VENDEDOR);
				} catch (SQLException e) {
					System.err.println("Erro ao iniciar a base de dados. "
							+ e.getMessage());
				}
			}
		} catch (NamingException | SQLException e) {
			System.err.println("Erro ao iniciar a base de dados. "
					+ e.getMessage());
		}
	}

	/**
	 * Retorna o próximo código da disponível para o campo e tabela informados.
	 * 
	 * @param tabela
	 *            o nome da tabela que será consultada.
	 * @param campo
	 *            o nome do campo que será consultado.
	 * @return o próximo código que deverá ser usado nas operações de inserção.
	 * @throws ClassNotFoundException
	 *             caso a conexão com o banco de dados apresente problemas.
	 * @throws SQLException
	 *             caso a conexão com o banco de dados apresente problemas.
	 * @throws NamingException
	 */
	public static Long getProximoCodigo(String tabela, String campo)
			throws ClassNotFoundException, SQLException, NamingException {

		try (Statement s = getConexao().createStatement()) {

			String sql = String.format("select max(%s)+1 from %s", campo,
					tabela);
			try (ResultSet rs = s.executeQuery(sql)) {

				return rs.next() && rs.getLong(1) != 0l ? rs.getLong(1) : 1L;
			}
		}
	}

	public static void excluirPorId(String tabela, String campo, Long id)
			throws SQLException, NamingException {

		Connection conn = getConexao();
		PreparedStatement ps = conn.prepareStatement("delete from " + tabela
				+ " where " + campo + " = " + id);

		try {
			ps.execute();

		} finally {
			conn.close();
		}
	}

	public static void excluirPorDoisId(String tabela, String campo,
			String campo2, Long id, Long id2) throws SQLException,
			NamingException {

		Connection conn = getConexao();
		PreparedStatement ps = conn.prepareStatement("delete from " + tabela
				+ " where " + campo + " = " + id + " and " + campo2 + " = "
				+ id2);

		try {
			ps.execute();

		} finally {
			conn.close();
		}
	}

}
