package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.dao.CotacaoDAO;
import br.com.eprocurement.entities.Cotacao;

/**
 * Servlet implementation class EncerraControlador
 */
@WebServlet("/EncerraControlador")
public class EncerraControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EncerraControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if ("abreEncerrar".equals(acao)) {

			try {
				List<Cotacao> listaCotacao = CotacaoDAO.consultarTodos();
				request.setAttribute("listaCotacao", listaCotacao);
				request.getRequestDispatcher("encerrarCotacoes.jsp").forward(
						request, response);

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if ("encerrar".equals(acao)) {

			Long idCotacao = Long.parseLong(request.getParameter("idCotacao")
					.trim());

			try {
				CotacaoDAO.updateEncerradaPorId(idCotacao);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				List<Cotacao> listaCotacao = CotacaoDAO.consultarTodos();
				request.setAttribute("listaCotacao", listaCotacao);
				request.getRequestDispatcher("encerrarCotacoes.jsp").forward(
						request, response);

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
