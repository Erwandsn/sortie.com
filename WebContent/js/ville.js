$(document).ready(function(){
	$('#modifVilleArea').hide();
	$('#btnValiderVille').hide();
	
//	AJAX Function
	$('#rechercher').click(function(){
		
		var nomVille = $('#hier').val();
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/ville/"+nomVille,
			  cache: false,
			  type: "GET",
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
//				  	alert("cocou");
				  	var html = "";
				  	console.log(JSON.stringify(data));
				  	for( var i = 0; i < data.length; i++) {
	    	   			html += '<tr>';
	    	   			html += "<td><input type='radio' name='radio' value='"+data[i]['id']+"'/></td>";
		    	   		html += '<td>' + data[i]["id"] + '</td>';
		    	   		html += '<td>' + data[i]["nomVille"] + '</td>';
		    	   		html += '<td>' + data[i]["codePostal"] + '</td>';
			    	 	html += "</tr>";
		    	 	}
				  	
		    	 	$('#table-ville').html(html);

			  }
		});
	});
	
	
	$('#btnSupprVille').click(function(){
		var radioValue = $("input[name='radio']:checked").val();
		if(radioValue != undefined){
			var nomVille = $("#villenom"+radioValue).html();
			$('#gestionVille').hide();
			$('#confirmationSuppression').show();
			$('#first-content').show();
			$('#nomEntite').html("Ville");
			$('#compelement').html(nomVille);
			$('#confirmSupp').click(function(){
				$.ajax({
					  url: "http://localhost:8080/sortie.com/rest/ville/delete/"+radioValue,
					  cache: false,
					  type: "DELETE",
					  beforeSend: function(request) {
					  	request.setRequestHeader("Accept","application/json");
					  },
					  success: function(data){
						  $('#confirmationSuppression').hide();
						  $('#gestionVille').show();
						  refreshVilleTable();
					  }
				});
			});
			$('#cancelSupp').click(function(){
				$('#confirmationSuppression').hide();
				$('#gestionVille').show();
			});
		}else{
			alert("Veuillez selectionner un ville");
		}
	});

	$('#cancelModifVille').click(function(){
		$('#modifVilleIdArea').html("");
		$('#modifVilleName').val("");
		$('#modifVilleArea').hide();
	});

	$('#btnModifVille').click(function(){
		var radioValue = $("input[name='radio']:checked").val();
		if(radioValue != undefined){
			var nomVille = $("#villenom"+radioValue).html();
			$('#modifVilleArea').show();
			$('#modifVilleIdArea').html(radioValue);
			$('#modifVilleName').val(nomVille);
			$('#saveModifVille').click(function(){
				var nouveauNomVille = $('#modifVilleName').val();
				if(nouveauNomVille != nomVille){
					$.ajax({
						  url: "http://localhost:8080/sortie.com/rest/ville/update/"+radioValue+"/"+nouveauNomVille,
						  cache: false,
						  type: "PUT",
						  beforeSend: function(request) {
						  	request.setRequestHeader("Accept","application/json");
						  },
						  success: function(data){
							  $('#modifVilleName').val("");
							  $('#modifVilleArea').hide();
							  refreshVilleTable();
						  }
					});
				}
			});
		}else{
			alert("Veuillez selectionner un ville");
		}
	});
	
	
//	AJAX Function

//	Ajout d'un nouveau ville
	$('#submitAddVille').click(function(){
		$('#btnValiderVille').show();
		$('#submitAddVille').hide();
		var nomVille = $('#addNomVille').val();
		
		var html = "<tr>";
	  	html += "<td></td>";
	  	html += "<td></td>";
	  	html += "<td><input type='text' name='nomVille' value='' id='ajout-nom-ville'  class='form-control' placeholder='Nom ville'/></td>";
	  	html += "<td><input type='text' name='codePostal' value='' id='ajout-code-postal' class='form-control' placeholder='Code postal'/></td>";
	 	html += "</tr>";
	 	$('#table-ville').append(html);
	});
	
	$('#btnValiderVille').click(function(){
		var nomVille = $('#ajout-nom-ville').val();
		var codePostal = $('#ajout-code-postal').val();
		alert(nomVille+"/ "+codePostal);
		$.ajax({
			url: "http://localhost:8080/sortie.com/rest/ville",
			cache: false,
			type: "POST",
			data: jQuery.param({ nomVille: nomVille,codePostal: codePostal}),
			beforeSend: function(request) {
				request.setRequestHeader("Accept","application/json");
			},
			success: function(data){
				
//				html += '<tr>';
//				html += "<td><input type='radio' name='radio' value='"+data[i]['id']+"'/></td>";
//				html += '<td>' + data[i]["id"] + '</td>';
//				html += '<td>' + data[i]["nomVille"] + '</td>';
//				html += '<td>' + data[i]["codePostal"] + '</td>';
//				html += "</tr>";
//
//
//				$('#table-ville').append(html);
				
				refreshVilleTable();
			}
		});
	});
	
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
			console.log(JSON.stringify(data));
	   		for( var i = 0; i < data.length; i++) {
   				html += '<tr>';
   				html += "<td><input type='radio' name='radio' value='"+data[i]['id']+"'/></td>";
    	   		html += '<td>' + data[i]["id"] + '</td>';
    	   		html += '<td id="villenom' +data[i]["id"]+ '">' + data[i]["nomVille"] + '</td>';
    	   		html += '<td id="codepostal'+ data[i]["id"] +'">' + data[i]["codePostal"] + '</td>';
	    	 	html += "</tr>";
    	 	}
    	 	$('#table-ville').html(html);
		  }
		});
	}
	
	
	
});