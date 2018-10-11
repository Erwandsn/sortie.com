<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%

		Boolean auth = false;
		String login = "";
		String mdp = "";
		if(request.getAttribute("login") != null && request.getAttribute("mdp") != null){
		 	login = (String)request.getAttribute("login");
		 	mdp = (String)request.getAttribute("mdp");
			auth = true;
		}
	%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=K2D"
	rel="stylesheet">
<script type="text/javascript" src='/sortie.com/vendor/jquery/jquery.js'></script>
<script type="text/javascript"
	src='/sortie.com/vendor/bootstrap/js/bootstrap.js'></script>
<link rel='stylesheet' type='text/css'
	href='/sortie.com/vendor/bootstrap/css/bootstrap.css' />
<link rel='stylesheet' type='text/css' href='/sortie.com/css/style.css' />
<meta charset="ISO-8859-1">
<title>Sortie | Authentification</title>
</head>
<body>
	<div class="card card-2 col-lg-3 col-md-3 col-xs-3">
		<div class='container-fluid'>
			<div class='row'>
			 <img src="./images/sortie3.jpg" >
				<!-- <div class="logo col-lg-6 col-md-6- col-xs-6"></div> -->
			</div>
			<div id='authentification' class="col-lg-12 col-md-12 col-xs-12">
				<div class='col-lg-12 col-md-12 col-xs-12'>
					<form action='' method='POST' class=''>
						<div class='row col-lg-12 col-md-12 col-xs-12'>
							<div class='form-group col-lg-12 col-md-12 col-xs-12'>
								<input type='text' name='login' class='form-control' autocomplete='off' value="${login}" />
							</div>
						</div>
						<div class='row col-lg-12 col-md-12 col-xs-12'>
							<div class='form-group col-lg-12 col-md-12 col-xs-12'>
								 <input type='password' name='mdp' class='form-control' autocomplete='off' value="${mdp}"/>
							</div>
						</div>
						<div class='row col-lg-12 col-md-12 col-xs-12'>
							<div class='form-group col-lg-12 col-md-12 col-xs-12'>
								<button type='submit' class='btnConnexion'>
									<span class='glyphicon glyphicon-user'></span> Connexion
								</button>
								<a href='#'>Mot de passe oublié</a> <a href='#'
									id='inscriptionBtn'>S'inscrire</a>
							</div>
						</div>
					</form>
				</div>

			</div>
			<div class="container" id=creation-participant>
				<h1>Inscription</h1>
				<div class="row form-group">
					<div class="col-lg-6 col-md-6 col-xs-6">
						<input class="form-control" id="pseudo"
							aria-describedby="pseudoHelp" placeholder="Pseudo" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-lg-6 col-md-6 col-xs-6">
						<input class="form-control" id="prenom"
							aria-describedby="prenomHelp" placeholder="Prénom" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-lg-6 col-md-6 col-xs-6">
						<input class="form-control" id="nom" aria-describedby="nomHelp"
							placeholder="Nom" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-lg-6 col-md-6 col-xs-6">
						<input class="form-control" id="telephone"
							aria-describedby="telephoneHelp" placeholder="Téléphone">
					</div>
				</div>
				<div class="row form-group">
					<div class="col-lg-6 col-md-6 col-xs-6">
						<input class="form-control" id="email"
							aria-describedby="emailHelp" placeholder="Email" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-lg-6 col-md-6 col-xs-6">
						<input class="form-control" id="mot-de-passe"
							aria-describedby="motDePasseHelp" placeholder="Mot de passe"
							type="password" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-lg-6 col-md-6 col-xs-6">
						<input class="form-control" id="confirmation"
							aria-describedby="confirmationHelp" placeholder="Confirmation"
							type="password" required>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-lg-6 col-md-6 col-xs-6">
						<select class="form-control" id="ville"
							aria-describedby="villeHelp" placeholder="Ville" required>

						</select>
					</div>
				</div>
				<div class="row form-group col-lg-4 col-md-4 col-xs-4">
					<form action='' method='POST'>
						<div class='col-lg-6 col-md-6 col-xs-6'>
							<button type='button' id='enregistrer' class='btn btn-primary'>Enregistrer</button>
						</div>
					</form>
					<div class="col-lg-6 col-md-6 col-xs-6">
						<button type="button" class="btn btn-primary" id="btnAnnuler">Annuler</button>
					</div>
				</div>
			</div>
			<jsp:include page="/WEB-INF/views/parts/confirmationInscription.jsp" />
		</div>
		<script type='text/javascript' src='/sortie.com/js/inscription.js'>

		</script>
	</div>
</body>
</html>