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
import br.com.eprocurement.entities.Usuario;

/**
 * Servlet implementation class AtualizaUsuario
 */
@WebServlet("/AtualizaUsuario")
public class AtualizaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizaUsuario() {
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

			Usuario usuario = UsuarioDAO.consultarPorId(id);
			request.setAttribute("usuario", usuario);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("editaUsuario.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if ("editarUsuario".equals(acao)) {

			Long perfil = "Colaborador".equals(request
					.getParameter("perfilInput")) ? 1L : 2L;
			String login = request.getParameter("loginInput");
			String senha = request.getParameter("senhaInput");
			Long id = Long.parseLong(request.getParameter("idInput"));

			Usuario usuario = new Usuario();
			try {
				usuario = UsuarioDAO.consultarPorId(id);
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			usuario.setPerfil(perfil);
			usuario.setLogin(login);
			usuario.setSenha(senha);

			try {
				UsuarioDAO.atualizar(usuario);

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
