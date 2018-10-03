
<div class="container-fluid">
	<div class="row form-group">
		<div class="col-lg-1 col-md-1 col-xs-1">Filtrer les villes</div>
	</div>
	<div class="row form-group">
		<div class="col-lg-2 col-md-2 col-xs-2">Le nom contient:</div>
		<div class="col-lg-2 col-md-2 col-xs-2">
			<input class="form-control" id="hier" aria-describedby="hier"
				placeholder="Hier">
		</div>
		<form action='' method='POST' class='form-inline'>
			<div class='form-group'>
				<button type='button' id='rechercher' class='btn btn-primary'>Rechercher</button>
			</div>
		</form>
	</div>
	<div class="row form-group">
		<table class="table" id="table-ville">
			<thead>
				<tr>
					<th scope="col">Ville</th>
					<th scope="col">Code postal</th>
					<th scope="col">Action</th>

				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
<script src='/sortie.com/js/ville.js'></script>