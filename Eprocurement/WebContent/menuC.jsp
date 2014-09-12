<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>B2B Cotação Perfil Colaborador</title>
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

label {
	padding-right: 20px;
}

#btnLimpar {
	float: none;
}
</style>
</head>
<body>
<jsp:useBean id="isColaborador" class="java.lang.String"
		scope="request"></jsp:useBean>
<jsp:useBean id="escondeMenu" class="java.lang.String"
		scope="request"></jsp:useBean>
	<header> <img src="imagens/sistema.jpg"> <hgroup>
	<nav>
	<ul>
		<li><a class="nav" href="AcessoControlador?acao=voltarMenu">Home</a></li>
		<br>

		<li><a class="nav" href="ListaMercado" <%="0".equals(request.getAttribute("isColaborador")) ? "hidden=true" : "" %>>Cadastro de mercados</a></li>
		<br>
		<li><a class="nav" href="ListaColaborador" <%out.println("0".equals(request.getAttribute("isColaborador")) ? "hidden=true" : ""); %>>Cadastro de
				colaboradores</a></li>
		<br>
		<li><a class="nav" href="ListaProduto" <%out.println("0".equals(request.getAttribute("isColaborador")) ? "hidden=true" : ""); %>>Cadastro de produtos</a></li>
		<br>
		<li><a class="nav" href="ListaFornecedor">Cadastro de
				fornecedores</a></li>
		<br>
		<li><a class="nav" href="ListaVendedor">Cadastro de
				vendedores</a></li>
		<br>
		<li><a class="nav" href="ListaUsuario" <%out.println("0".equals(request.getAttribute("isColaborador")) ? "hidden=true" : ""); %>>Usuários</a></li>
		<br>
		<li><a class="nav" href="CotacaoControlador?acao=novaCotacao">Cotações</a></li>
		<br>
		<li><a class="nav" href="EnviarControlador?acao=abreEnviar" <%out.println("0".equals(request.getAttribute("isColaborador")) ? "hidden=true" : ""); %>>Enviar cotações</a></li>
		<br>
		<li><a class="nav" href="ListaResposta?acao=responder">Responder
				cotações</a></li>
		<br>
		<li><a class="nav" href="EncerraControlador?acao=abreEncerrar" <%out.println("0".equals(request.getAttribute("isColaborador")) ? "hidden=true" : ""); %>>Encerrar
				cotações</a></li>
		<br>
	</ul>
	</nav> </hgroup> </header>
</body>
</html>

