$(document).ready(function(){
	const FORMAT = "dd/MM/yyyy HH:mm";
//Display Functions
	$('#li-accueil').addClass('btn-active');
	clearIHM();
	$('#accueil').show();
	refreshAccueilSortieTable();
	
	function clearIHM(){
		$('#accueil').hide();
		$('#gestion-ville').hide();
		$('#gestionSite').hide();
		$('#creation-participant').hide();
		$('#confirmationSuppression').hide();
		$('#creation-sortie').hide();
		$('#affichage-sortie').hide();
		$('#modification-sortie').hide();
		$('#annulation-sortie').hide();
		$('#detailSortie').hide();
		$('#ModificationSortie').hide();
		$('#modifSiteArea').hide();
		$('#actionSuccess').hide();
		$("input[name='radio']:checked").prop("checked", false);
	}
	
//	On ajoute la liste des sorties en page d'accueil
	function refreshAccueilSortieTable(){
		var currentUser = $('#currentUser').val();
		$.ajax({
			url: "http://localhost:8080/sortie.com/rest/sortie",
			cache: false,
			type: "GET",
			beforeSend: function(request) {
				request.setRequestHeader("Accept","application/json");
			},
			success: function(data){
				html = "";
				for( var i = 0; i < data.length; i++) {
					var datedebut = new Date(data[i]["dateheureDebut"]);
					var dateFin = new Date(data[i]["dateLimiteInscription"]);
					html += "<tr>";
					html += "<td><input type='radio' name='radio' value='"+data[i]['id']+"'/></td>";
					html += "<td>"+data[i]['nom']+"</td>";
					html += "<td>"+datedebut.toString(FORMAT)+"</td>";
					html += "<td>"+dateFin.toString(FORMAT)+"</td>";
					html += "<td><span class='empNbParticipant' data-id='"+data[i]['id']+"'>"+data[i]['listeParticipants'].length+"</span> /"+data[i]['nbInscriptionsMax']+"</td>";
					html += "<td>"+data[i]['etat']['libelle']+"</td>";
					var test = false;
					bloc_is_inscrit:{
						for (y = 0; y < data[i]['listeParticipants'].length; y++){
							if(data[i]['listeParticipants'][y]['id'] == currentUser && test == false){
								html += "<td>X</td>"
								test = true;
								break bloc_is_inscrit;
							}
						}
					}
					if(test == false){
						html += "<td></td>";
					}
					html += "<td>"+data[i]['organisateur']['prenom']+" "+data[i]['organisateur']['nom']+"</td>";
					html += "</tr>";
				}
				$('#listeSorties').html(html);
			}
		});
	}
	
	$('#modifierMaSortie').click(function(){
		$('#detailSortie').hide();
		$('#ModificationSortie').show();
		$('#raisonAnnulationArea').hide();
		var currentSortie = $('#currentSortie').val();
		$.ajax({
		  url: "http://localhost:8080/sortie.com/rest/sortie/get/"+currentSortie,
		  cache: false,
		  type: "GET",
		  beforeSend: function(request) {
		  	request.setRequestHeader("Accept","application/json");
		  },
		  success: function(data){
			  $('#modifSortieNom').val(data['nom']);
			  $('#modifSortieDateSortie').val(data['dateheureDebut']);
			  $('#modifSortieDateInscription').val(data['dateLimiteInscription']);
			  $('#modifSortieNbPlace').val(data['nbInscriptionsMax']);
			  $('#modifSortieDuree').val(data['duree']);
			  CKEDITOR.instances['modifSortieDesc'].setData(data['infosSortie']);
			  $('#modifSortieVille').html("<option selected>"+data['ville']['nomVille']+"</option>");
			  $('#modifSortieVille').attr("disabled", "true");
			  $('#modifSortieLieu').html("<option selected>"+data['lieu']['nom']+"</option>");
			  $('#modifSortieLieu').attr("disabled", "true");
			  $.ajax({
				  url: "http://localhost:8080/sortie.com/rest/etat",
				  cache: false,
				  type: "GET",
				  beforeSend: function(request) {
				  	request.setRequestHeader("Accept","application/json");
				  },
				  success: function(item){
					  html="";
					  for( var i = 0; i < item.length; i++) {
						  if(data['etat']['id'] == item[i]['id']){
							  html += "<option value='"+item[i]['id']+"' selected>"+ item[i]['libelle'] +"</option>"
						  }
						  html += "<option value='"+item[i]['id']+"'>"+ item[i]['libelle'] +"</option>";
		    	 	}
					$('#modifSortieEtat').html(html);
				  }
			});
		  }
		});
	});
	
	$('#ModificationSortie').click(function(){
		if($('#modifSortieEtat').val() == 5){
			$('#btnSaveModifSortie').hide();
			$('#modifSortieNom').attr("disabled", "true");
			$('#modifSortieDateSortie').attr("disabled", "true");
			$('#modifSortieDateInscription').attr("disabled", "true");
			$('#modifSortieNbPlace').attr("disabled", "true");
			$('#modifSortieDuree').attr("disabled", "true");
			$('#modifSortieDesc').attr("disabled", "true");
			$('#raisonAnnulationArea').show();
		}
	});
	
	$('#btnSaveAnnulation').click(function(){
		var raisonAnnulation = $('#raisonAnnulation').val();
		var sortieId = $('#currentSortie').val();
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/sortie/annulerSortie/"+sortieId+"/"+raisonAnnulation,
			  cache: false,
			  type: "PUT",
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				 $('#ModificationSortie').hide();
				 $('#accueil').show();
			  }
		  });
	});
	
	$('#btnSaveModifSortie').click(function(){
		var idSortie = $('#currentSortie').val();
		var nom = $('#modifSortieNom').val();
		var dateDebut = $('#modifSortieDateSortie').val();
		var dateFin = $('#modifSortieDateInscription').val();
		var nbPlace = $('#modifSortieNbPlace').val();
		var duree = $('#modifSortieDuree').val();
		var description = $('#modifSortieDesc').val();
		var ville = $('#modifSortieVille').val();
		var lieu = $('#modifSortieLieu').val();
		var etat = $('#modifSortieEtat').val();
		var organisateur = $('#currentUser').val();
		console.log("organisateur "+organisateur);
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/sortie/modif",
			  cache: false,
			  type: "POST",
			  data: jQuery.param({idSortie: idSortie,nom: nom, dateDebut:dateDebut, 
				  dateFin: dateFin, nbPlace: nbPlace, duree: duree,
				  description: description,ville:ville, lieu:lieu, etat: etat}),
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
			  }
		});
	});
	
	$('#btnAfficherSortie').click(function(){
		var radioValue = $("input[name='radio']:checked").val();
		var currentUser = $('#currentUser').val();
		if(radioValue != undefined){
			$('#accueil').hide();
			$('#detailSortie').show();
			$.ajax({
				  url: "http://localhost:8080/sortie.com/rest/sortie/get/"+radioValue,
				  cache: false,
				  type: "GET",
				  beforeSend: function(request) {
				  	request.setRequestHeader("Accept","application/json");
				  },
				  success: function(data){
					  if(currentUser != data['organisateur']['id']){
						  $('#modifierMaSortie').hide();
					  }
					  if(data['listeParticipants'].length == data['nbInscriptionsMax']){
						  $('#sinscrireAlaSortie').hide();
					  }
					  $('#currentSortie').val(data['id']);
					  $('#currentDetailSortieId').val(data['id']);
					  $('#titleSortie').html(data['nom']);
					  $('#dateDeSortie').html(data['dateheureDebut']);
					  $('#dateFinDinscription').html(data['dateLimiteInscription']);
					  $('#nbPlacesInscrit').html(data['listeParticipants'].length+"/"+data['nbInscriptionsMax']);
					  $('#organisateurSortie').html(data['organisateur']['prenom']+ " "+data['organisateur']['nom']);
					  $('#etatSortie').html(data['etat']['libelle']);
					  $('#descriptionSortie').html(data['infosSortie']);
					  var html = "";
					  if(data['listeParticipants'].length > 0){
						  for(i = 0; i<data['listeParticipants'].length; i++){
							  html += "<div class='row'>";
							  html += "<div class='col-md-offset-2 col-md-8'>";
							  html += "<p>-"+data['listeParticipants'][i]['prenom']+" "+ data['listeParticipants'][i]['nom'] +"</p>";
							  html += "</div>";
							  html += "</div>";
						  }
					  }else{
						 html += "<p>Aucun participant n'est inscrit a cette sortie</p>";
					  }
					  $('#modalParticipants').html(html);
					  $.ajax({
						  url: "http://localhost:8080/sortie.com/rest/inscrit/isinscrit/"+radioValue+"/"+currentUser,
						  cache: false,
						  type: "GET",
						  beforeSend: function(request) {
						  	request.setRequestHeader("Accept","application/json");
						  },
						  success: function(data){
							  if(data == true){
								  $('#sinscrireAlaSortie').hide();
							  }else{
								  $('#seDesinscrireAlaSortie').hide();
							  }
						  }
					});
				  }
			});
		}else{
			$('#modalErreurMessage').html("Veuillez selectionner une sortie pour effectuer cette operation");
			$('#modalErreur').modal('show'); 
		}
		
	});
	
	$('#sinscrireAlaSortie').click(function(){
		var currentUser = $('#currentUser').val();
		var idSortie = $('#currentDetailSortieId').val();
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/inscrit",
			  cache: false,
			  type: "POST",
			  data: jQuery.param({ idSortie: idSortie,idParticipant: currentUser}),
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				  $('#detailSortie').hide();
				  $('#accueil').show();
				  $('#modifierMaSortie').show();
				  $('#seDesinscrireAlaSortie').show();
				  $('#sinscrireAlaSortie').show();
				  $('#actionSuccessMessage').html("Vous etes bien inscit de la sortie "+idSortie);
				  $('#actionSuccess').show();
				  refreshAccueilSortieTable();
			  }
		});
	});
	
	$('#seDesinscrireAlaSortie').click(function(){
		var currentUser = $('#currentUser').val();
		var idSortie = $('#currentDetailSortieId').val();
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/inscrit/delete/"+idSortie+"/"+currentUser,
			  cache: false,
			  type: "DELETE",
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				  if(data == true){
					  $('#detailSortie').hide();
					  $('#accueil').show();
					  $('#modifierMaSortie').show();
					  $('#seDesinscrireAlaSortie').show();
					  $('#sinscrireAlaSortie').show();
					  $('#actionSuccessMessage').html("Vous etes bien desinscit de la sortie "+idSortie);
					  $('#actionSuccess').show();
					  refreshAccueilSortieTable();
				  }else{
					  $('#modalErreurMessage').html("Une erreur est survenue, si elle persiste contactez l'administrateur du site");
					  $('#modalErreur').modal('show'); 
				  }
			  }
		});
	});
	
	$('#retourVersListeSortie').click(function(){
		$('#detailSortie').hide();
		$('#accueil').show();
		$('#modifierMaSortie').show();
		$('#seDesinscrireAlaSortie').show();
		$('#sinscrireAlaSortie').show();
		refreshSortieTable();
	});
	
	$('#btnAccueil').click(function(){
		clearIHM();
		$('#accueil').show();
		$('#li-accueil').addClass('btn-active');
		$('#li-ville').removeClass('btn-active');
		$('#li-site').removeClass('btn-active');
		$('#li-creation-participant').removeClass('btn-active');
		
//		On charge la liste déroulante
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/site",
			  cache: false,
			  type: "GET",
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				  html="<option value='0'>--Choisir un site--</option>";
				  for( var i = 0; i < data.length; i++) {
					  html += "<option value='"+data[i]['id']+"'>"+ data[i]['nom'] +"</option>"
	    	 	}
				$('#select-site').html(html);
			  }
		});
		

	});
	
	$('#btnVille').click(function(){
		clearIHM();
		$('#gestion-ville').show();
		$('#li-accueil').removeClass('btn-active');
		$('#li-ville').addClass('btn-active');
		$('#li-site').removeClass('btn-active');
		$('#li-creation-participant').removeClass('btn-active');
		refreshVilleTable();

	});
	
	$('#btnSite').click(function(){
		clearIHM();
		$('#gestionSite').show();
		$('#li-accueil').removeClass('btn-active');
		$('#li-ville').removeClass('btn-active');
		$('#li-site').addClass('btn-active');
		$('#li-creation-participant').removeClass('btn-active');
		
		refreshSiteTable();
	});
	
	$('#btnCreationParticipant').click(function(){
		clearIHM();
		$('#creation-participant').show();
		$('#li-accueil').removeClass('btn-active');
		$('#li-ville').removeClass('btn-active');
		$('#li-site').removeClass('btn-active');
		$('#li-creation-participant').addClass('btn-active');
		refreshSiteTable();
	});

	$('#btnSupprSite').click(function(){
		var radioValue = $("input[name='radio']:checked").val();
		if(radioValue != undefined){
			var nomSite = $("#sitenom"+radioValue).html();
			$('#gestionSite').hide();
			$('#confirmationSuppression').show();
			$('#nomEntite').html("Site");
			$('#compelement').html(nomSite);
			$('#confirmSupp').click(function(){
				$.ajax({
					  url: "http://localhost:8080/sortie.com/rest/site/delete/"+radioValue,
					  cache: false,
					  type: "DELETE",
					  beforeSend: function(request) {
					  	request.setRequestHeader("Accept","application/json");
					  },
					  success: function(data){
						  $('#confirmationSuppression').hide();
						  $('#gestionSite').show();
						  refreshSiteTable();
					  }
				});
			});
			$('#cancelSupp').click(function(){
				$('#confirmationSuppression').hide();
				$('#gestionSite').show();
			});
		}else{
			$('#modalErreurMessage').html("Veuillez selectionner un site pour effectuer cette action");
			  $('#modalErreur').modal('show'); 
		}
	});

	$('#cancelModifSite').click(function(){
		$('#modifSiteIdArea').html("");
		$('#modifSiteName').val("");
		$('#modifSiteArea').hide();
	});
	
	$('#btnCreationSortie').click(function(){
		$('#accueil').hide();
		$('#creation-sortie').show();
		
//		On charge la liste déroulante
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/ville",
			  cache: false,
			  type: "GET",
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				  html="<option value='0'>--Choisir une ville--</option>";
				  for( var i = 0; i < data.length; i++) {
					  html += "<option value='"+data[i]['id']+"'>"+ data[i]['nomVille'] +"</option>"
				  }
				$('#creation-sortie-ville').html(html);
			  }
		});
		
