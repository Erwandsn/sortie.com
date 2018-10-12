<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<div class="container-fluid" id="accueil">
	<div class='row container-title'>
		<div class='col-md-2 col-md-offset-5 title'>
			<h2>Toutes les sorties</h2>
		</div>
	</div>
	<div class='container'>
		<div class="row form-group">
			<div class="col-lg-2 col-md-2 col-xs-2">
				<input type="text" class="form-control" id="recherche"
					aria-describedby="recherche-help" placeholder="Recherche"></select>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2">
				<select class="form-control" id="select-site"
					aria-describedby="select-site-help"></select>
			</div>
			<div class="multiselect col-lg-2 col-md-2 col-xs-2">
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
							auxquelles je ne suis pas inscrit/e.</label> </label> <label for="five">
						<input type="checkbox" class="form-check-input"
						id="sortie-pas-inscrit"> <label class="form-check-label"
						for="sortie-pas-inscrit">Sorties auxquelles je ne suis pas
							inscrit/e.</label>
					</label>
				</div>
			</div>
			<div class='col-lg-2 col-md-2 col-xs-2'>
				<div class='input-group date' id='debut'>
					<input type='text' class="form-control" placeholder="D&eacute;but"
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
			<table class="table table-responsive">
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
					<button class='btn btn-info' id='btnAfficherSortie'><span class='glyphicon glyphicon-eye-open'></span> &nbsp;Afficher</button>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-4">
					<button type="button" class="btn btn-primary"
						id="btnCreationSortie"><span class='glyphicon glyphicon-plus'></span> &nbsp;Creer une sortie</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div class='container' id='detailSortie'>
	<input type='hidden' id='currentSortie' />
	<input type='hidden' id='currentDetailSortieId' value='' />
	<div class='row'>
		<div class='col-md-1' id='retourVersListeSortie'>
			<button class='btn btn-info'>
				<span class='glyphicon glyphicon-chevron-left'></span>
			</button>
		</div>
		<div class='col-md-11' class='headerSortie'>
			<img src='/sortie.com/images/defaultSortie.jpg' class='imgheader' alt='defaultImage'/>
			<div class='encadreSortie'>
				<div class='col-md-12'>
					<h2 id='titleSortie'></h2>
				</div>
			</div>
		</div>
		<div class='col-md-11 col-md-offset-1 contentSortie'>
			<div class='col-md-4 col-xs-12'>
				<p>
					<i class='glyphicon glyphicon-calendar'></i> &nbsp;<span id='dateDeSortie'></span>
				</p>
				<p>
					Fin d'inscription: <span id='dateFinDinscription'></span>
				</p>
			</div>
			<div class='col-md-4 col-xs-12'>
				<p>
					<span class='glyphicon glyphicon-user'> </span> <span id='nbPlacesInscrit'></span>
				</p>
				<p>
					Organisateur :<span id='organisateurSortie'></span>
				</p>
			</div>
			<div class='col-md-4 col-xs-12'>
				<p>
					Etat : <span id='etatSortie'></span>
				</p>
				<button type="button" class="btn btnVoirParticipant" data-toggle="modal"
				data-target="#listeParticipantsSortie">Voir les participants</button>
			</div>
			<div class='col-md-4 col-xs-12'>
				<p>
					<span class='glyphicon glyphicon-screenshot'></span><span id='villeLieuSortie'></span>
				</p>
			</div>
			<div class='col-md-12'>
				<p id='descriptionSortie'></p>
			</div>
		</div>
	</div>
	<div class='row'>
		<div class='col-md-3'>
			<button class='btn btn-success btn-block' id='sinscrireAlaSortie'>S'inscrire</button>
		</div>
		<div class='col-md-3'>
			<button class='btn btn-danger btn-block' id='seDesinscrireAlaSortie'>Se
				desinscrire</button>
		</div>
		<div class='col-md-3'>
			<button class='btn btn-info btn-block' id='modifierMaSortie'>
				<span class='glyphicon glyphicon-pencil'></span> Modifier
			</button>
		</div>
	</div>
	<!-- Modal  liste participants-->
	<div id="listeParticipantsSortie" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Partcipants a la sortie</h4>
				</div>
				<div class="modal-body" id="modalParticipants"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div class='container' id='ModificationSortie'>
	<div class='col-md-6 col-md-offset-3 col-xs-12'>
		<div class="row form-group">
			<label class='label-control'>Nom</label> <input class="form-control"
				id="modifSortieNom" aria-describedby="creation-sortie-nom"
				placeholder="Nom" value="">
		</div>
		<div class="row form-group">
			<div class='input-group date' id='creation-sortie-date1'>
				<label class='label-control'>Date de sortie</label> <input
					type='text' class="form-control"
					placeholder="Date et heure de sortie" id='modifSortieDateSortie' />
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
		<div class="row form-group">
			<label class='label-control'>Date fin d'inscription</label>
			<div class='input-group date' id='creation-sortie-date-inscription'>
				<input type='text' class="form-control"
					placeholder="Date limite d'inscription"
					id='modifSortieDateInscription' /> <span class="input-group-addon">
					<span class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
		<div class="row form-group">
			<label class='label-control'>Nombre de places</label> <input
				class="form-control" id="modifSortieNbPlace"
				aria-describedby="creation-sortie-place"
				placeholder="Nombre de place">
		</div>
		<div class="row form-group">
			<label class='label-control'>Dur&eacute;e</label> <input
				class="form-control" id="modifSortieDuree"
				aria-describedby="creation-sortie-duree" placeholder="Dur&eacute;e">
		</div>
		<div class="row form-group">
			<label class='label-control'>Description</label>
			<textarea class="form-control" rows="5" id="modifSortieDesc"
				placeholder="Description et infos"></textarea>
		</div>
		<div class="row form-group">
			Ville organisatrice:
		</div>
		<div class="row form-group">
			<select class="form-control" id="modifSortieVille"
				aria-describedby="creation-sortie-ville-help" placeholder="Ville"
				required>
			</select>
		</div>
		<div class="row form-group">
			<label class='label-control'>Lieu</label> <select
				class="form-control" id="modifSortieLieu"
				aria-describedby="creation-sortie-Lieu-help" placeholder="Lieu"
				required>
			</select>
		</div>
		<div class="row form-group">
			<label class='label-control'>Etat de votre sortie</label> <select
				class="form-control" id="modifSortieEtat"
				aria-describedby="creation-sortie-etat-help" placeholder="Etat"
				required>
			</select>
		</div>
		<div class="row form-group" id='raisonAnnulationArea'>
			<label class='label-control'> Raison de l'annulation </label>
			<textarea id='raisonAnnulation' class='form-control'>
			</textarea>
			<button class='btn btn-success' type='button' id='btnSaveAnnulation'>Enregistrer</button>
		</div>
		<div class='row'>
			<div class='col-md-12'>
				<div class='col-md-6'>
					<button class='btn btn-success' type='button'
						id='btnSaveModifSortie'>Enregistrer</button>
				</div>
			</div>
		</div>
	</div>
	<ckeditor:replace replace="modifSortieDesc" basePath="/sortie.com/vendor/ckeditor" />
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