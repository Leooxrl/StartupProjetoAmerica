package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.conexao.BaseDados;
import br.com.eprocurement.dao.ColaboradorDAO;
import br.com.eprocurement.dao.FornecedorDAO;
import br.com.eprocurement.dao.MercadoDAO;
import br.com.eprocurement.dao.ProdutoDAO;
import br.com.eprocurement.dao.UsuarioDAO;
import br.com.eprocurement.dao.VendedorDAO;
import br.com.eprocurement.entities.Colaborador;
import br.com.eprocurement.entities.Fornecedor;
import br.com.eprocurement.entities.Mercado;
import br.com.eprocurement.entities.Produto;
import br.com.eprocurement.entities.Usuario;
import br.com.eprocurement.entities.Vendedor;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Controlador() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if ("adicionarProduto".equals(acao)) {

			String descricao = request.getParameter("descricaoInput");
			Produto produto = new Produto(descricao);

			try {
				produto.setId(BaseDados.getProximoCodigo("produto",
						"produto_id"));
			} catch (ClassNotFoundException | SQLException | NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				ProdutoDAO.inserir(produto);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.sendRedirect("AcessoControlador?acao=voltarMenu");

		} else if ("adicionarColaborador".equals(acao)) {

			String nome = request.getParameter("nomeInput");
			String email = request.getParameter("emailInput");

			// XXX consultar pelo usuário conectado qual o mercado e código de
			// usuário

			Usuario usuario = new Usuario();
			usuario.setId(1L);
			Mercado mercado = new Mercado();
			mercado.setId(1L);

			Colaborador colaborador = new Colaborador(email, nome, usuario,
					mercado);

			try {
				colaborador.setId(BaseDados.getProximoCodigo("colaborador",
						"colaborador_id"));
			} catch (ClassNotFoundException | SQLException | NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				ColaboradorDAO.inserir(colaborador);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.sendRedirect("AcessoControlador?acao=voltarMenu");

		} else if ("adicionarMercado".equals(acao)) {

			String nomeFantasia = request.getParameter("nomeFantasiaInput");
			String razaoSocial = request.getParameter("razaoSocialInput");
			String cnpj = request.getParameter("cnpjInput");
			String endereco = request.getParameter("enderecoInput");
			String telefone = request.getParameter("telInput");

			Mercado mercado = new Mercado(cnpj, nomeFantasia, endereco,
					razaoSocial, telefone);

			try {
				mercado.setId(BaseDados.getProximoCodigo("mercado",
						"mercado_id"));
			} catch (ClassNotFoundException | SQLException | NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				MercadoDAO.inserir(mercado);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.sendRedirect("AcessoControlador?acao=voltarMenu");

		} else if ("adicionarFornecedor".equals(acao)) {

			String nomeFantasia = request.getParameter("nomeFantasiaInput");
			String razaoSocial = request.getParameter("razaoSocialInput");
			String cnpj = request.getParameter("cnpjInput");

			Fornecedor fornecedor = new Fornecedor(nomeFantasia, razaoSocial,
					cnpj);

			try {
				fornecedor.setId(BaseDados.getProximoCodigo("fornecedor",
						"fornecedor_id"));
			} catch (ClassNotFoundException | SQLException | NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				FornecedorDAO.inserir(fornecedor);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.sendRedirect("AcessoControlador?acao=voltarMenu");

		} else if ("adicionarVendedor".equals(acao)) {

			String nome = request.getParameter("nomeInput");
			String email = request.getParameter("emailInput");
			String telefone = request.getParameter("telInput");

			// XXX consultar pelo usuário conectado qual o fornecedor e código
			// de
			// usuário

			Usuario usuario = new Usuario();
			usuario.setId(1L);
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setId(1L);

			Vendedor vendedor = new Vendedor(email, telefone, nome, usuario,
					fornecedor);

			try {
				vendedor.setId(BaseDados.getProximoCodigo("vendedor",
						"vendedor_id"));
			} catch (ClassNotFoundException | SQLException | NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				VendedorDAO.inserir(vendedor);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.sendRedirect("AcessoControlador?acao=voltarMenu");

		} else if ("adicionarUsuario".equals(acao)) {
			Long perfil = "Colaborador".equals(request
					.getParameter("perfilInput")) ? 1L : 2L;
			String login = request.getParameter("loginInput");
			String senha = request.getParameter("senhaInput");

			Usuario usuario = new Usuario(login, senha, perfil);

			try {
				usuario.setId(BaseDados.getProximoCodigo("usuario",
						"usuario_id"));
			} catch (ClassNotFoundException | SQLException | NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				UsuarioDAO.inserir(usuario);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.sendRedirect("AcessoControlador?acao=voltarMenu");

		}

	}
}