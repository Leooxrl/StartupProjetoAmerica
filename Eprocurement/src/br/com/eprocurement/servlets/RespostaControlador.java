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
import br.com.eprocurement.dao.ColaboradorDAO;
import br.com.eprocurement.dao.CotacaoDAO;
import br.com.eprocurement.dao.ItemCotacaoVendedorDAO;
import br.com.eprocurement.dao.MercadoDAO;
import br.com.eprocurement.dao.RespostaCotacaoDAO;
import br.com.eprocurement.dao.VendedorDAO;

/**
 * Servlet implementation class RespostaControlador
 */
@WebServlet("/RespostaControlador")
public class RespostaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RespostaControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// consultar perfil do usuário
		// se for colaborador deve trazer todos os itens da cotação com seu
		// respectivo vendedor informado
		// se for vendedor, somente pode trazer os itens daquele vendedor

		Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
		Long idCotacao = Long.parseLong(request.getParameter("idCotacao"));
		String nomeFantasia = "";
		String dataTermino = "";
		try {
			dataTermino = CotacaoDAO.consultarPorIdDetalhes(idCotacao);
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			nomeFantasia = MercadoDAO.consultarNomePorCotacaoId(idCotacao);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String acao = request.getParameter("acao");

		if ("abrirCotacaoColaborador".equals(acao)) {

			List<List<Object>> listaPrecosCotacao = new ArrayList<>();
			List<List<Object>> listaItensCotacao = new ArrayList<>();

			Long idColaborador = 0L;

			try {
				idColaborador = ColaboradorDAO
						.consultarIdPorUsuarioId(idUsuario);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				listaItensCotacao = RespostaCotacaoDAO
						.consultaritensporCotacao(idCotacao);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// consultar se o item já foi respondido
			try {
				listaPrecosCotacao = ItemCotacaoVendedorDAO
						.consultarPrecosPorCotacao(idCotacao);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("dataTermino", dataTermino);
			request.setAttribute("nomeFantasia", nomeFantasia);
			request.setAttribute("idCotacao", idCotacao.toString());
			request.setAttribute("listaPrecosCotacao", listaPrecosCotacao);
			request.setAttribute("listaItensCotacao", listaItensCotacao);
			request.getRequestDispatcher("respostaCotacaoColaborador.jsp")
					.forward(request, response);

		} else if ("abrirCotacaoVendedor".equals(acao)) {

			// consultar código do vendedor do usuário
			Long idVendedor = 0L;
			// montar tela com base nos dados coletados no banco
			List<List<Object>> listaItensCotacaoVendedor = new ArrayList<>();
			List<List<Object>> listaPrecosCotacaoVendedor = new ArrayList<>();

			try {
				idVendedor = VendedorDAO.consultarIdPorUsuarioId(idUsuario);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				listaItensCotacaoVendedor = RespostaCotacaoDAO
						.consultaritensporCotacaoVendedor(idCotacao, idVendedor);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// consultar se o item já foi respondido
			try {
				listaPrecosCotacaoVendedor = ItemCotacaoVendedorDAO
						.consultarPrecosVendedorPorCotacao(idCotacao,
								idVendedor);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("dataTermino", dataTermino);
			request.setAttribute("nomeFantasia", nomeFantasia);
			request.setAttribute("idCotacao", idCotacao.toString());
			request.setAttribute("idVendedor", idVendedor.toString());
			request.setAttribute("listaPrecosCotacaoVendedor",
					listaPrecosCotacaoVendedor);
			request.setAttribute("listaItensCotacaoVendedor",
					listaItensCotacaoVendedor);
			request.getRequestDispatcher("respostaCotacaoVendedor.jsp")
					.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if ("responderCotacaoVendedor".equals(acao)) {

			Long idCotacao = Long.parseLong(request.getParameter("idCotacao"));

			Integer i = Integer.parseInt(request.getParameter("i"));
			Double preco = Double.parseDouble(request.getParameter("precoInput"
					+ i));
			Long id = Long.parseLong(request.getParameter("id"));

			// consultar se o preço o item já foi respondido!
			boolean isPrecoRespondido = false;
			try {
				isPrecoRespondido = ItemCotacaoVendedorDAO
						.isPrecoItemRespondidoVendedor(id);
			} catch (NamingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (isPrecoRespondido) {
				// atualiza preço
				try {
					ItemCotacaoVendedorDAO.atualizarResposta(preco, id);
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
				// insere o preço
				Long itemCotacaoVendedorId = 0L;
				try {
					itemCotacaoVendedorId = BaseDados
							.getProximoCodigo("Item_cotacao_vendedor",
									"item_cotacao_vendedor_id");
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
					ItemCotacaoVendedorDAO.inserirResposta(
							itemCotacaoVendedorId, id, preco);
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
			response.sendRedirect("RespostaControlador?acao=abrirCotacaoVendedor&idCotacao="
					+ idCotacao.toString());

		} else if ("responderCotacaoColaborador".equals(acao)) {
			Long idCotacao = Long.parseLong(request.getParameter("idCotacao"));

			Integer i = Integer.parseInt(request.getParameter("i"));
			Double preco = Double.parseDouble(request.getParameter("precoInput"
					+ i));
			Long id = Long.parseLong(request.getParameter("id"));

			// consultar se o preço o item já foi respondido!
			boolean isPrecoRespondido = false;
			try {
				isPrecoRespondido = ItemCotacaoVendedorDAO
						.isPrecoItemRespondidoVendedor(id);
			} catch (NamingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (isPrecoRespondido) {
				// atualiza preço
				try {
					ItemCotacaoVendedorDAO.atualizarResposta(preco, id);
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
				// insere o preço
				Long itemCotacaoVendedorId = 0L;
				try {
					itemCotacaoVendedorId = BaseDados
							.getProximoCodigo("Item_cotacao_vendedor",
									"item_cotacao_vendedor_id");
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
					ItemCotacaoVendedorDAO.inserirResposta(
							itemCotacaoVendedorId, id, preco);
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
			response.sendRedirect("RespostaControlador?acao=abrirCotacaoColaborador&idCotacao="
					+ idCotacao.toString());

		}
	}

}
