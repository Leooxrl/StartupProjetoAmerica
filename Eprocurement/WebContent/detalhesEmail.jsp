<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.eprocurement.entities.Produto"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>B2B Cotação Detalhes</title>
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

	<jsp:useBean id="listaItens" class="java.util.ArrayList"
		scope="request"></jsp:useBean>
	<jsp:useBean id="dataValidade" class="java.lang.String"
		scope="request"></jsp:useBean>
		<jsp:useBean id="tela" class="java.lang.String"
		scope="request"></jsp:useBean>	
		
	<jsp:useBean id="listaVendedores" class="java.util.ArrayList"
		scope="request"></jsp:useBean>
	<jsp:useBean id="idCotacao" class="java.lang.String" scope="request"></jsp:useBean>
	<jsp:useBean id="listaPrecosVendedores" class="java.util.ArrayList"
		scope="request"></jsp:useBean>	
	<header> <hgroup>

	<h2>B2B NEGOCIAÇÃO DE PREÇOS ENTRE SUPERMERCADISTAS E FORNECEDORES</h2>
	
	</hgroup> </header>

	<section> <article>


	<h1>Detalhes da cotação <%= request.getAttribute("idCotacao") %></h1>
	<h3>validade: <%= request.getAttribute("dataValidade") %></h3>
	<input type=text id=acao name=acao value="detalhesCotacao" hidden=true>
	
	<h2>Itens</h2>

		<%
			for (int i = 0; i < listaItens.size(); i++) {
		%>
		<h4> 
		 Quantidade: <%out.println(((List<Object>) listaItens.get(i)).get(1)); %> Produto: <%out.println(((List<Object>) listaItens.get(i)).get(2)); %>
		  
		</h4>
		
		<%
				for (int h = 0; h < listaVendedores.size(); h++) {
					
					
		%>
		
		<table>
		<%
					if(((List<Object>) listaItens.get(i)).get(0).equals( ((List<Object>)listaVendedores.get(h)).get(0))) {	
			
		%>
		
		<tr "left" bgcolor="<%=h % 2 == 0 ? "white" : "#7fe275"%>">
			<td><%out.println(((List<Object>)listaVendedores.get(h)).get(1)); %></td>
			
			<td>Preço: <% for (int j = 0; j < listaPrecosVendedores.size(); j++) {
									
							if(((List<Object>) listaVendedores.get(h)).get(2) == ((List<Object>) listaPrecosVendedores.get(j)).get(1)){
										out.println(((List<Object>) listaPrecosVendedores.get(j)).get(0));
							} else{
								out.println("");
							}
						} %>
			</td>
			<td><%out.println(((List<Object>)listaVendedores.get(h)).get(3)); %></td>
			<td><a href="EmailControlador?acao=enviarCompra&idCotacao=<%out.println(request.getAttribute("idCotacao"));%>&email=<%out.println(((List<Object>)listaVendedores.get(h)).get(3)); %>">Enviar solicitação de compra</a></td>
		</tr>
		
			<%
					}
		
		
			
				}
			}
		%>
	</table>		
	<br>
	<a href="<%out.println(tela); %>">voltar</a> </article> </section>
</body>
</html>