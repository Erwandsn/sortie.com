<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container-fluid" id="annulation-sortie">
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Nom: <div id="annulation-sortie-nom"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Date de la sortie: <div id="annulation-sortie-date"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Ville organisatrice: <div id="annulation-sortie-ville"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Lieu: <div id="annulation-sortie-lieu"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<textarea class="form-control" rows="5" id="annulation-sortie-motif" placeholder="Motif"></textarea>
		</div>
	</div>
	<div class="row form-group col-lg-8 col-md-8 col-xs-8">
		<div class='col-lg-3 col-md-3 col-xs-3'>
			<form action='' method='POST'>
				<button type='button' class='btn btn-success btn-block'
					id='btnEnregAnnulSortie'>Enregistrer</button>
			</form>
		</div>
		<div class='col-lg-3 col-md-3 col-xs-3'>
			<button type='button' class='btn btn-warning btn-block'
				id='btnAnnulAnnulSortie'>Annuler</button>
		</div>
	</div>
</div>