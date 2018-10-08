<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container-fluid" id="modification-sortie">
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="modification-sortie-nom"
				aria-describedby="modification-sortie-nom" placeholder="Nom">
		</div>
	</div>
	<div class="row form-group">
		<div class='col-lg-6 col-md-6 col-xs-6'>
			<div class='input-group date' id='modification-sortie-date'>
				<input type='text' class="form-control"
					placeholder="Date et heure de sortie" /> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	<div class="row form-group">
		<div class='col-lg-6 col-md-6 col-xs-6'>
			<div class='input-group date' id='modification-sortie-date-inscription'>
				<input type='text' class="form-control"
					placeholder="Date limite d'inscription" /> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="modification-sortie-place"
				aria-describedby="modification-sortie-place" placeholder="Nombre de place">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="modification-sortie-duree"
				aria-describedby="modification-sortie-duree" placeholder="Durée">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<textarea class="form-control" rows="5" id="modification-sortie-description" placeholder="Description et infos"></textarea>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Ville organisatrice: <div id="modification-sortie-ville-lbl"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<select class="form-control" id="modification-sortie-ville" aria-describedby="modification-sortie-ville-help"
				placeholder="Ville" required>
			</select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<select class="form-control" id="modification-sortie-lieu" aria-describedby="modification-sortie-Lieu-help"
				placeholder="Lieu" required>
			</select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Rue: <div id="modification-sortie-ville-lbl"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Code postal: <div id="modification-sortie-code-postal-lbl"></div></div>
	</div>
	<div class="row form-group col-lg-8 col-md-8 col-xs-8">
		<div class='col-lg-3 col-md-3 col-xs-3'>
			<form action='' method='POST'>
				<button type='button' class='btn btn-success btn-block'
					id='btnEnregModifSortie'>Enregistrer</button>
			</form>
		</div>
		<div class='col-lg-3 col-md-3 col-xs-3'>
			<button type='button' class='btn btn-primary btn-block'
				id='btnPublModifSortie'>Publier la sortie</button>
		</div>
		<div class='col-lg-3 col-md-3 col-xs-3'>
			<button type='button' class='btn btn-danger btn-block'
				id='btnSuppModifSortie'>Supprimer</button>
		</div>
		<div class='col-lg-3 col-md-3 col-xs-3'>
			<button type='button' class='btn btn-warning btn-block'
				id='btnAnnulModifSortie'>Annuler</button>
		</div>
	</div>
</div>
