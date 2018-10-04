$('#modifProfilUser').hide();

$('#btnModifProfil').click(function(){
	$('#profilUser').hide();
	$('#modifProfilUser').show();
});

$('button#btnAnnuler').click(function(){
	$('#pseudo').val("");
	$('#prenom').val("");
	$('#nom').val("");
	$('#telephone').val("");
	$('#email').val("");
	$('#mot-de-passe').val("");
	$('#confirmation').val("");
	$('#ville').val("");
});


$('#enregistrer').click(function(){
	var pseudo = $('#pseudo').val();
	var prenom = $('#prenom').val();
	var nom = $('#nom').val();
	var telephone = $('#telephone').val();
	var email = $('#email').val();
	var motDePasse = $('#motDePasse').val();
	var confirmation = $('#confirmation').val();
	var ville = $('#ville').val();
	$.ajax({
		  url: "http://localhost:8080/sortie.com/rest/creationParticipant",
		  cache: false,
		  type: "POST",
		  data: jQuery.param({ pseudo: pseudo, prenom: prenom,nom: nom,telephone: telephone,email: email,motDePasse: motDePasse,confirmation: confirmation, ville: ville}),
		  beforeSend: function(request) {
		  	request.setRequestHeader("Accept","application/json");
		  },
		  success: function(data){
			  $('#accueil').show();
			  $('#creation-participant').hide();
		  }
	});
});