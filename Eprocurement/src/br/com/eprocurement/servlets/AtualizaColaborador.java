package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.dao.ColaboradorDAO;
import br.com.eprocurement.entities.Colaborador;

/**
 * Servlet implementation class AtualizaColaborador
 */
@WebServlet("/AtualizaColaborador")
public class AtualizaColaborador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizaColaborador() {
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

			Colaborador colaborador = ColaboradorDAO.consultarPorId(id);
			request.setAttribute("colaborador", colaborador);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("editaColaborador.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if ("editarColaborador".equals(acao)) {

			Long id = Long.parseLong(request.getParameter("idInput"));
			String nome = request.getParameter("nomeInput");
			String email = request.getParameter("emailInput");

			Colaborador colaborador = new Colaborador();
			try {
				colaborador = ColaboradorDAO.consultarPorId(id);
				colaborador.setNome(nome);
				colaborador.setEmail(email);

			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				ColaboradorDAO.atualizar(colaborador);

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
