<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container-fluid" id="affichage-sortie">
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Nom: <div id="affichage-sortie-nom"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Date: <div id="affichage-sortie-date"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Date limite d'inscription<div id="affichage-sortie-date-inscription"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Duree: <div id="affichage-sortie-duree"></div> min</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Description et infos: <div id="affichage-sortie-description"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Ville organisatrice: <div id="affichage-sortie-ville"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Lieu: <div id="affichage-sortie-lieu"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Rue: <div id="affichage-sortie-ville-lbl"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Code postal: <div id="affichage-sortie-code-postal-lbl"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Latitude: <div id="affichage-sortie-latitude"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Longitude: <div id="affichage-sortie-longitude"></div></div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Affichage des sorties:</div>
	</div>
	<div class="row form-group">
		<table class='table table-striped'>
			<thead>
				<tr>
					<th>#</th>
					<th>Pseudo</th>
					<th>Nom</th>
				</tr>
			</thead>
			<tbody id='table-afficher-sortie'>
			</tbody>
		</table>
	</div>
</div>