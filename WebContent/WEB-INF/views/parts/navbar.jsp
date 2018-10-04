<%@page import="bo.Participant"%>
<%
	Participant currentUser = null;
	if(request.getSession().getAttribute("currentUser") != null){
		currentUser = (Participant)request.getSession().getAttribute("currentUser");
	}else{
		response.sendRedirect("/sortie.com/authentification");
	}
%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Sortie.com</a>
		</div>
		<ul class="nav navbar-nav">
		<li class="active" id="li-accueil"><a href=# id="btnAccueil">Accueil</a></li>
			<% if(currentUser.getAdmin() == true){  %>
				<li class="" id="li-ville"><a href="#" id='btnVille'>Villes</a></li>
				<li class="" id="li-site"><a href="#" id='btnSite'>Site</a></li>
			<%} %>
			<li class="" id="li-creation-participant"><a href="#" id="btnCreationParticipant" >Mon profil</a></li>
			<li><a href="/sortie.com/deconnexion">Se deconnecter</a></li>
		</ul>
	</div>
</nav>