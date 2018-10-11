$(document).ready(function(){
	const FORMAT = "dd/MM/yyyy HH:mm";
//Display Functions
	$('#li-accueil').addClass('btn-active');
	$('#gestion-ville').hide();
	$('#gestionSite').hide();
	$('#creation-participant').hide();
	$('#confirmationSuppression').hide();
	$('#creation-sortie').hide();
	$('#affichage-sortie').hide();
	$('#modification-sortie').hide();
	$('#annulation-sortie').hide();
	$('#detailSortie').hide();
	refreshAccueilSortieTable();
	
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
				console.log(JSON.stringify(data));
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
					  console.log(JSON.stringify(data));
					  $('#currentDetailSortieId').val(data['id']);
					  $('#titleSortie').html(data['nom']);
					  $('#dateDeSortie').html(data['dateheureDebut']);
					  $('#dateFinDinscription').html(data['dateLimiteInscription']);
					  $('#nbPlacesInscrit').html(data['nbInscriptionsMax']);
					  $('#organisateurSortie').html(data['organisateur']['prenom']+ " "+data['organisateur']['nom']);
					  $('#etatSortie').html(data['etat']['libelle']);
					  $('#descriptionSortie').html(data['infosSortie']);
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
			alert('Veuillez selectionner une sortie a afficher'); 
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
				  alert('inscription effectuée');
				  $('#detailSortie').hide();
				  $('#accueil').show();
				  $('#modifierMaSortie').show();
				  $('#seDesinscrireAlaSortie').show();
				  $('#sinscrireAlaSortie').show();
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
					  alert('desinscription effectuée');
					  $('#detailSortie').hide();
					  $('#accueil').show();
					  $('#modifierMaSortie').show();
					  $('#seDesinscrireAlaSortie').show();
					  $('#sinscrireAlaSortie').show();
					  refreshAccueilSortieTable();
				  }else{
					  alert("Echec de la desinscription")
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
		$('#accueil').show();
		$('#gestion-ville').hide();
		$('#gestionSite').hide();
		$('#creation-participant').hide();
		$('#confirmationSuppression').hide();
		$('#creation-sortie').hide();
		$('#affichage-sortie').hide();
		$('#modification-sortie').hide();
		$('#annulation-sortie').hide();
		
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
				  console.log("sorties js +"+JSON.stringify(data));
				  html="<option value='0'>--Choisir un site--</option>";
				  for( var i = 0; i < data.length; i++) {
					  html += "<option value='"+data[i]['id']+"'>"+ data[i]['nom'] +"</option>"
	    	 	}
				$('#select-site').html(html);
			  }
		});
		

	});
	
	$('#btnVille').click(function(){
		$('#accueil').hide();
		$('#gestion-ville').show();
		$('#gestionSite').hide();
		$('#creation-participant').hide();
		$('#confirmationSuppression').hide();
		$('#creation-sortie').hide();
		$('#affichage-sortie').hide();
		$('#modification-sortie').hide();
		$('#annulation-sortie').hide();
		
		$('#li-accueil').removeClass('btn-active');
		$('#li-ville').addClass('btn-active');
		$('#li-site').removeClass('btn-active');
		$('#li-creation-participant').removeClass('btn-active');
		refreshVilleTable();

	});
	
	$('#btnSite').click(function(){
		$('#accueil').hide();
		$('#gestion-ville').hide();
		$('#confirmationSuppression').hide();
		$('#gestionSite').show();
		$('#creation-participant').hide();
		$('#modifSiteArea').hide();
		$('#creation-sortie').hide();
		$('#affichage-sortie').hide();
		$('#modification-sortie').hide();
		$('#annulation-sortie').hide();
		
		$('#li-accueil').removeClass('btn-active');
		$('#li-ville').removeClass('btn-active');
		$('#li-site').addClass('btn-active');
		$('#li-creation-participant').removeClass('btn-active');
		
		refreshSiteTable();
	});
	
	$('#btnCreationParticipant').click(function(){
		$('#accueil').hide();
		$('#gestion-ville').hide();
		$('#gestionSite').hide();
		$('#gestion-ville').hide();
		$('#creation-participant').show();
		$('#modifSiteArea').hide();
		$('#confirmationSuppression').hide();
		$('#creation-sortie').hide();
		$('#affichage-sortie').hide();
		$('#modification-sortie').hide();
		$('#annulation-sortie').hide();
		
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
			alert("Veuillez selectionner un site");
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
				  console.log("sorties js +"+JSON.stringify(data));
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
//			alert(debut);
//			var debut2 = $('#sortie-debut').val();
//			
//			
//			
//			var dateAr2 = debut2.split(' ');
//			
//			debut = debut+" "+dateAr2[1];
//			alert(debut);
		}else{
			debut="1800-01-01 00:00";
		}
		
		

		var fin = $('#sortie-fin').val();
		if(!fin == ""){
			var dateArFin = fin.split(' ');
			var dateArFin2 = dateArFin[0].split('/');
			fin = dateArFin2[2] + '-' + dateArFin2[1] + '-' + dateArFin2[0]+" "+dateArFin[1];
			alert(fin);
//			fin = dateArFin[2] + '-' + dateArFin[1] + '-' + dateArFin[0];
//			var fin2 = $('#sortie-fin').val();
//			var dateArFin2 = fin2.split(' ');
//			fin = fin+" "+dateArFin2[1];
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
				 console.log(JSON.stringify(data));
				 for( var i = 0; i < data.length; i++) {
					 var datedebut = new Date(data[i]["dateheureDebut"]);
					 var dateFin = new Date(data[i]["dateLimiteInscription"]);
					 html += "<tr>";
					 html += "<td><input type='radio' name='radio' value='"+data[i]['id']+"'/></td>";
					 html += "<td>"+data[i]['nom']+"</td>";
					 html += "<td>"+data[i]['dateheureDebut'].toString(FORMAT)+"</td>";
					 html += "<td>"+data[i]['dateLimiteInscription'].toString(FORMAT)+"</td>";
					 html += "<td>TODO /"+data[i]['nbInscriptionsMax']+"</td>";
					 html += "<td>"+data[i]['etat']['libelle']+"</td>";
					 html += "<td>TODO</td>";
					 html += "<td>"+data[i]['organisateur']['prenom']+" "+data[i]['organisateur']['nom']+"</td>";
					 html += "<td><button type='button' class='btn btn-info'>Afficher</td>";
					 html += "</tr>";
				  }
				 $('#listeSorties').html(html);
//				 refreshSortieTable();
			}
		});
	});
	
	$('#btnEnregSortie').click(function(){
		var nom =$('#creation-sortie-nom').val();
		var date =$('#creation-sortie-date1-val').val();
		var dateInscription =$('#creation-sortie-date-inscription-val').val();
		var place =$('#creation-sortie-place').val();
		var duree =$('#creation-sortie-duree').val();
		var description =$('#creation-sortie-description').val();
		var ville =$('#creation-sortie-ville').val();
		var lieu =$('#creation-sortie-lieu').val();
		var currentUser = $('#currentUser').val();
		var etat = $('#creation-sortie-etat').val();
//		console.log("nom "+nom);
//		console.log("date "+date);
//		console.log("dateInscription "+dateInscription);
//		console.log("place "+place);
//		console.log("duree "+duree);
//		console.log("description "+description);
//		console.log("lieu "+lieu);
//		console.log("currentUser "+currentUser);
//		console.log("etat "+etat);

		if($('#creation-sortie-ville').val() == 0 || $('#creation-sortie-lieu').val() == 0 || 
				$('#creation-sortie-etat').val() == 0){
			alert("Veuillez compléter les champs.");
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
			alert("Veuillez selectionner un site");
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
			console.log("sorties js +"+JSON.stringify(data));
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


