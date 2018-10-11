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
	<h2 class='text-center' id='pseudoUser'>${currentUser.pseudo}</h2>
	<div class="row" id='profilUser'>
		<div class='col-md-2'>
			 
      		<c:choose>
			  	<c:when test = "${currentUser.photo == null}">
         			<img  class='img-rounded img-responsive' src='/sortie.com/file/utils/defaultimage.jpg'/>
      			</c:when>
			  	<c:otherwise>
			    	<img  class='img-rounded img-responsive' src='/sortie.com/file/upload/${currentUser.photo}'/>
			  	</c:otherwise>
			</c:choose>
			
			<button type='button' class='btn btn-primary' id='modifierPhoto' style='margin-top:10px;'><span class='glyphicon glyphicon-pencil' ></span> &nbsp;Modifier ma photo</button>
		</div>
		<div class='col-md-offset-2 col-md-2'>
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
			<button class='btn btn-info' id='btnModifProfil' style='margin-top:10px;'><span class='glyphicon glyphicon-pencil'></span> &nbsp;Editer mon profil</button>
		</div>
	</div>
	<div class='row' id='modifProfilUser'>
		<div class='col-md-2'>
			<c:choose>
			  	<c:when test = "${currentUser.photo == null}">
         			<img  class='img-rounded img-responsive' src='/sortie.com/file/utils/defaultimage.jpg'/>
      			</c:when>
			  	<c:otherwise>
			    	<img  class='img-rounded img-responsive' src='/sortie.com/file/upload/${currentUser.photo}'/>
			  	</c:otherwise>
			</c:choose>
		</div>
		<div class='col-md-offset-2 col-md-2'>
			<p>Nom : </p>
			<p>Prénom : </p>
			<p>Telephone : </p>
			<p>Mail : </p>
			<p>Ville de rattachement : </p>
		</div>
		<div class='col-md-6'>
			<input class='form-control' type='text' value='${currentUser.nom}' id='modifProfilNom'/>
			<input class='form-control' type='text' value='${currentUser.prenom}' id='modifProfilPrenom'/>
			<input class='form-control' type='text' value='${currentUser.telephone}' id='modifProfilTelephone'/>
			<input class='form-control' type='text' value='${currentUser.mail}' id='modifProfilMail'/>
			<input type="hidden" value='${currentUser.ville.id}' id='currentVilleId'/>
			<select class='form-control' type='text' id='modifProfilVille'>
				
			</select>
		</div>
		<div class='col-md-12'>
			<button type='button' class='btn btn-success' id='saveProfilModif'><span class='glyphicon glyphicon-save'></span> &nbsp;Enregistrer</button>
			<button type='button' class='btn btn-warning' id='cancelProfilModif'><span class='glyphicon glyphicon-remove'></span> &nbsp;Annuler</button>
		</div>
	</div>
	
</div>
<script src='/sortie.com/js/participant.js'>

</script>