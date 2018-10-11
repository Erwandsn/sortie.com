<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container-fluid" id="gestion-ville">
	<div class='row'>
		<div class='col-md-6 col-md-offset-3'>
			<div class="row form-group">
				<div class="col-lg-6 col-md-6 col-xs-6">
					<input class="form-control" id="hier" aria-describedby="hier"
						placeholder="Nom">
				</div>
				<form action='' method='POST' class='form-inline'>
					<div class='form-group'>
						<button type='button' id='rechercher' class='btn btn-primary'>Rechercher</button>
					</div>
				</form>
			</div>
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
			<div id="btn-modification">
				<div class='col-md-4'>
					<button type='button' class='btn btn-warning btn-block'
						id='btnModifVille'>Modifier</button>
				</div>
				<div class='col-md-4'>
					<button type='button' class='btn btn-danger btn-block'
						id='btnSupprVille'>Supprimer</button>
				</div>
				<div class='col-md-4'>
					<button type='button' class='btn btn-success btn-block'
						id='submitAddVille'>Ajouter</button>
				</div>
			</div>
			<div id="btn-validation" class="col-md-6">
				<div class='col-md-6'>
					<form action='' method='POST'>
						<button type='button' id='btnValiderVille'
							class='btn btn-success col-md-12'>Valider</button>
					</form>
				</div>
				<div class='col-md-6 '>
					<button type='button' id='btnAnnulerVille'
						class='btn btn-danger col-md-12'>Annuler</button>
				</div>
			</div>
		</div>
	</div>
	<div class='row'></div>
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