//		On charge la liste déroulante
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/lieu",
			  cache: false,
			  type: "GET",
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				  html="<option value='0'>--Choisir un lieu--</option>";
				 
				  for( var i = 0; i < data.length; i++) {
					  html += "<option value='"+data[i]['id']+"'>"+ data[i]['nom'] +"</option>"
	    	 	}
				$('#creation-sortie-lieu').html(html);
			  }
		});
		
		
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/etat",
			  cache: false,
			  type: "GET",
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				  html="<option value='0'>--Choisir un etat--</option>";
				  for( var i = 0; i < data.length; i++) {
					  html += "<option value='"+data[i]['id']+"'>"+ data[i]['libelle'] +"</option>"
	    	 	}
				$('#creation-sortie-etat').html(html);
			  }
		});
	});
	
	$('#btnModificationSortie').click(function(){
		$('#accueil').hide();
		$('#modification-sortie').show();
		
//		On charge la liste déroulante
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/ville",
			  cache: false,
			  type: "GET",
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				  html="<option value='0'>--Choisir une ville--</option>";
				  for( var i = 0; i < data.length; i++) {
					  html += "<option value='"+data[i]['id']+"'>"+ data[i]['nomVille'] +"</option>"
	    	 	}
				$('#modification-sortie-ville').html(html);
			  }
		});
