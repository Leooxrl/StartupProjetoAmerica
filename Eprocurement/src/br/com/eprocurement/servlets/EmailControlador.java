package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.dao.CotacaoDAO;
import br.com.eprocurement.dao.CotacaoItemDAO;
import br.com.eprocurement.dao.ItemCotacaoVendedorDAO;
import br.com.eprocurement.util.Email;

/**
 * Servlet implementation class EmailControlador
 */
@WebServlet("/EmailControlador")
public class EmailControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailControlador() {
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

		if ("detalhe".equals(acao)) {

			String tela = "EncerraControlador".equals(request
					.getParameter("tela")) ? "EncerraControlador?acao=abreEncerrar"
					: "EnviarControlador?acao=abreEnviar";

			Long idCotacao = Long.parseLong(request.getParameter("idCotacao")
					.trim());

			List<List<Object>> listaItens = new ArrayList<>();
			List<List<Object>> listaVendedores = new ArrayList<>();
			List<List<Object>> listaPrecosVendedores = new ArrayList<>();

			String dataValidade = "";

			try {
				dataValidade = CotacaoDAO.consultarPorIdDetalhes(idCotacao);
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				listaItens = CotacaoItemDAO
						.consultarItensPorCotacaoDetalhes(idCotacao);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				listaVendedores = ItemCotacaoVendedorDAO
						.consultarVendedorPorCotacaoDetalhes(idCotacao);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				listaPrecosVendedores = ItemCotacaoVendedorDAO
						.consultarPrecosPorCotacao(idCotacao);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("tela", tela);
			request.setAttribute("dataValidade", dataValidade);
			request.setAttribute("listaItens", listaItens);
			request.setAttribute("listaVendedores", listaVendedores);
			request.setAttribute("idCotacao", idCotacao.toString());
			request.setAttribute("listaPrecosVendedores", listaPrecosVendedores);
			request.getRequestDispatcher("detalhesEmail.jsp").forward(request,
					response);

		} else if ("enviar".equals(acao)) {

			//

			Email email = new Email();

			// buscar lista de email dos vendedores
			// mensagem
			// assunto
			String assunto = "Voc� tem uma nova cota��o no portal e-procurement";
			String mensagem = "Ol�,\nPara responder a cota��o por favor acesse o seu portal e-procurement no endere�o: http://localhost:8080/Eprocurement";

			Long idCotacao = Long.parseLong(request.getParameter("idCotacao")
					.trim());

			List<String> listaDestinatarios = new ArrayList<>();
			try {
				listaDestinatarios = ItemCotacaoVendedorDAO
						.consultarEmailVendedorPorCotacao(idCotacao);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			email.enviarEmail(listaDestinatarios, assunto, mensagem);
			request.getRequestDispatcher("EnviarControlador?acao=abreEnviar")
					.forward(request, response);

		} else if ("enviarCompra".equals(acao)) {

			Long idCotacao = Long.parseLong(request.getParameter("idCotacao")
					.trim());
			String destinatario = request.getParameter("email");

			List<String> listaDestinatarios = new ArrayList<>();
			listaDestinatarios.add(destinatario);

			Email email = new Email();
			String assunto = "Solicita��o de compra - cota��o " + idCotacao;
			String mensagem = "Ol�,\nSolicito a compra dos produtos da cota��o "
					+ idCotacao
					+ ". Para mais informa��es por favor acesse o seu portal e-procurement no endere�o: http://localhost:8080/Eprocurement";

			email.enviarEmail(listaDestinatarios, assunto, mensagem);
			request.getRequestDispatcher("EnviarControlador?acao=abreEnviar")
					.forward(request, response);
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
