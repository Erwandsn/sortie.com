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

import bll.InscritManager;

import bo.Inscrit;

@Path("/inscrit")
public class GestionInscrit 
{
private InscritManager mgr;
	
	public GestionInscrit() {
		this.mgr = new InscritManager();
	}
	
	public InscritManager getMgr() {
		return this.mgr;
	}
	
	@GET
	@Path("/{nomInscrit }")
	public ArrayList<Inscrit> getRecherche(@PathParam("nomInscrit") String nomInscrit){
		ArrayList<Inscrit> listInscrits = new ArrayList<>();
		listInscrits = getMgr().getSearchByNomInscrit(nomInscrit);
		return listInscrits;
	}
	
	@GET
	public ArrayList<Inscrit> getAll(){
		ArrayList<Inscrit> listInscrits = new ArrayList<>();
		listInscrits  = getMgr().getAll();
		return listInscrits;
	}
			
	@POST
	public Inscrit addNote(@FormParam("nomInscrit") String nom) throws ParseException {
		Inscrit unInscrit = new Inscrit();
		unInscrit.setNomInscrit(nom);
		unInscrit = getMgr().createInscrit(unInscrit);
		return unInscrit;
	}
	
	
	@POST
	@Path("/ajoutInscrit")
	public Inscrit ajoutInscrit(@FormParam("nomInscrit") String nom,@FormParam("sortie") String sortie) throws ParseException {
		Inscrit unInscrit = new Inscrit();
		unInscrit.setParticipant(nom);
		unInscrit.setSortie(codePostal);
		unInscrit = getMgr().createInscrit(unInscrit);
		return unInscrit;
	}
	
	
	@DELETE
	@Path("/delete/{idInscrit : \\d+}")
	public Boolean deleteOne(@PathParam("idInscrit") int idInscrit) {
		Inscrit unInscrit = new Inscrit();
		unInscrit.setId(idInscrit);
		Boolean state = getMgr().deleteInscrit(unInscrit);
		return state;
	}
	
	@PUT
	@Path("/update/{idInscrit : \\d+}/{nomModif}")
	public Inscrit updateInscrit(@PathParam("idInscrit") int id, @PathParam("nomModif") String nomInscrit) {
		Inscrit unInscrit = new Inscrit();
		unInscrit.setId(id);
		unInscrit.setNomInscrit(nomInscrit);
		unInscrit.setNomInscrit(getMgr().updateInscrit(unInscrit).getNomInscrit());
		return unInscrit;
	}
}