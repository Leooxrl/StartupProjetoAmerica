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
import br.com.eprocurement.entities.CotacaoItem;
import br.com.eprocurement.entities.Vendedor;

/**
 * Servlet implementation class AtualizarControler
 */
@WebServlet("/ExclusaoControlador")
public class ExclusaoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExclusaoControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		Long id = Long.parseLong(request.getParameter("id"));
		String tabela = "";
		String campo = "";

		if ("excluirProduto".equals(acao)) {

			tabela = "produto";
			campo = "produto_id";

		} else if ("excluirColaborador".equals(acao)) {

			tabela = "colaborador";
			campo = "colaborador_id";

		} else if ("excluirMercado".equals(acao)) {

			tabela = "mercado";
			campo = "mercado_id";

		} else if ("excluirFornecedor".equals(acao)) {
			tabela = "fornecedor";
			campo = "fornecedor_id";

		} else if ("excluirVendedor".equals(acao)) {
			tabela = "vendedor";
			campo = "vendedor_id";

		} else if ("excluirUsuario".equals(acao)) {
			tabela = "usuario";
			campo = "usuario_id";

		} else if ("excluirItemCotacao".equals(acao)) {
			tabela = "item_cotacao";
			campo = "item_cotacao_id";

		} else if ("excluirVendedorItemCotacao".equals(acao)) {

			tabela = " Item_cotacao_Item_cotacao_vendedor";
			campo = "item_cotacao_vendedor_id";
			String campo2 = "item_cotacao_id";
			Long id2 = Long.parseLong(request.getParameter("idItemCotacao"));

			try {
				BaseDados.excluirPorDoisId(tabela, campo, campo2, id, id2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

			return;
		}

		try {
			BaseDados.excluirPorId(tabela, campo, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if ("excluirItemCotacao".equals(acao)) {

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

		} else {
			response.sendRedirect("AcessoControlador?acao=voltarMenu");
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
