$('#modifProfilUser').hide();

$('#btnModifProfil').click(function(){
	$('#profilUser').hide();
	$('#modifProfilUser').show();
	var villeId = $('#currentVilleId').val();
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
				  if(data[i]['id'] == villeId){
					  html += "<option value='"+data[i]['id']+"' selected='selected'>"+ data[i]['nomVille'] +"</option>"
				  }else{
					  html += "<option value='"+data[i]['id']+"'>"+ data[i]['nomVille'] +"</option>"
				  }
				  
  	 	}
			$('#modifProfilVille').html(html);
		  }
	});
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

$('#saveProfilModif').click(function(){
	var pseudo = $('#pseudoUser').html();
	var nom = $('#modifProfilNom').val();
	var prenom = $('#modifProfilPrenom').val();
	var telephone = $('#modifProfilTelephone').val();
	var email = $('#modifProfilMail').val();
	var ville = $('#modifProfilVille').val();
	var mdp = "";
	if($('#modifProfilMdp').val() != "" && $('#modifProfilConfirmMdp').val() != "" &&  $('#modifProfilMdp').val() == $('#modifProfilConfirmMdp').val()){
		mdp = $('#modifProfilMdp').val();
	}
	$.ajax({
		  url: "http://localhost:8080/sortie.com/rest/creationParticipant",
		  cache: false,
		  type: "PUT",
		  data: jQuery.param({pseudo: pseudo, prenom: prenom,nom: nom,telephone: telephone,email: email, ville: ville, mdp: mdp}),
		  beforeSend: function(request) {
		  	request.setRequestHeader("Accept","application/json");
		  },
		  success: function(data){
			  $('#modifProfilUser').hide();
			  $('#profilUser').show();
			  $('#actionSuccessMessage').html("La modification de votre profil a bien ete appliquee");
			  $('#actionSuccess').show();
			  window.location="/sortie.com/refreshUser?pseudo="+pseudo;
		  }
	});
	
});

$('#cancelProfilModif').click(function(){
	$("#modifProfilUser").hide();
	$('#profilUser').show();
});

//$('input[type=file]').change(function(){
//
//	$(this).simpleUpload("/sortie.com/ajax/uploadPhoto", {
//	
//		start: function(file){
//			//upload started
//			console.log("upload started");
//		},
//
//		progress: function(progress){
//			//received progress
//			console.log("upload progress: " + Math.round(progress) + "%");
//		},
//
//		success: function(data){
//			//upload successful
//			console.log("upload successful!");
//			console.log(data);
//		},
//
//		error: function(error){
//			//upload failed
//			console.log("upload error: " + error.name + ": " + error.message);
//		}
//
//	});
//
//});