package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.dao.FornecedorDAO;
import br.com.eprocurement.entities.Fornecedor;

/**
 * Servlet implementation class AtualizaFornecedor
 */
@WebServlet("/AtualizaFornecedor")
public class AtualizaFornecedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizaFornecedor() {
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

			Fornecedor fornecedor = FornecedorDAO.consultarPorId(id);
			request.setAttribute("fornecedor", fornecedor);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("editaFornecedor.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if ("editarFornecedor".equals(acao)) {

			Long id = Long.parseLong(request.getParameter("idInput"));
			String nomeFantasia = request.getParameter("nomeFantasiaInput");
			String razaoSocial = request.getParameter("razaoSocialInput");
			String cnpj = request.getParameter("cnpjInput");

			Fornecedor fornecedor = new Fornecedor(id, nomeFantasia,
					razaoSocial, cnpj);

			try {
				FornecedorDAO.atualizar(fornecedor);

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
