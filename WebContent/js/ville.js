$(document).ready(function(){
	
	
//	AJAX Function
	$('#rechercher').click(function(){
		var nomVille = $('#hier').val();
		$.ajax({
			  url: "http://localhost:8080/sortie.com/rest/ville/"+nomVille,
			  cache: false,
			  type: "GET",
			  data: jQuery.param({ nomVille: nomVille}),
			  beforeSend: function(request) {
			  	request.setRequestHeader("Accept","application/json");
			  },
			  success: function(data){
//				  alert("coucou"+nomVille);
				  	var html = "";
				  	console.log(JSON.stringify(data));
				  	for( var i = 0; i < data.length; i++) {
	    	   			html += '<tr>';
		    	   		html += '<td>' + data[i]["id"] + '</td>';
		    	   		html += '<td>' + data[i]["nomVille"] + '</td>';
		    	   		html += '<td>' + data[i]["codePostal"] + '</td>';
			    	 	html += "</tr>";
		    	 	}
				  	
		    	 	$('#table-ville').append(html);
		    	 	$('#hier').val("");
			  }
		});
	});
});