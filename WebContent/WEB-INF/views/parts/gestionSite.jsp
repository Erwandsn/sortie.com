<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div id='gestionSite'>
	<h2>Gestion de site</h2>
	<div class='row'>
		<div class='col-md-8 col-md-offset-2'>
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
		<div class='col-md-8 col-md-offset-2'>
			<table class='table table-striped'>
				<thead>
					<tr>
						<th>#</th>
						<th>Nom du site</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody id='tableSite'>
				
				</tbody>
			</table>
		</div>
	</div>
</div>