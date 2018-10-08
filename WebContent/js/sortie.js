$(document).ready(function(){
//Display Functions
	$('#gestion-ville').hide();
	$('#gestionSite').hide();
	$('#creation-participant').hide();
	$('#confirmationSuppression').hide();
	$('#creation-sortie').hide();
	$('#affichage-sortie').hide();
	$('#modification-sortie').hide();
	$('#annulation-sortie').hide();
	
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
		
		$('#li-accueil').addClass('active');
		$('#li-ville').removeClass('active');
		$('#li-site').removeClass('active');
		$('#li-creation-participant').removeClass('active');
		

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
		
		$('#li-accueil').removeClass('active');
		$('#li-ville').addClass('active');
		$('#li-site').removeClass('active');
		$('#li-creation-participant').removeClass('active');
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
		
		$('#li-accueil').removeClass('active');
		$('#li-ville').removeClass('active');
		$('#li-site').addClass('active');
		$('#li-creation-participant').removeClass('active');
		
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
		
		$('#li-accueil').removeClass('active');
		$('#li-ville').removeClass('active');
		$('#li-site').removeClass('active');
		$('#li-creation-participant').addClass('active');
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
	
	$('#btnAccueilRecherche').click(function(){
		$('#accueil').hide();
		$('#affichage-sortie').show();

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
		console.log("nom "+nom);
		console.log("date "+date);
		console.log("dateInscription "+dateInscription);
		console.log("place "+place);
		console.log("duree "+duree);
		console.log("description "+description);
		console.log("lieu "+lieu);
		console.log("currentUser "+currentUser);

		
		$.ajax({
			url: "http://localhost:8080/sortie.com/rest/sortie/ajoutSortie",
			cache: false,
			type: "POST",
			data: jQuery.param({ nom: nom,date: date, dateInscription: dateInscription, place:place, duree: duree,
				description: description, ville: ville, lieu: lieu,currentUser: currentUser}),
			beforeSend: function(request) {
				request.setRequestHeader("Accept","application/json");
			},
			success: function(data){
			
			}
		});
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
        	 format: 'dd/mm/yyyy',
    		 language: 'fr'
         }
        
         );
         
         $('#fin').datetimepicker({
        	 format: 'dd/mm/yyyy',
    		 language: 'fr'
         }
        
         );
     });
	 
	
});