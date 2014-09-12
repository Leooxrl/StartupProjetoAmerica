package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.dao.ProdutoDAO;
import br.com.eprocurement.entities.Produto;

/**
 * Servlet implementation class AtualizaProduto
 */
@WebServlet("/AtualizaProduto")
public class AtualizaProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizaProduto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("id"));

		try {

			Produto produto = ProdutoDAO.consultarPorId(id);
			request.setAttribute("produto", produto);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("editaProduto.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if ("editarProduto".equals(acao)) {

			String descricao = request.getParameter("descricaoInput");
			Long id = Long.parseLong(request.getParameter("idInput"));

			Produto produto = new Produto(id, descricao);

			try {
				ProdutoDAO.atualizar(produto);

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
