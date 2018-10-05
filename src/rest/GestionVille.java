package rest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	public ArrayList<Ville> getRecherche(@PathParam("nomVille") String nomVille){
		ArrayList<Ville> listVilles = new ArrayList<>();
		listVilles = getMgr().getSearchByNomVille(nomVille);
		return listVilles;
	}
	
	@GET
	public ArrayList<Ville> getAll(){
		ArrayList<Ville> listVilles = new ArrayList<>();
		listVilles  = getMgr().getAll();
		return listVilles;
	}
			
	@POST
	public Ville addNote(@FormParam("nomVille") String nom) throws ParseException {
		Ville unVille = new Ville();
		unVille.setNomVille(nom);
		unVille = getMgr().createVille(unVille);
		return unVille;
	}
	
	
	@POST
	@Path("/ajoutVille")
	public Ville ajoutVille(@FormParam("nomVille") String nom,@FormParam("codePostal") String codePostal) throws ParseException {
		Ville unVille = new Ville();
		unVille.setNomVille(nom);
		unVille.setCodePostal(codePostal);
		unVille = getMgr().createVille(unVille);
		return unVille;
	}
	
	
	@DELETE
	@Path("/delete/{idVille : \\d+}")
	public Boolean deleteOne(@PathParam("idVille") int idVille) {
		Ville unVille = new Ville();
		unVille.setId(idVille);
		Boolean state = getMgr().deleteVille(unVille);
		return state;
	}
	
	@PUT
	@Path("/update/{idVille : \\d+}/{nomModif}")
	public Ville updateVille(@PathParam("idVille") int id, @PathParam("nomModif") String nomVille) {
		Ville unVille = new Ville();
		unVille.setId(id);
		unVille.setNomVille(nomVille);
		unVille.setNomVille(getMgr().updateVille(unVille).getNomVille());
		return unVille;
	}
}