package rest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.VilleManager;
import bo.Ville;

@Path("/ville")
public class GestionVille 
{
private VilleManager mgr;
	
	public GestionVille() {
		this.mgr = new VilleManager();
	}
	
	public VilleManager getMgr() {
		return this.mgr;
	}
	
	@GET
	@Path("/{nomVille }")
	public ArrayList<Ville> getAll(@PathParam("nomVille") String nomVille){
		ArrayList<Ville> listVilles = new ArrayList<>();
		listVilles = getMgr().getSearchByNomVille(nomVille);
		return listVilles;
	}
			
	@POST
	public Ville addNote(@FormParam("nomVille") String nom) throws ParseException {
		Ville unVille = new Ville();
		unVille.setNomVille(nom);
		unVille = getMgr().createVille(unVille);
		return unVille;
	}
}