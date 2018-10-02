<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src='/sortie.com/vendor/jquery/jquery.js'></script>
	<script type="text/javascript" src='/sortie.com/vendor/bootstrap/js/bootstrap.js'></script>
	<link rel='stylesheet' type='text/css' href='/sortie.com/vendor/bootstrap/css/bootstrap.css'/>
	<meta charset="ISO-8859-1">
	<title>Gestion de sortie</title>
</head>
<body>
<div class='container'>
	<%@include file="parts/navbar.jsp" %>
	
	<div id='gestionVille'>
		<h2>Gestion de ville</h2>
	</div>
	<%@include file="parts/gestionSite.jsp" %>
</div>

<script src='/sortie.com/js/sortie.js'></script>
</body>
</html>