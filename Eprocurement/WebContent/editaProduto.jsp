<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="br.com.eprocurement.entities.Produto"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>B2B Cota��o Produtos</title>
<meta charset="utf-8">
<meta name="description" content="Cota��o" />
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

#acao {
	display: none;
}
</style>
</head>
<body>
	<header> <hgroup>

	<h2>B2B NEGOCIA��O DE PRE�OS ENTRE SUPERMERCADISTAS E FORNECEDORES
		v1</h2>
	</hgroup> </header>

	<section> <article>
	<form action="AtualizaProduto" method="post">
		<%
	Produto produto = (Produto) request.getAttribute("produto");
	 %>
		<h1>Produtos</h1>
		<input type=text id=acao name=acao value="editarProduto" hidden=true>

		<input type=number id=idInput name=idInput hidden=true
			value="<%=produto.getId()%>" required>

		<p>
			<label> <span>descri��o</span><input type=text
				id=descricaoInput name=descricaoInput
				value="<%=produto.getDescricao()%>" required></label> 
		</p>

		<p>
			<input type="submit" name=btnGravar id=btnGravar value="Gravar">
			<input type="reset" name="btnLimpar" value="Limpar" id="btnLimpar">
		</p>
		<br> <a href="AcessoControlador?acao=voltarMenu">Home</a>
	</article> </section>
	</form>
	</article>
	</section>

</body>
</html>