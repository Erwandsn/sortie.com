<div class="container-fluid" id="accueil">
	<div class="row form-group">
		<div class="col-lg-2 col-md-2 col-xs-2">
			<select class="form-control" id="select-site"
				aria-describedby="select-site-help"></select>
		</div>
		<div class="multiselect col-lg-4 col-md-4 col-xs-4">
			<div class="selectBox form-control" onclick="showCheckboxes()">
				<select>
					<option>Filtres</option>
				</select>
				<div class="overSelect"></div>
			</div>
			<div id="checkboxes">
				<label for="one"><input type="checkbox"
					class="form-check-input" id="sortie-organisateur"> <label
					class="form-check-label" for="sortie-organisateur">Sorties
						dont je suis l'organisateur/trice.</label> </label> <label for="two"><input
					type="checkbox" class="form-check-input" id="sortie-inscrit">
					<label class="form-check-label" for="sortie-inscrit">Sorties
						auxquelles je suis inscrit/e.</label> </label> <label for="four"><input
					type="checkbox" class="form-check-input" id="sortie-pas-inscrit">
					<label class="form-check-label" for="sortie-pas-inscrit">Sorties
						auxquelles je ne suis pas inscrit/e.</label> </label> <label for="five"> <input
					type="checkbox" class="form-check-input" id="sortie-pas-inscrit">
					<label class="form-check-label" for="sortie-pas-inscrit">Sorties
						auxquelles je ne suis pas inscrit/e.</label>
				</label>
			</div>
		</div>
		<div class='col-lg-2 col-md-2 col-xs-2'>
			<div class='input-group date' id='debut'>
				<input type='text' class="form-control" placeholder="D�but"
					id="sortie-debut" /> <span class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
		<div class='col-lg-2 col-md-2 col-xs-2'>
			<div class='input-group date' id='fin'>
				<input type='text' class="form-control" placeholder="Fin"
					id="sortie-fin" /> <span class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2">
			<button type="button" class="btn" id="btnAccueilRecherche">Rechercher</button>
		</div>
	</div>

	<div class="row form-group"></div>
	<div class="row form-group"></div>
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
		<div class='row col-lg-4 col-md-4 col-xs-4'>
			<div class='col-lg-4 col-md-4 col-xs-4'>
				<button class='btn btn-info btn-block' id='btnAfficherSortie'>Afficher</button>
			</div>
			<div class="col-lg-4 col-md-4 col-xs-4">
				<button type="button" class="btn btn-primary" id="btnCreationSortie">Cr�er
					une sortie</button>
			</div>
		</div>
	</div>
</div>
<div class='container' id='detailSortie'>
	<input type='hidden' id='currentSortie'/>
	<div class='row'>
		<div class='col-md-1' id='retourVersListeSortie'>
			<button class='btn btn-info'>
				<span class='glyphicon glyphicon-chevron-left'></span>
			</button>
		</div>
		<h2 id='titleSortie'></h2>
	</div>
	<div class='row'>
		<input type='hidden' id='currentDetailSortieId' value='' />
		<p>
			Date de la sortie: <span id='dateDeSortie'></span>
		</p>
		<p>
			Date de fin d'inscription: <span id='dateFinDinscription'></span>
		</p>
		<p>
			Inscripts/nombre de place <span id='nbPlacesInscrit'></span>
		</p>
		<p>
			Organisateur :<span id='organisateurSortie'></span>
		</p>
		<p>
			Etat :<span id='etatSortie'></span>
		</p>
		<p>Description
		<p>
		<p id='descriptionSortie'></p>
	</div>
	<div class='row'>
		<div class='col-md-3'>
			<button class='btn btn-success btn-block' id='sinscrireAlaSortie'>S'inscrire</button>
		</div>
		<div class='col-md-3'>
			<button class='btn btn-danger btn-block' id='seDesinscrireAlaSortie'>Se
				d�sinscrire</button>
		</div>
		<div class='col-md-3'>
			<button class='btn btn-info btn-block' id='modifierMaSortie'>
				<span class='glyphicon glyphicon-pencil'></span> Modifier
			</button>
		</div>
	</div>
	<!-- Modal -->
	<div id="listeParticipantsSortie" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Partcipants a la sortie</h4>
				</div>
				<div class="modal-body" id="modalParticipants">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div class='container' id='ModificationSortie'>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
		<label class='label-control'>Nom</label>
			<input class="form-control" id="modifSortieNom"
				aria-describedby="creation-sortie-nom" placeholder="Nom" value="">
		</div>
	</div>
	<div class="row form-group">
		<div class='col-lg-6 col-md-6 col-xs-6'>
			<div class='input-group date' id='creation-sortie-date1'>
			<label class='label-control'>Date de sortie</label>
				<input type='text' class="form-control"
					placeholder="Date et heure de sortie" id='modifSortieDateSortie'/> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	<div class="row form-group">
		<div class='col-lg-6 col-md-6 col-xs-6'>
		<label class='label-control'>Date fin d'inscription</label>
			<div class='input-group date' id='creation-sortie-date-inscription'>
				<input type='text' class="form-control"
					placeholder="Date limite d'inscription" id='modifSortieDateInscription' /> <span
					class="input-group-addon" > <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
		<label class='label-control'>Nombre de places</label>
			<input class="form-control" id="modifSortieNbPlace"
				aria-describedby="creation-sortie-place" placeholder="Nombre de place">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
		<label class='label-control'>Dur�e</label>
			<input class="form-control" id="modifSortieDuree"
				aria-describedby="creation-sortie-duree" placeholder="Dur�e">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<label class='label-control'>Description</label>
			<textarea class="form-control" rows="5" id="modifSortieDesc" placeholder="Description et infos"></textarea>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">Ville organisatrice: </div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
			<select class="form-control" id="modifSortieVille" aria-describedby="creation-sortie-ville-help"
				placeholder="Ville" required>
			</select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
		<label class='label-control'>Lieu</label>
			<select class="form-control" id="modifSortieLieu" aria-describedby="creation-sortie-Lieu-help"
				placeholder="Lieu" required>
			</select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-6 col-md-6 col-xs-6">
		<label class='label-control'>Etat de votre sortie</label>
			<select class="form-control" id="modifSortieEtat" aria-describedby="creation-sortie-etat-help"
				placeholder="Etat" required>
			</select>
		</div>
	</div>
	<div class="row form-group" id='raisonAnnulationArea'>
		<label class='label-control'>
			Raison de l'annulation
		</label>
		<textarea id='raisonAnnulation' class='form-control'>
		</textarea>
		<button class='btn btn-success' type='button' id='btnSaveAnnulation'>Enregistrer</button>
	</div>
	<div class='row'>
		<div class='col-md-12'>
			<div class='col-md-6'>
				<button class='btn btn-success' type='button' id='btnSaveModifSortie'>Enregistrer</button>
			</div>
		</div>
	</div>
</div>


<script>
	var expanded = false;

	function showCheckboxes() {
		var checkboxes = document.getElementById("checkboxes");
		if (!expanded) {
			checkboxes.style.display = "block";
			expanded = true;
		} else {
			checkboxes.style.display = "none";
			expanded = false;
		}
	}
</script>