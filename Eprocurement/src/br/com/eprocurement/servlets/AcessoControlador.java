package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.dao.UsuarioDAO;

/**
 * Servlet implementation class AcessoControlador
 */
@WebServlet("/AcessoControlador")
public class AcessoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcessoControlador() {
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

		if ("acessar".equals(acao)) {

			String usuarioLogin = request.getParameter("usuarioInput");
			String senha = request.getParameter("senhaInput");

			Long id = 0L;
			try {
				id = UsuarioDAO.consultarPorId(usuarioLogin, senha);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!id.equals(0L)) {

				String isColaborador = "";

				try {
					isColaborador = UsuarioDAO.isUsuarioColaborador(id) ? "1"
							: "0";
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				request.getSession().setAttribute("idUsuario", id);
				request.setAttribute("isColaborador", isColaborador);
				request.setAttribute("escondeMenu", "hidden=true");
				request.getRequestDispatcher("menuC.jsp").forward(request,
						response);
			} else {
				response.sendRedirect("login.jsp");
			}

		} else if ("voltarMenu".equals(acao)) {

			Long id = (Long) request.getSession().getAttribute("idUsuario");

			if (!id.equals(0L)) {

				String isColaborador = "";

				try {
					isColaborador = UsuarioDAO.isUsuarioColaborador(id) ? "1"
							: "0";
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				request.getSession().setAttribute("idUsuario", id);
				request.setAttribute("isColaborador", isColaborador);
				request.setAttribute("escondeMenu", "hidden=true");
				request.getRequestDispatcher("menuC.jsp").forward(request,
						response);
			} else {
				response.sendRedirect("login.jsp");
			}

		}

	}

}
