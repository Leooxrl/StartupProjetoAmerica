<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.eprocurement.entities.Colaborador"%>

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

	<jsp:useBean id="listaColaborador" class="java.util.ArrayList"
		scope="request"></jsp:useBean>
	<header> <hgroup>

	<h2>B2B NEGOCIAÇÃO DE PREÇOS ENTRE SUPERMERCADISTAS E FORNECEDORES</h2>
	<ul>
		<li><a class="nav" href="colaboradores.jsp">Novo Colaborador</a></li>
	</ul>
	</hgroup> </header>

	<section> <article>

	<form action="ListaColaborador" method="post">
		<h1>Colaboradores</h1>
		<input type=text id=acao name=acao value="listarColaboradores"
			hidden=true>
		<p>	
		<table>
			<tr "left" bgcolor="#7fe275">
				<th>código</th>
				<th>nome</th>
				<th>e-mail</th>
				<th>mercado</th>
				<th>editar</th>
				<th>excluir</th>
			</tr>

			<%
				for (int i = 0; i < listaColaborador.size(); i++) {
			%>

			<tr "left" bgcolor="<%=i % 2 == 0 ? "white" : "#7fe275"%>">
				<td><%=((Colaborador) listaColaborador.get(i)).getId()%></td>
				<td><%=((Colaborador) listaColaborador.get(i)).getNome()%></td>
				<td><%=((Colaborador) listaColaborador.get(i)).getEmail()%></td>
				<td><%=((Colaborador) listaColaborador.get(i)).getMercado()
						.getNomeFantasia()%></td>
				<td><a
					href="AtualizaColaborador?acao=editarColaborador&id=<%out.println(((Colaborador) listaColaborador.get(i)).getId());%>">editar</a></td>
				<td><a
					href="ExclusaoControlador?acao=excluirColaborador&id=<%out.println(((Colaborador) listaColaborador.get(i)).getId());%>">excluir</a></td>
			</tr>

			<%
				}
			%>

		</table>
		</p>
	</form>
	<br>
	<a href="AcessoControlador?acao=voltarMenu">Home</a> </article> </section>
</body>
</html>