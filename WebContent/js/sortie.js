$(document).ready(function(){
//Display Functions
	
	$('#gestionSite').hide();
	$('#gestionVille').hide();
	$('#confirmationSuppression').hide();

	$('#btnVille').click(function(){
		$('#accueil').hide();
		$('#gestionSite').hide();
		$('#confirmationSuppression').hide();
		$('#gestionVille').show();
	});
	
	$('#btnSite').click(function(){
		$('#accueil').hide();
		$('#gestionVille').hide();
		$('#confirmationSuppression').hide();
		$('#gestionSite').show();
		$('#modifSiteArea').hide();
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
});