<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>B2B Cotação Resposta</title>
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

<jsp:useBean id="listaItensCotacaoVendedor" class="java.util.ArrayList"
		scope="request"></jsp:useBean>
<jsp:useBean id="listaPrecosCotacaoVendedor" class="java.util.ArrayList"
		scope="request"></jsp:useBean>

<jsp:useBean id="idVendedor" class="java.lang.String"
		scope="request"></jsp:useBean>
<jsp:useBean id="idCotacao" class="java.lang.String"
		scope="request"></jsp:useBean>
<jsp:useBean id="nomeFantasia" class="java.lang.String"
		scope="request"></jsp:useBean>
<jsp:useBean id="dataTermino" class="java.lang.String"
		scope="request"></jsp:useBean>		

	<header> <hgroup>

	<h2>B2B NEGOCIAÇÃO DE PREÇOS ENTRE SUPERMERCADISTAS E FORNECEDORES</h2>
	</hgroup> </header>

	<section> <article>
		<h1>Resposta cotação <%out.println(request.getAttribute("idCotacao")); %></h1>
		<h3> validade: <% out.println(request.getAttribute("dataTermino")); %></h3>
		<h2>Supermercado: <%= request.getAttribute("nomeFantasia") %></h2>
		<h3>Itens da cotação</h3>
	<%
			for (int i = 0; i < listaItensCotacaoVendedor.size(); i++) {
	%>
	<form method="post" action="RespostaControlador?acao=responderCotacaoVendedor&idCotacao=<%out.println(request.getAttribute("idCotacao")); %>&i=<%out.println(i);%>&id=<%out.println(((List<Object>) listaItensCotacaoVendedor.get(i)).get(4));%> ">
		<table width="100%">
		
			<tr align="left" bgcolor="#7fe275">
				<th>Quantidade</th>
				<th>Descrição</th>
				<th>Preço</th>
				<th>Responder</th>
			</tr>
			
			<tr "left" bgcolor="<%=i % 2 == 0 ? "white" : "#7fe275"%>">
				<td><% out.println(((List<Object>) listaItensCotacaoVendedor.get(i)).get(1)); %> </td>
				<td><% out.println(((List<Object>) listaItensCotacaoVendedor.get(i)).get(3)); %></td>
			
				<td><label> <input type=number id=precoInput<%out.println(i);%>
						name=precoInput<%out.println(i);%> value=<%
								for (int j = 0; j < listaPrecosCotacaoVendedor.size(); j++) {
									
									if(((List<Object>) listaItensCotacaoVendedor.get(i)).get(4) == ((List<Object>) listaPrecosCotacaoVendedor.get(j)).get(1)){
										out.println(((List<Object>) listaPrecosCotacaoVendedor.get(j)).get(0));
									} else{
										out.println("");
									}
								}
						%> required>
				</label></td>
				<td>		
				<input type="submit" name="btnResponder" value="Responder" id="btnResponder">
				</td>
			</tr>
		</table>
	</form>
			<%
					
				}
			%>

	<br>
	<a href="AcessoControlador?acao=voltarMenu">Home</a> </article> </section>
	</article>
	</section>

</body>
</html>