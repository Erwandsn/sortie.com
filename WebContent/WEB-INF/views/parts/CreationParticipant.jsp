<div class="container">
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="pseudo" aria-describedby="pseudoHelp" placeholder="Pseudo">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="prenom" aria-describedby="prenomHelp" placeholder="Prénom">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input  class="form-control" id="nom" aria-describedby="nomHelp" placeholder="Nom">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input  class="form-control" id="telephone" aria-describedby="telephoneHelp" placeholder="Téléphone">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input  class="form-control" id="email" aria-describedby="emailHelp" placeholder="Email">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input  class="form-control" id="mot-de-passe" aria-describedby="motDePasseHelp" placeholder="Mot de passe">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="confirmation" aria-describedby="confirmationHelp" placeholder="Confirmation">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="ville" aria-describedby="villeHelp" placeholder="Ville">
		</div>
	</div>
	<div class="row form-group">
		<form action='' method='POST'>
			<div class='form-group'>
				<button type='button' id='rechercher' class='btn btn-primary'>Enregistrer</button>
			</div>
		</form>
		<div class="col-lg-4 col-md-4 col-xs-4">
			<button type="button" class="btn btn-primary" id="btnAnnuler" onClick="annuler()">Annuler</button>
		</div>
	</div>
</div>
<script src='/sortie.com/js/participant.js'></script>