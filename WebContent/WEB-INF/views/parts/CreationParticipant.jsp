<%@page import="bo.Participant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Participant currentUser = null;
	if(request.getSession().getAttribute("currentUser") != null){
		currentUser = (Participant)request.getSession().getAttribute("currentUser");
	}else{
		response.sendRedirect("/sortie.com/authentification");
	}
%>
<div class="container" id='creation-participant'>
	<h2 class='text-center'>${currentUser.pseudo}</h2>
	<div class="row" id='profilUser'>
		<div class='col-md-offset-4 col-md-2'>
			<p>Nom : </p>
			<p>Prénom : </p>
			<p>Telephone : </p>
			<p>Mail : </p>
			<p>Ville de rattachement : </p>
		</div>
		<div class='col-md-6'>
			<p>${currentUser.nom}</p>
			<p>${currentUser.prenom}</p>
			<p>${currentUser.telephone}</p>
			<p>${currentUser.mail}</p>
			<p>${currentUser.ville.nomVille}</p>
		</div>
		<div class='col-md-12'>
			<button class='btn btn-info' id='btnModifProfil'><span class='glyphicon glyphicon-pencil'></span> &nbsp;Editer mon profil</button>
		</div>
	</div>
	<div class='row' id='modifProfilUser'>
		<div class='col-md-offset-4 col-md-2'>
			<p>Nom : </p>
			<p>Prénom : </p>
			<p>Telephone : </p>
			<p>Mail : </p>
			<p>Ville de rattachement : </p>
		</div>
		<div class='col-md-6'>
			<input class='form-control' type='text' value='${currentUser.nom}'/>
			<input class='form-control' type='text' value='${currentUser.prenom}'/>
			<input class='form-control' type='text' value='${currentUser.telephone}'/>
			<input class='form-control' type='text' value='${currentUser.mail}'/>
			<input class='form-control' type='text' value='${currentUser.ville.nomVille}'/>
		</div>
		<div class='col-md-12'>
			<button type='button' class='btn btn-success' id='saveProfilModif'><span class='glyphicon glyphicon-save'></span> &nbsp;Enregistrer</button>
		</div>
	</div>
	
</div>
<script src='/sortie.com/js/participant.js'></script>