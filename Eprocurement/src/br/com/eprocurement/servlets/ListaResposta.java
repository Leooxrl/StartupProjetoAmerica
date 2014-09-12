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
import br.com.eprocurement.dao.UsuarioDAO;
import br.com.eprocurement.dao.VendedorDAO;
import br.com.eprocurement.entities.Cotacao;

/**
 * Servlet implementation class ListaResposta
 */
@WebServlet("/ListaResposta")
public class ListaResposta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListaResposta() {
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
		Long id = (Long) request.getSession().getAttribute("idUsuario");
		boolean isColaborador = true;
		if ("responder".equals(acao)) {

			try {
				isColaborador = UsuarioDAO.isUsuarioColaborador(id);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (isColaborador) {
				// Lista todas as cotações não encerradas
				try {
					List<Cotacao> listaCotacao = CotacaoDAO.consultarTodos();
					request.setAttribute("listaCotacao", listaCotacao);
					request.getRequestDispatcher(
							"listaCotacoesRespostaColaborador.jsp").forward(
							request, response);

				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				Long idVendedor = 0L;
				try {
					idVendedor = VendedorDAO.consultarIdPorUsuarioId(id);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// consultar cotacoes do vendedor
				// Lista todas as cotações não encerradas
				try {
					List<Long> listaCotacao = CotacaoDAO
							.consultarCotacoesPorVendedor(idVendedor);
					request.setAttribute("listaCotacao", listaCotacao);
					request.getRequestDispatcher(
							"listaCotacoesRespostaVendedor.jsp").forward(
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
