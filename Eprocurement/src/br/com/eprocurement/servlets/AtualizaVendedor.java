package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.dao.VendedorDAO;
import br.com.eprocurement.entities.Vendedor;

/**
 * Servlet implementation class AtualizaVendedor
 */
@WebServlet("/AtualizaVendedor")
public class AtualizaVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizaVendedor() {
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

			Vendedor vendedor = VendedorDAO.consultarPorId(id);
			request.setAttribute("vendedor", vendedor);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("editaVendedor.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if ("editarVendedor".equals(acao)) {

			Long id = Long.parseLong(request.getParameter("idInput"));
			String nome = request.getParameter("nomeInput");
			String email = request.getParameter("emailInput");
			String telefone = request.getParameter("telInput");

			Vendedor vendedor = new Vendedor();
			try {
				vendedor = VendedorDAO.consultarPorId(id);
				vendedor.setNome(nome);
				vendedor.setEmail(email);
				vendedor.setTelefone(telefone);

			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				VendedorDAO.atualizar(vendedor);

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
