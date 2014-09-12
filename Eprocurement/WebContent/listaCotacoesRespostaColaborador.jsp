<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.eprocurement.entities.Cotacao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>B2B Cotação Resposta Vendedor</title>
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

	<jsp:useBean id="listaCotacao" class="java.util.ArrayList"
		scope="request"></jsp:useBean>
		
	<header> <hgroup>
	<%
 	if (listaCotacao == null) {
 		listaCotacao = new ArrayList<Cotacao>();
 	}
 %>

	<h2>B2B NEGOCIAÇÃO DE PREÇOS ENTRE SUPERMERCADISTAS E FORNECEDORES</h2>
	</hgroup> </header>

	<section> <article>
	<form method="post" action="ResponderControlador">
		<h1>Responder cotações</h1>
		<p>
		<table>
			<tr align="left" bgcolor="#7fe275">
				<th>Código</th>
				<th>Responder</th>
			</tr>
			
			<%
				for (int i = 0; i < listaCotacao.size(); i++) {
			%>
			<tr align="left">
				<td><%= ((Cotacao) listaCotacao.get(i)).getId() %></td>
				<td><a href="RespostaControlador?acao=abrirCotacaoColaborador&idCotacao=<%out.println(((Cotacao) listaCotacao.get(i)).getId()); %>">Responder Cotação</a></td>
				</label></td>
			</tr>
			<%
				}
			%>
		</table>
		</p>
		<br>
	</form>
	<br>
	<a href="AcessoControlador?acao=voltarMenu">Home</a> </article> </section>
	</article>
	</section>
</body>
</html>