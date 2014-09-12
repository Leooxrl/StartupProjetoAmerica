package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.dao.MercadoDAO;
import br.com.eprocurement.entities.Mercado;

/**
 * Servlet implementation class AtualizaMercado
 */
@WebServlet("/AtualizaMercado")
public class AtualizaMercado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizaMercado() {
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

			Mercado mercado = MercadoDAO.consultarPorId(id);
			request.setAttribute("mercado", mercado);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("editaMercado.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if ("editarMercado".equals(acao)) {

			Long id = Long.parseLong(request.getParameter("idInput"));
			String nomeFantasia = request.getParameter("nomeFantasiaInput");
			String razaoSocial = request.getParameter("razaoSocialInput");
			String cnpj = request.getParameter("cnpjInput");
			String endereco = request.getParameter("enderecoInput");
			String telefone = request.getParameter("telInput");

			Mercado mercado = new Mercado(id, cnpj, nomeFantasia, endereco,
					razaoSocial, telefone);

			try {
				MercadoDAO.atualizar(mercado);

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
