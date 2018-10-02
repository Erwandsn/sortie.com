package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/Ville")
public class GestionVille 
{
	private static List<String> listeVille;
	static
	{

	}
	
	@GET
	public String getVille()
	{
		return listeVille.toString();
	}
	
	@GET
	@Path("/rouge")
	public String getCouleurRouge()
	{
		return "rouge";
	}
	
	@GET
	@Path("/{id : \\d+}")
	public String getCouleur(@PathParam("id") int id)
	{
		return "La vil de l'id " + id + " est " + listeVille.get(id);
	}
}