//		On charge la liste déroulante
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/lieu",
			  cache: false,
			  type: "GET",
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				  html="<option value='0'>--Choisir un lieu--</option>";
				  for( var i = 0; i < data.length; i++) {
					  html += "<option value='"+data[i]['id']+"'>"+ data[i]['nomLieu'] +"</option>"
	    	 	}
				$('#modification-sortie-lieu').html(html);
			  }
		});
	});
	
	$('#btnAnnulSortie').click(function(){
		$('#creation-sortie-nom').val("");
		$('#creation-sortie-date').val("");
		$('#creation-sortie-date-inscription').val("");
		$('#creation-sortie-place').val("");
		$('#creation-sortie-duree').val("");
		$('#creation-sortie-description').val("");
		$('#creation-sortie-ville').val("");
		$('#creation-sortie-lieu').val("");

	});
	
	
	$('#btnEnregAnnulSortie').click(function(){
		//enregistrement de l'annulation
		var motif =$('#annulation-sortie-motif').val();
		var etat =$('#annulation-sortie-etat').val();
		
		$.ajax({
			url: "http://localhost:8080/sortie.com/rest/sortie/annulerSortie",
			cache: false,
			type: "POST",
			data: jQuery.param({ nom: nom,date: date, dateInscription: dateInscription, place:place, duree: duree,
				description: description, ville: ville, lieu: lieu,currentUser: currentUser, etat:etat}),
			beforeSend: function(request) {
				request.setRequestHeader("Accept","application/json");
			},
			success: function(data){
			
			}
		});
	});
	
	
	$('#btnAccueilRecherche').click(function(){
//		$('#accueil').hide();
//		$('#affichage-sortie').show();
		
		var recherche = $('#recherche').val();
		if(recherche == ""){
			recherche = "null";
		}
		
		console.log(recherche);
		
		var organisateur = "false";
		if($('#sortie-organisateur').is(':checked')){
			organisateur = $('#currentUser').val();
		}
		var inscrit = "false";
		if ($('#sortie-inscrit').is(':checked')) {
			inscrit = "true";
		}
		var pasInscrit = "false";
		if ($('#sortie-pas-inscrit').is(':checked')) {
			pasInscrit = "true";
		}
		
		var sortiePassee = "false";
		if ($('#sortie-passee').is(':checked')) {
			sortiePassee = "true";
		}
		var debut = $('#sortie-debut').val();
		if(!debut == ""){
			var dateAr = debut.split(' ');
			var dateAr2 = dateAr[0].split('/');
			debut = dateAr2[2] + '-' + dateAr2[1] + '-' + dateAr2[0]+" "+dateAr[1];
		}else{
			debut="1800-01-01 00:00";
		}
		
		

		var fin = $('#sortie-fin').val();
		if(!fin == ""){
			var dateArFin = fin.split(' ');
			var dateArFin2 = dateArFin[0].split('/');
			fin = dateArFin2[2] + '-' + dateArFin2[1] + '-' + dateArFin2[0]+" "+dateArFin[1];
		}else{
			fin="2100-01-01 00:00";
		}
		
		var site = $('#select-site option:selected' ).text();
		if(site == "--Choisir un site--"){
			site = "null";
		}	

		
		$.ajax({
			url: "http://localhost:8080/sortie.com/rest/sortie/"+site+"/"+recherche+"/"+organisateur+"/"+inscrit+"/"+pasInscrit+"/"+sortiePassee+"/"+debut+"/"+fin+"",
			cache: false,
			type: "GET",
			beforeSend: function(request) {
				request.setRequestHeader("Accept","application/json");
			},
			success: function(data){
				html = "";
				for( var i = 0; i < data.length; i++) {
					var datedebut = new Date(data[i]["dateheureDebut"]);
					var dateFin = new Date(data[i]["dateLimiteInscription"]);
					html += "<tr>";
					html += "<td><input type='radio' name='radio' value='"+data[i]['id']+"'/></td>";
					html += "<td>"+data[i]['nom']+"</td>";
					html += "<td>"+datedebut.toString(FORMAT)+"</td>";
					html += "<td>"+dateFin.toString(FORMAT)+"</td>";
					html += "<td><span class='empNbParticipant' data-id='"+data[i]['id']+"'>"+data[i]['listeParticipants'].length+"</span> /"+data[i]['nbInscriptionsMax']+"</td>";
					html += "<td>"+data[i]['etat']['libelle']+"</td>";
					var test = false;
					bloc_is_inscrit:{
						for (y = 0; y < data[i]['listeParticipants'].length; y++){
							if(data[i]['listeParticipants'][y]['id'] == currentUser && test == false){
								html += "<td>X</td>"
								test = true;
								break bloc_is_inscrit;
							}
						}
					}
					if(test == false){
						html += "<td></td>";
					}
					html += "<td>"+data[i]['organisateur']['prenom']+" "+data[i]['organisateur']['nom']+"</td>";
					html += "</tr>";
				}
				 $('#listeSorties').html(html);
			}
		});
	});
	
	$('#btnEnregSortie').click(function(){
		var nom =$('#creation-sortie-nom').val();
		var date =$('#creation-sortie-date1-val').val();
		var dateInscription =$('#creation-sortie-date-inscription-val').val();
		var place =$('#creation-sortie-place').val();
		var duree =$('#creation-sortie-duree').val();
		var description = CKEDITOR.instances['creation-sortie-description'].getData();
		var ville =$('#creation-sortie-ville').val();
		var lieu =$('#creation-sortie-lieu').val();
		var currentUser = $('#currentUser').val();
		var etat = $('#creation-sortie-etat').val();

		if($('#creation-sortie-ville').val() == 0 || $('#creation-sortie-lieu').val() == 0 || 
				$('#creation-sortie-etat').val() == 0){
			$('#modalErreurMessage').html("Veuillez compléter les champs");
			$('#modalErreur').modal('show'); 
		}else{
			$.ajax({
				url: "http://localhost:8080/sortie.com/rest/sortie/ajoutSortie",
				cache: false,
				type: "POST",
				data: jQuery.param({ nom: nom,date: date, dateInscription: dateInscription, place:place, duree: duree,
					description: description, ville: ville, lieu: lieu,currentUser: currentUser, etat:etat}),
				beforeSend: function(request) {
					request.setRequestHeader("Accept","application/json");
				},
				success: function(data){
					clearIHM();
					$('#accueil').show();
				}
			});
		}
		
		
	});
	
	$('#btnAnnulSortie').click(function(){

	});
	

	
	$('#btnAnnulModifSortie').click(function(){
		$('#annulation-sortie').show();
		$('#modification-sortie').hide();
//		refreshSortieTable();
	});
	
	
	$('#btnModifSite').click(function(){
		var radioValue = $("input[name='radio']:checked").val();
		if(radioValue != undefined){
			var nomSite = $("#sitenom"+radioValue).html();
			$('#modifSiteArea').show();
			$('#modifSiteIdArea').html(radioValue);
			$('#modifSiteName').val(nomSite);
			$('#saveModifSite').click(function(){
				var nouveauNomSite = $('#modifSiteName').val();
				if(nouveauNomSite != nomSite){
					$.ajax({
						  url: "http://localhost:8080/sortie.com/rest/site/update/"+radioValue+"/"+nouveauNomSite,
						  cache: false,
						  type: "PUT",
						  beforeSend: function(request) {
						  	request.setRequestHeader("Accept","application/json");
						  },
						  success: function(data){
							  $('#modifSiteName').val("");
							  $('#modifSiteArea').hide();
							  refreshSiteTable();
						  }
					});
				}
			});
		}else{
			$('#modalErreurMessage').html("Veuillez selectionner un site pour effectuer cette action");
			$('#modalErreur').modal('show'); 
		}
	});
	
	
