<div class="container-fluid" id="accueil">
	<div class="row form-group">
		<div class="col-lg-1 col-md-1 col-xs-1">Site</div>
		<div class="col-lg-4 col-md-4 col-xs-4">
			<select class="form-control" id="select-site" aria-describedby="select-site-help"></select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-2 col-md-2 col-xs-2">Le nom de la sortie contient:</div>
		<div class="col-lg-2 col-md-2 col-xs-2">
			<input class="form-control" id="recherche"
				aria-describedby="recherche" placeholder="Recherche">
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2">
			<div class="form-check">
				<input type="checkbox" class="form-check-input"
					id="sortie-organisateur"> <label class="form-check-label"
					for="sortie-organisateur">Sorties dont je suis
					l'organisateur/trice.</label>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2">
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="sortie-inscrit">
				<label class="form-check-label" for="sortie-inscrit">Sorties
					auxquelles je suis inscrit/e.</label>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2">
			<div class="form-check">
				<input type="checkbox" class="form-check-input"
					id="sortie-pas-inscrit"> <label class="form-check-label"
					for="sortie-pas-inscrit">Sorties auxquelles je ne suis pas
					inscrit/e.</label>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2">
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="sortie-passee">
				<label class="form-check-label" for="sortie-passee">Sorties
					passées.</label>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2">
			<button type="button" class="btn btn-primary" id="btnAccueilRecherche">Rechercher</button>
		</div>
	</div>
	<div class="row form-group">
		<div class='col-lg-2 col-md-2 col-xs-2'>
			<div class='input-group date' id='debut'>
				<input type='text' class="form-control"
					placeholder="Début" id="sortie-debut" /> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
		<div class='col-lg-4 col-md-4 col-xs-4'>
			<div class='input-group date' id='fin'>
				<input type='text' class="form-control" placeholder="Fin"  id="sortie-fin"/> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	<div class="row form-group">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Nom de la sortie</th>
					<th scope="col">Date de la sortie</th>
					<th scope="col">Cloture</th>
					<th scope="col">Inscrits/ places</th>
					<th scope="col">Etat</th>
					<th scope="col">Inscrit</th>
					<th scope="col">Organisateur</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody id='listeSorties'>
				
			</tbody>
		</table>
	</div>
	<div class="row form-group">
		<div class="col-lg-4 col-md-4 col-xs-4">
			<button type="button" class="btn btn-primary" id="btnCreationSortie">Créer une
				sortie</button>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-4 col-md-4 col-xs-4">
			<button type="button" class="btn btn-primary" id="btnModificationSortie">Modifier une sortie</button>
		</div>
	</div>
</div>