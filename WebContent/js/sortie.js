$(document).ready(function(){
	
	$('#gestionSite').hide();
	$('#gestionVille').hide();
	
	$('#btnVille').click(function(){
		$('#gestionSite').hide();
		$('#gestionVille').toggle();
	});
	
	$('#btnSite').click(function(){
		$('#gestionVille').hide();
		$('#gestionSite').toggle();
	});
});