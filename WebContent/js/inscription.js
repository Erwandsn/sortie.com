$(document).ready(function(){
	$('#creation-participant').hide();
	$('#confirmationInscription').hide();
	$('#inscriptionBtn').click(function(){
		$('#authentification').hide();
		$('#creation-participant').show();
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
				alert("Les deux mots de passe ne sont pas identiques");
			}else{
				alert('tentative d inscription');
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
		}else{
			alert("Pour compléter votre inscription, veuillez compléter tous les champs");
		}
	});
	
});