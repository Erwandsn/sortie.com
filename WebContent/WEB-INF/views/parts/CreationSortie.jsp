<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container-fluid" id="creation-sortie">
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="creation-sortie-nom"
				aria-describedby="creation-sortie-nom" placeholder="Nom">
		</div>
	</div>
	<div class="row form-group">
		<div class='col-lg-6 col-md-6 col-xs-6'>
			<div class='input-group date' id='creation-sortie-date1'>
				<input type='text' class="form-control"
					placeholder="Date et heure de sortie" id='creation-sortie-date1-val'/> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	<div class="row form-group">
		<div class='col-lg-6 col-md-6 col-xs-6'>
			<div class='input-group date' id='creation-sortie-date-inscription'>
				<input type='text' class="form-control"
					placeholder="Date limite d'inscription" id='creation-sortie-date-inscription-val' /> <span
					class="input-group-addon" > <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="creation-sortie-place"
				aria-describedby="creation-sortie-place" placeholder="Nombre de place">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="creation-sortie-duree"
				aria-describedby="creation-sortie-duree" placeholder="Dur�e">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<textarea class="form-control" rows="5" id="creation-sortie-description" placeholder="Description et infos"></textarea>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Ville organisatrice: <div id="creation-sortie-ville-lbl"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<select class="form-control" id="creation-sortie-ville" aria-describedby="creation-sortie-ville-help"
				placeholder="Ville" required>
			</select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<select class="form-control" id="creation-sortie-lieu" aria-describedby="creation-sortie-Lieu-help"
				placeholder="Lieu" required>
			</select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Rue: <div id="creation-sortie-ville-lbl"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Code postal: <div id="creation-sortie-code-postal-lbl"></div></div>
	</div>
	<div class="row form-group col-lg-6 col-md-6 col-xs-6">
		<div class='col-md-4'>
			<form action='' method='POST'>
				<button type='button' class='btn btn-success btn-block'
					id='btnEnregSortie'>Enregistrer</button>
			</form>
		</div>
		<div class='col-md-4'>
			<button type='button' class='btn btn-primary btn-block'
				id='btnPublSortie'>Publier la sortie</button>
		</div>
		<div class='col-md-4'>
			<button type='button' class='btn btn-danger btn-block'
				id='btnAnnulSortie'>Annuler</button>
		</div>
	</div>
</div>