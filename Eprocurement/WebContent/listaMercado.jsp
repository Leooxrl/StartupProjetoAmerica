<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.eprocurement.entities.Mercado"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>B2B Cotação Mercados</title>
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

#btnLimpar {
	float: none;
}
</style>
</head>
<body>

	<jsp:useBean id="listaMercado" class="java.util.ArrayList"
		scope="request"></jsp:useBean>
	<header> <hgroup>

	<h2>B2B NEGOCIAÇÃO DE PREÇOS ENTRE SUPERMERCADISTAS E FORNECEDORES</h2>
	<ul>
		<li><a class="nav" href="mercados.jsp">Novo Mercado</a></li>
	</ul>
	</hgroup> </header>

	<section> <article>

	<form action="ListaMercado" method="post">
		<h1>Mercados</h1>
		<input type=text id=acao name=acao value="listarMercado" hidden=true>
		<p>
		<table>
			<tr "left" bgcolor="#7fe275">
				<th>código</th>
				<th>nome Fantasia</th>
				<th>razão Social</th>
				<th>cnpj</th>
				<th>endereço</th>
				<th>telefone</th>
				<th>editar</th>
				<th>excluir</th>
			</tr>

			<%
				for (int i = 2; i < listaMercado.size(); i++) {
			%>
			<form method="post" action="AtualizarControlador">
				<tr "left" bgcolor="<%=i % 2 == 0 ? "white" : "#7fe275"%>">
					<td id=id name=id><%=((Mercado) listaMercado.get(i)).getId()%></td>
					<td id=nomeFantasia name=nomeFantasia><%=((Mercado) listaMercado.get(i)).getNomeFantasia()%></td>
					<td id=razaoSocial name=razaoSocial><%=((Mercado) listaMercado.get(i)).getRazaoSocial()%></td>
					<td id=cnpj name=cnpj><%=((Mercado) listaMercado.get(i)).getCnpj()%></td>
					<td id=endereco name=endereco><%=((Mercado) listaMercado.get(i)).getEndereco()%></td>
					<td id=telefone name=telefone><%=((Mercado) listaMercado.get(i)).getTelefone()%></td>
					<td><a
						href="AtualizaMercado?acao=editarMercado&id=<%out.println(((Mercado) listaMercado.get(i)).getId());%>">editar</a></td>
					<td><a
						href="ExclusaoControlador?acao=excluirMercado&id=<%out.println(((Mercado) listaMercado.get(i)).getId());%>">excluir</a></td>
				</tr>
			</form>
			<%
				}
			%>
		</p>
		</table>
	</form>
	<br>
	<a href="AcessoControlador?acao=voltarMenu">Home</a> </article> </section>
</body>
</html>