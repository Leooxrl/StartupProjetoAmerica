package br.com.eprocurement.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.eprocurement.conexao.BaseDados;
import br.com.eprocurement.dao.CotacaoDAO;
import br.com.eprocurement.dao.CotacaoItemDAO;
import br.com.eprocurement.dao.MercadoDAO;
import br.com.eprocurement.dao.ProdutoDAO;
import br.com.eprocurement.entities.Cotacao;
import br.com.eprocurement.entities.CotacaoItem;
import br.com.eprocurement.entities.Produto;

/**
 * Servlet implementation class CotacaoControlador
 */
@WebServlet("/CotacaoControlador")
public class CotacaoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CotacaoControlador() {
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

		if ("novaCotacao".equals(acao)) {

			try {
				String nomeFantasiaMercado = MercadoDAO.consultarPorId(1l)
						.getNomeFantasia();
				request.setAttribute("nomeFantasiaMercado", nomeFantasiaMercado);
				request.getRequestDispatcher("cotacoes.jsp").forward(request,
						response);

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if ("voltarAosItens".equals(acao)) {

			Long idCotacao = Long.parseLong(request.getParameter("idCotacao")
					.trim());
			String sdataTermino = request.getParameter("dataTermino");

			List<CotacaoItem> listaCotacaoItem = new ArrayList<>();
			try {
				listaCotacaoItem = CotacaoItemDAO
						.consultarItensPorCotacao(idCotacao);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("idCotacao", idCotacao.toString());
			request.setAttribute("dataTermino", sdataTermino);
			request.setAttribute("listaCotacaoItem", listaCotacaoItem);
			request.getRequestDispatcher("itensCotacoes.jsp").forward(request,
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

		if ("adicionarCotacao".equals(acao)) {

			String dataTermino = request.getParameter("dataTerminoInput");

			String idCotacao = "";
			try {
				idCotacao = BaseDados.getProximoCodigo("cotacao", "cotacao_id")
						.toString();
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
			request.setAttribute("idCotacao", idCotacao);
			request.setAttribute("dataTermino", dataTermino);
			request.getRequestDispatcher("itensCotacoes.jsp").forward(request,
					response);

		} else if ("adicionarItemCotacao".equals(acao)) {

			Long idCotacao = Long.parseLong(request.getParameter("idCotacao")
					.trim());

			Double quantidade = Double.parseDouble(request
					.getParameter("quantidadeInput"));
			Long produtoId = Long.parseLong(request
					.getParameter("produtoInput"));

			SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataTermino = new Date();
			String sdataTermino = request.getParameter("dataTermino");

			try {
				dataTermino = formataData.parse(sdataTermino);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Cotacao cotacao = new Cotacao(idCotacao, dataTermino);
			Produto produto = new Produto();

			try {
				produto = ProdutoDAO.consultarPorId(produtoId);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Long cotacaoItemid = 0L;
			try {
				cotacaoItemid = BaseDados.getProximoCodigo("item_cotacao",
						"item_cotacao_id");
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

			CotacaoItem cotacaoItem = new CotacaoItem(cotacaoItemid,
					quantidade, produto);

			boolean isNovaCotacao = false;

			try {
				isNovaCotacao = !CotacaoDAO.consultarPorIdExiste(idCotacao);
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (isNovaCotacao) {

				try {
					CotacaoDAO.inserir(cotacao);
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

			try {
				CotacaoItemDAO.inserir(idCotacao, cotacaoItem);
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

			List<CotacaoItem> listaCotacaoItem = new ArrayList<>();
			try {
				listaCotacaoItem = CotacaoItemDAO
						.consultarItensPorCotacao(idCotacao);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("idCotacao", idCotacao.toString());
			request.setAttribute("dataTermino", sdataTermino);
			request.setAttribute("listaCotacaoItem", listaCotacaoItem);
			request.getRequestDispatcher("itensCotacoes.jsp").forward(request,
					response);

		}
	}
}
