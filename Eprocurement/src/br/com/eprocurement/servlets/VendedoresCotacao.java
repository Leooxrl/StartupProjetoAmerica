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

import br.com.eprocurement.conexao.BaseDados;
import br.com.eprocurement.dao.CotacaoItemDAO;
import br.com.eprocurement.dao.ItemCotacaoVendedorDAO;
import br.com.eprocurement.entities.Vendedor;

/**
 * Servlet implementation class VendedoresCotacao
 */
@WebServlet("/VendedoresCotacao")
public class VendedoresCotacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VendedoresCotacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long idItemCotacao = Long.parseLong(request
				.getParameter("idItemCotacao"));

		Long idCotacao = Long.parseLong(request.getParameter("idCotacao")
				.trim());

		String sdataTermino = request.getParameter("dataTermino");

		List<Vendedor> listaVendedores = new ArrayList<Vendedor>();
		try {
			listaVendedores = CotacaoItemDAO
					.consultarVendedoresPorItem(idItemCotacao);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("idItemCotacao", String.valueOf(idItemCotacao));
		request.setAttribute("idCotacao", idCotacao.toString());
		request.setAttribute("dataTermino", sdataTermino);
		request.setAttribute("listaVendedores", listaVendedores);
		request.getRequestDispatcher("itensCotacaoVendedores.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if ("adicionarItemVendedor".equals(acao)) {

			Long vendedorId = Long.parseLong(request
					.getParameter("vendedorInput"));

			Long itemCotacaoId = Long.parseLong(request
					.getParameter("idItemCotacao"));

			Long id = 0L;
			try {
				id = BaseDados.getProximoCodigo(
						"Item_cotacao_Item_cotacao_vendedor", "id");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				ItemCotacaoVendedorDAO.inserir(id, itemCotacaoId, vendedorId);
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

			this.doGet(request, response);
		}
	}

}
