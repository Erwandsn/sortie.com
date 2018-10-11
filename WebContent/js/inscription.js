$(document).ready(function(){
	$('#creation-participant').hide();
	$('#confirmationInscription').hide();
	
	
	
	$('#inscriptionBtn').click(function(){
		$('#authentification').hide();
		$('#creation-participant').show();
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
				$('#ville').html(html);
			  }
		});
	});
	
	$('#btnAnnuler').click(function(){
		$('#creation-participant').hide();
		$('#authentification').show();
	});
	
	$('#goToLogin').click(function(){
		$('#creation-participant').show();
		$('#confirmationInscription').hide();
	});
	
	$('#enregistrer').click(function(){
		var pseudo = $('#pseudo').val();
		var prenom = $('#prenom').val();
		var nom = $('#nom').val();
		var telephone = $('#telephone').val();
		var email = $('#email').val();
		var motDePasse = $('#mot-de-passe').val();
		var confirmation = $('#confirmation').val();
		var ville = $('#ville').val();
		if(pseudo != "" && prenom != "" && nom!="" && telephone!="" && email!="" && motDePasse!="" && confirmation !="" && ville !=""){
			if(motDePasse != confirmation){
				$('#modalErreurMessage').html("Les deux mots de passe ne sont pas identiques");
				$('#modalErreur').modal('show'); 
			}else{
				if(ville=="0"){
					$('#modalErreurMessage').html("Veuillez choisir une ville");
					$('#modalErreur').modal('show'); 
				}else{
					$.ajax({
						url: "http://localhost:8080/sortie.com/rest/creationParticipant",
						cache: false,
						type: "POST",
						data: jQuery.param({ pseudo: pseudo, prenom: prenom,nom: nom,telephone: telephone,email: email,motDePasse: motDePasse,confirmation: confirmation, ville: ville}),
						beforeSend: function(request) {
							request.setRequestHeader("Accept","application/json");
						},
						success: function(data){
							$('#creation-participant').hide();
							$('#confirmationInscription').show();
						}
					});
				}
			}
		}else{
			$('#modalErreurMessage').html("Pour completer votre inscription, veuillez completer tous les champs");
			$('#modalErreur').modal('show'); 
		}
	});
	
});