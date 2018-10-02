<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src='<% out.print(request.getContextPath()); %>/vendor/jquery/jquery.js'></script>
	<script type="text/javascript" src='<% out.print(request.getContextPath()); %>/vendor/bootstrap/js/bootstrap.js'></script>
	<link rel='stylesheet' type='text/css' href='<% out.print(request.getContextPath()); %>/vendor/bootstrap/css/bootstrap.css'/>
	<meta charset="ISO-8859-1">
	<title>Gestion de sortie</title>
</head>
<body>
<div class='container col-md-12'>
	<%@include file="parts/navbar.jsp" %>
	<%@include file="Accueil.jsp" %>
	
	
	<div id='gestionVille'>
		<h2>Gestion de ville</h2>
		<%@include file="GestionVille.jsp" %>
	</div>
	
	
	
	<div id='gestionSite'>
		<h2>Gestion de site</h2>
	</div>
</div>

<script src='<% out.print(request.getContextPath()); %>/js/sortie.js'></script>
</body>
</html>