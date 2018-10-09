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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bll.EtatManager;

import bo.Etat;

@Path("/etat")
public class GestionEtat 
{
private EtatManager mgr;
	
	public GestionEtat() {
		this.mgr = new EtatManager();
	}
	
	public EtatManager getMgr() {
		return this.mgr;
	}
	
	@GET
	@Path("/{nomEtat }")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Etat> getRecherche(@PathParam("nomEtat") String nomEtat){
		ArrayList<Etat> listEtats = new ArrayList<>();
		listEtats = getMgr().getSearchByNomEtat(nomEtat);
		return listEtats;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Etat> getAll(){
		ArrayList<Etat> listEtats = new ArrayList<>();
		listEtats  = getMgr().getAll();
		return listEtats;
	}
			
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Etat addNote(@FormParam("libelle") String libelle) throws ParseException {
		Etat unEtat = new Etat();
		unEtat.setLibelle(libelle);
		unEtat = getMgr().createEtat(unEtat);
		return unEtat;
	}
	
	
	@POST
	@Path("/ajoutEtat")
	@Produces(MediaType.APPLICATION_JSON)
	public Etat ajoutEtat(@FormParam("libelle") String libelle) throws ParseException {
		Etat unEtat = new Etat();
		unEtat.setLibelle(libelle);
		unEtat = getMgr().createEtat(unEtat);
		return unEtat;
	}
	
	
	@DELETE
	@Path("/delete/{idEtat : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean deleteOne(@PathParam("idEtat") int idEtat) {
		Etat unEtat = new Etat();
		unEtat.setId(idEtat);
		Boolean state = getMgr().deleteEtat(unEtat);
		return state;
	}
	
	@PUT
	@Path("/update/{idEtat : \\d+}/{nomModif}")
	@Produces(MediaType.APPLICATION_JSON)
	public Etat updateEtat(@PathParam("idEtat") int id, @PathParam("libelle") String libelle) {
		Etat unEtat = new Etat();
		unEtat.setId(id);
		unEtat.setLibelle(libelle);
		unEtat.setLibelle(getMgr().updateEtat(unEtat).getLibelle());
		return unEtat;
	}
}