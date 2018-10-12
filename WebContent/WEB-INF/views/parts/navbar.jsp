<%@page import="bo.Participant"%>
<%
	/* if(request.getSession().getAttribute("currentUser") != null){ */
		Participant currentUser = null;
	try{
		currentUser = (Participant)request.getSession().getAttribute("currentUser");
	}catch(Exception e){
		response.sendRedirect("/sortie.com/authentification");
	}
%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<img src="./images/sortie3.jpg" >
		</div>
		<ul class="nav navbar-nav">
		<li class="btnNavBar" id="li-accueil"><a href=# id="btnAccueil">Accueil</a></li>
			<% if (currentUser != null){ %>
				<% if(currentUser.getAdmin() == true){  %>
					<li class="btnNavBar" id="li-ville"><a href="#" id='btnVille'>Villes</a></li>
					<li class="btnNavBar" id="li-site"><a href="#" id='btnSite'>Site</a></li>
				<%}%>
			<%}%>
			<li class="btnNavBar" id="li-creation-participant"><div
					class="dropdown">
					<button class="dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false" style="padding:15px;">Profil</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item col-md-12" href="/sortie.com/deconnexion" id="li-creation-participant">Se déconnecter</a> <a
							class="dropdown-item col-md-12" id="btnModifProfil">Modifier profil</a> <a
							class="dropdown-item col-md-12" id="modifierPhoto">Modifier image profil</a>
					</div>
				</div></li>
			<!-- <li class="btnNavBar" id="li-creation-participant"><a href="#" id="btnCreationParticipant" >Mon profil</a></li>
			<li><a href="/sortie.com/deconnexion">Se deconnecter</a></li> -->
		</ul>
	</div>
</nav>

