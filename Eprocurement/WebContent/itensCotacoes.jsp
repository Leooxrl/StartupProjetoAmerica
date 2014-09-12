<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="br.com.eprocurement.entities.CotacaoItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>B2B Cotação Itens</title>
<meta charset="utf-8">
<meta name="description" content="Cotação" />
<meta name="keywords" content="B2B, e-procurement, supermercados" />
<meta name="author" content="Leonardo Ruon Leandro" />
<meta name="viewport" content="width=device-width">

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="icon" href="favicon.ico" type="image/x-icon">

<link rel="stylesheet" type="text/css" href="estilos/styles.css"
	media="screen">
<link rel="stylesheet" type="text/css" href="estilos/mobile.css"
	media="(max-width: 939px)" />
<script src="scripts/jquery-2.1.0.min.js"></script>
<script src="scripts/menuMobile.js"></script>

<style type="text/css" media="all">
span {
	width: 175px;
	display: block;
	float: left;
}

input {
	float: left;
}

p {
	padding-top: 10px;
	clear: all;
}

h2 {
	padding-top: 20px;
	clear: all;
}

label {
	padding-right: 20px;
}

#btnLimpar, #btnConcluir {
	float: none;
}
</style>
</head>
<body>
	<jsp:useBean id="listaCotacaoItem" class="java.util.ArrayList"
		scope="request"></jsp:useBean>
	<jsp:useBean id="idCotacao" class="java.lang.String" scope="request"></jsp:useBean>
	<jsp:useBean id="dataTermino" class="java.lang.String" scope="request"></jsp:useBean>

	<header> <hgroup> <%
 	if (listaCotacaoItem == null) {
 		listaCotacaoItem = new ArrayList<CotacaoItem>();
 	}
 %>

	<h2>B2B NEGOCIAÇÃO DE PREÇOS ENTRE SUPERMERCADISTAS E FORNECEDORES</h2>
	</hgroup> </header>

	<section> <article>
	<form method="post"
		action="CotacaoControlador?acao=adicionarItemCotacao&idCotacao=<%= request.getAttribute("idCotacao") %>&dataTermino=<%= request.getAttribute("dataTermino") %>">

		<h1>
			Itens da cotação
			<%=request.getAttribute("idCotacao")%></h1>
		<p>
			<label><span>produto</span> <input type=number
				id=produtoInput name=produtoInput autofocus required></label>
		</p>
		<p>
			<label> <span>quantidade</span><input type=number
				id=quantidadeInput name=quantidadeInput required></label>
		</p>
		<p>
			<button name=btnInserirItem id=btnInserirItem>Inserir Item</button>
			<input type="reset" name="btnLimpar" value="Limpar" id="btnLimpar">
		</p>

		<table>
			<tr "left" bgcolor="#7fe275">
				<th>quantidade</th>
				<th>produto</th>
				<th>add vendedores</th>
				<th>remover item</th>
			</tr>

			<%
				for (int i = 0; i < listaCotacaoItem.size(); i++) {
			%>

			<tr "left" bgcolor="<%= i % 2 == 0 ? "white" : "#7fe275" %>">
				<td name="idProduto"><%= ((CotacaoItem) listaCotacaoItem.get(i))
						.getQuantidade() %></td>
				<td name="descricaoProduto"><%= ((CotacaoItem) listaCotacaoItem.get(i)).getProduto()
						.getDescricao() %></td>
				<td><a
					href="VendedoresCotacao?acao=adicionarItemVendedor&idItemCotacao=<%out.println(((CotacaoItem) listaCotacaoItem.get(i)).getId());%>&idCotacao=<%out.println(request.getAttribute("idCotacao")); %>&dataTermino=<%out.println(request.getAttribute("dataTermino")); %>">add vendedores</a></td>
				<td><a
					href="ExclusaoControlador?acao=excluirItemCotacao&idItemCotacao=<%out.println(((CotacaoItem) listaCotacaoItem.get(i)).getId()); %>&idCotacao=<%out.println(request.getAttribute("idCotacao")); %>&dataTermino=<%out.println(request.getAttribute("dataTermino")); %>">remover
						item</a></td>
			</tr>

			<%
				}
			%>

		</table>
	</form>
	<p>
		<input type="submit" value="Concluir" id=btnConcluir name=btnConcluir onclick="location. href='AcessoControlador?acao=voltarMenu' ">
	</p>
	</article>
	</section>
</body>
</html>