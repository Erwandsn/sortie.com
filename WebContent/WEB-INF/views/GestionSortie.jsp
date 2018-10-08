<%@page import="bo.Participant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Participant currentUser = null;
	if(request.getSession().getAttribute("currentUser") != null){ 
		currentUser = (Participant)request.getSession().getAttribute("currentUser");
	}else{
		response.sendRedirect("/sortie.com/authentification");
	}	
%>
<!DOCTYPE html>
<html>
<head>
	<link href="https://fonts.googleapis.com/css?family=K2D" rel="stylesheet">
	<script type="text/javascript" src='/sortie.com/vendor/jquery/jquery.js'></script>
	<script type="text/javascript" src="/sortie.com/vendor/simpleUpload.js"></script>
	<script type="text/javascript" src='/sortie.com/vendor/bootstrap/js/bootstrap.js'></script>
	<link rel='stylesheet' type='text/css' href='/sortie.com/vendor/bootstrap/css/bootstrap.css'/>
	<link rel='stylesheet' type='text/css' href='/sortie.com/css/style.css' />
	<meta charset="ISO-8859-1">
	<title>Gestion de sortie</title>
</head>
<body>
<div class='container col-md-12'>
	<jsp:include page="/WEB-INF/views/parts/navbar.jsp"/>
	<jsp:include page="/WEB-INF/views/parts/confirmSuppr.jsp"/>
	<jsp:include page="/WEB-INF/views/parts/Accueil.jsp"/>
	<c:if test="${currentUser.admin == true}">
		<jsp:include page="/WEB-INF/views/parts/GestionVille.jsp"/>
		<jsp:include page="/WEB-INF/views/parts/gestionSite.jsp"/>
	</c:if>
	<jsp:include page="/WEB-INF/views/parts/CreationParticipant.jsp"/>
</div>
<script src='/sortie.com/js/sortie.js'></script>
</body>
</html>