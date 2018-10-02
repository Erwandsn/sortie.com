$(document).ready(function(){
//Display Functions
	
	$('#gestionSite').hide();
	$('#gestionVille').hide();
	
	$('#btnVille').click(function(){
		$('#gestionSite').hide();
		$('#gestionVille').toggle();
	});
	
	$('#btnSite').click(function(){
		$('#gestionVille').hide();
		$('#gestionSite').toggle();
//		On récupère la liste des Site existants en base de données
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
		    	   		html += '<td>' + data[i]["id"] + '</td>';
		    	   		html += '<td>' + data[i]["nom"] + '</td>';
		    	   		html += '<td> <div class="dropdown"><button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Actions<span class="caret"></span></button><ul class="dropdown-menu"><li><a href="#">Modifier</a></li><li><a href="#">Supprimer</a></li></ul></div></td>'
			    	 	html += "</tr>";
		    	 	}
		    	 	$('#tableSite').html(html);
			  }
		});
	});
	
	
//	AJAX Function
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
				  	var html = "";
				  	alert("ca marche");
				  	console.log(JSON.stringify(data));
//				  	html += '<tr>';
//    	  			html += '<td><input type="radio" name="radio" value="'+data["id"]+'"/></td>';
//	    	   		html += '<td>' + data["id"] + '</td>';
//	    	   		html += '<td>' + data["titre"] + '</td>';
//	    	   		html += '<td>' + str + '</td>';
//		    	 	html += "</tr>";
				  	
		    	 	$('#tableNote').append(html);
		    	 	$('#addNomSite').val("");
			  }
		});
	});
});