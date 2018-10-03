<div class="container" id=creation-participant>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="pseudo" aria-describedby="pseudoHelp" placeholder="Pseudo" required>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="prenom" aria-describedby="prenomHelp" placeholder="Prénom" required>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input  class="form-control" id="nom" aria-describedby="nomHelp" placeholder="Nom" required>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input  class="form-control" id="telephone" aria-describedby="telephoneHelp" placeholder="Téléphone">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input  class="form-control" id="email" aria-describedby="emailHelp" placeholder="Email" required>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input  class="form-control" id="mot-de-passe" aria-describedby="motDePasseHelp" placeholder="Mot de passe" type="password" required>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="confirmation" aria-describedby="confirmationHelp" placeholder="Confirmation" type="password" required>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<input class="form-control" id="ville" aria-describedby="villeHelp" placeholder="Ville" required>
		</div>
	</div>
	<div class="row form-group col-lg-4 col-md-4 col-xs-4">
		<form action='' method='POST'>
			<div class='col-lg-6 col-md-6 col-xs-6'>
				<button type='button' id='enregistrer' class='btn btn-primary'>Enregistrer</button>
			</div>
		</form>
		<div class="col-lg-6 col-md-6 col-xs-6">
			<button type="button" class="btn btn-primary" id="btnAnnuler">Annuler</button>
		</div>
	</div>
</div>
<script src='/sortie.com/js/participant.js'></script>