//	AJAX Function

//	Ajout d'un nouveau site
	$('#submitAddSite').click(function(){
		var nomSite = $('#addNomSite').val();
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/site",
			  cache: false,
			  type: "POST",
			  data: jQuery.param({ nomSite: nomSite}),
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
				  	var html = "<tr>";
				  	html += "<td><input type='radio' name='radio' value='"+data['id']+"'/></td>";
				  	html += '<td>' + data["id"] + '</td>';
	    	   		html += '<td id="sitenom'+ data["id"] +'">' + data["nom"] + '</td>';
		    	 	html += "</tr>";
		    	 	$('#tableSite').append(html);
		    	 	$('#addNomSite').val("");
			  }
		});
	});

	function refreshSiteTable(){
		$.ajax({
		  url: "http://localhost:8080/sortie.com/rest/site",
		  cache: false,
		  type: "GET",
		  beforeSend: function(request) {
		  	request.setRequestHeader("Accept","application/json");
		  },
		  success: function(data){
			var html = "";
	   		for( var i = 0; i < data.length; i++) {
   				html += '<tr>';
   				html += "<td><input type='radio' name='radio' value='"+data[i]['id']+"'/></td>";
    	   		html += '<td>' + data[i]["id"] + '</td>';
    	   		html += '<td id="sitenom' +data[i]["id"]+ '">' + data[i]["nom"] + '</td>';
	    	 	html += "</tr>";
    	 	}
    	 	$('#tableSite').html(html);
		  }
		});
	}
	
	function refreshVilleTable(){
		$.ajax({
		  url: "http://localhost:8080/sortie.com/rest/ville",
		  cache: false,
		  type: "GET",
		  beforeSend: function(request) {
		  	request.setRequestHeader("Accept","application/json");
		  },
		  success: function(data){
			var html = "";
	   		for( var i = 0; i < data.length; i++) {
   				html += '<tr>';
   				html += "<td><input type='radio' name='radio' value='"+data[i]['id']+"'/></td>";
    	   		html += '<td>' + data[i]["id"] + '</td>';
    	   		html += '<td id="villenom'+ data[i]["id"] +'">' + data[i]["nomVille"] + '</td>';
    	   		html += '<td id="codepostal'+ data[i]["id"] +'">' + data[i]["codePostal"] + '</td>';
	    	 	html += "</tr>";
    	 	}
    	 	$('#table-ville').html(html);
		  }
		});
	}
	
	function refreshSortieTable(){
		$.ajax({
		  url: "http://localhost:8080/sortie.com/rest/participant",
		  cache: false,
		  type: "GET",
		  beforeSend: function(request) {
		  	request.setRequestHeader("Accept","application/json");
		  },
		  success: function(data){
			var html = "";
	   		for( var i = 0; i < data.length; i++) {
   				html += '<tr>';
    	   		html += '<td>' + data[i]["id"] + '</td>';
    	   		html += '<td id="participantpseudo'+ data[i]["id"] +'">' + data[i]["pseudo"] + '</td>';
    	   		html += '<td id="participantnom'+ data[i]["id"] +'">' + data[i]["nom"] + '</td>';
	    	 	html += "</tr>";
    	 	}
    	 	$('#table-afficher-sortie').html(html);
		  }
		});
	}
	
	
//	$('.datepicker').datepicker({
//	    format: 'mm/dd/yyyy',
//	    startDate: '-3d'
//	});
	
//	$('#sandbox-container input').datepicker({
//	});
	
	 $(function () {
         $('#creation-sortie-date1').datetimepicker({
        	 format: 'dd/mm/yyyy hh:ii',
    		 language: 'fr'
         }
        		 
         );
         
         $('#creation-sortie-date-inscription').datetimepicker({
        	 format: 'dd/mm/yyyy',
    		 language: 'fr'
         }
        
         );
         
         $('#debut').datetimepicker({
        	 format: 'dd/mm/yyyy hh:ii',
    		 language: 'fr'
         }
        
         );
         
         $('#fin').datetimepicker({
        	 format: 'dd/mm/yyyy hh:ii',
    		 language: 'fr'
         }
        
         );
     });
	
	
});


