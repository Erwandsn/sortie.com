<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div id='gestionSite'>
	<h2>Gestion de site</h2>
	<div class='row'>
		<div class='col-md-6 col-md-offset-3'>
			<form action='' method='POST' class='form-inline'>
				<div class='form-group'>
					<label class='label-control' for='nomSite'>Nom du site</label>
					<input class='form-control' name='nomSite' id='addNomSite' />
					<button type='button' id='submitAddSite' class='btn btn-success'>Ajouter</button>
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
						<th>Nom du site</th>
					</tr>
				</thead>
				<tbody id='tableSite'>
				</tbody>
			</table>
			<div class='col-md-offset-2 col-md-4'>
				<button type='button' class='btn btn-warning btn-block' id='btnModifSite'>Modifier</button>	
			</div>
			<div class='col-md-4'>
				<button type='button' class='btn btn-danger btn-block' id='btnSupprSite'>Supprimer</button>
			</div>
		</div>
	</div>
	<div class='row' id='modifSiteArea'>
		<div class='col-md-6 col-md-offset-3'>
			<h3>Modification site <span id='modifSiteIdArea'></span></h3>
			<label class='label-control'>Nom du site</label>
			<input class='form-control' id='modifSiteName' type='text'/>
			<div class='col-md-6'>
				<button type='button' class='btn btn-info btn-block' id='saveModifSite'><span class='glyphicon glyphicon-save'></span> Enregistrer</button>
			</div>
			<div class='col-md-6'>
				<button type='button' class='btn btn-warning btn-block' id='cancelModifSite'><span class='glyphicon glyphicon-remove'></span> Annuler</button>
			</div>
		</div>
	</div>
</div>