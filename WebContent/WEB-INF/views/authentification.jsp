<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src='/sortie.com/vendor/jquery/jquery.js'></script>
	<script type="text/javascript" src='/sortie.com/vendor/bootstrap/js/bootstrap.js'></script>
	<link rel='stylesheet' type='text/css' href='/sortie.com/vendor/bootstrap/css/bootstrap.css'/>
	<link rel='stylesheet' type='text/css' href='/sortie.com/css/style.css'/>
	<meta charset="ISO-8859-1">
	<title>Sortie | Authentification</title>
</head>
<body>
	<div class='container'>
		<div class='row'>
			<h1>Logo sortie.com</h1>
		</div>
		<div class='row'>
			<div class='col-md-4'>
				<form action='' method='POST' class='form-inline'>
					<div class='form-group'>
						<label class='label-control' for='login'>Identifiant :</label>
						<input type='text' name='login' class='form-control' autocomplete='off'/>
					</div>
					<div class='form-group'>
						<label class='label-control' for='mdp'>Mot de passe :</label>
						<input type='password' name='mdp' class='form-control' autocomplete='off'/>
					</div>
					<div class='form-group'>
						<button type='submit' class='btn btn-success'><span class='glyphicon glyphicon-user'></span> Connexion</button>
						<a href='#'>Mot de passe oublié</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>