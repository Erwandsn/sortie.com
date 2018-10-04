<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container-fluid" id="gestion-ville">
	<div id="first-content">
		<div class="row form-group">
			<div class="col-lg-1 col-md-1 col-xs-1">Filtrer les villes</div>
		</div>
		<div class="row form-group">
			<div class="col-lg-2 col-md-2 col-xs-2">Le nom contient:</div>
			<div class="col-lg-2 col-md-2 col-xs-2">
				<input class="form-control" id="hier" aria-describedby="hier"
					placeholder="Hier">
			</div>
			<form action='' method='POST' class='form-inline'>
				<div class='form-group'>
					<button type='button' id='rechercher' class='btn btn-primary'>Rechercher</button>
				</div>
			</form>
		</div>
	</div>
	<div class='row'>
		<div class='col-md-6 col-md-offset-3'>
			<table class='table table-striped'>
				<thead>
					<tr>
						<th></th>
						<th>#</th>
						<th>Ville</th>
						<th>Code postal</th>
					</tr>
				</thead>
				<tbody id='table-ville'>
				</tbody>
			</table>
			<div class='col-md-4'>
				<button type='button' class='btn btn-warning btn-block'
					id='btnModifVille'>Modifier</button>
			</div>
			<div class='col-md-4'>
				<button type='button' class='btn btn-danger btn-block'
					id='btnSupprVille'>Supprimer</button>
			</div>
			<div class='col-md-4'>
				<button type='button' class='btn btn-success btn-block' id='submitAddVille'>Ajouter</button>
			</div>
		</div>
	</div>
	<div class='row'>
		<div class='col-md-6 '>
			<form action='' method='POST' class='form-inline'>
				<div class='form-group'>
					<button type='button' id='btnValiderVille' class='btn btn-primary'>Valider</button>
				</div>
			</form>
		</div>
	</div>
	<div class='row' id='modifVilleArea'>
		<div class='col-md-6 col-md-offset-3'>
			<h3>
				Modification ville <span id='modifVilleIdArea'></span>
			</h3>
			<label class='label-control'>Nom du ville</label> <input
				class='form-control' id='modifVilleName' type='text' />
			<div class='col-md-6'>
				<button type='button' class='btn btn-info btn-block'
					id='saveModifVille'>
					<span class='glyphicon glyphicon-save'></span> Enregistrer
				</button>
			</div>
			<div class='col-md-6'>
				<button type='button' class='btn btn-warning btn-block'
					id='cancelModifVille'>
					<span class='glyphicon glyphicon-remove'></span> Annuler
				</button>
			</div>
		</div>
	</div>
</div>
<script src='/sortie.com/js/ville.js'></script>