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
 * Servlet implementation class UsuarioControlador
 */
@WebServlet("/UsuarioControlador")
public class UsuarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if ("abreRelacionarUsuario".equals(acao)) {

			String idUsuario = request.getParameter("id");
			String perfil = request.getParameter("perfil");

			request.setAttribute("idUsuario", idUsuario);
			request.setAttribute("perfil", perfil);
			request.getRequestDispatcher("usuarioRelacao.jsp").forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if ("relacionarUsuario".equals(acao)) {

			Long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
			Long perfil = Long.parseLong(request.getParameter("perfil"));
			Long idVendCol = Long.parseLong(request
					.getParameter("vendedorColaboradorInput"));

			if (perfil == 1) {

				try {
					UsuarioDAO.atualizarColaborador(idUsuario, idVendCol);
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

			} else {

				try {
					UsuarioDAO.atualizarVendedor(idUsuario, idVendCol);
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
			}

			response.sendRedirect("ListaUsuario");
		}
	}
}
