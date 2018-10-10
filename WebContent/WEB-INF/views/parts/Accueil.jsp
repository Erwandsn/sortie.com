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
					pass�es.</label>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2">
			<button type="button" class="btn btn-primary" id="btnAccueilRecherche">Rechercher</button>
		</div>
	</div>
	<div class="row form-group">
		<div class='col-lg-4 col-md-4 col-xs-4'>
			<div class='input-group date' id='debut'>
				<input type='text' class="form-control"
					placeholder="D�but" id="sortie-debut" /> <span
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
					<th></th>
					<th scope="col">Nom de la sortie</th>
					<th scope="col">Date de la sortie</th>
					<th scope="col">Cloture</th>
					<th scope="col">Inscrits/ places</th>
					<th scope="col">Etat</th>
					<th scope="col">Inscrit</th>
					<th scope="col">Organisateur</th>
				</tr>
			</thead>
			<tbody id='listeSorties'>
				
			</tbody>
		</table>
		<div class='row'>
			<div class='col-md-4'>
				<button class='btn btn-info btn-block' id='btnAfficherSortie'><span class="glyphicon glyphicon-zoom-in"></span> Afficher</button>
			</div>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-4 col-md-4 col-xs-4">
			<button type="button" class="btn btn-primary" id="btnCreationSortie">Cr�er une
				sortie</button>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-4 col-md-4 col-xs-4">
			<button type="button" class="btn btn-primary" id="btnModificationSortie">Modifier une sortie</button>
		</div>
	</div>
</div>
<div class='container' id='detailSortie'>
	<div class='row'>
		<div class='col-md-1' id='retourVersListeSortie'>
			<button class='btn btn-info'><span class='glyphicon glyphicon-chevron-left'></span></button>
		</div>
		<h2 id='titleSortie'></h2>
	</div>
	<div class='row'>
		<input type='hidden' id='currentDetailSortieId' value=''/>
		<p>Date de la sortie: <span id='dateDeSortie'></span></p>
		<p>Date de fin d'inscription: <span id='dateFinDinscription'></span></p>
		<p>Inscripts/nombre de place <span id='nbPlacesInscrit'></span></p>
		<p>Organisateur :<span id='organisateurSortie'></span></p>
		<p>Etat :<span id='etatSortie'></span></p>
		<p>Description<p>
		<p id='descriptionSortie'></p>
	</div>
	<div class='row'>
		<div class='col-md-3'>
			<button class='btn btn-success btn-block' id='sinscrireAlaSortie'>S'inscrire</button>
		</div>
		<div class='col-md-3'>
			<button class='btn btn-danger btn-block' id='seDesinscrireAlaSortie'>Se d�sinscrire</button>
		</div>
		<div class='col-md-3'>
			<button class='btn btn-info btn-block' id='modifierMaSortie'><span class='glyphicon glyphicon-pencil'></span> Modifier</button>
		</div>
	</div>
	
</div>