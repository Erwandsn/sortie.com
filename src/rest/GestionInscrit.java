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
import bo.Participant;
import bo.Sortie;

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
	@Path("/isinscrit/{idSortie : \\d+}/{idParticipant : \\d+}")
	public Boolean isInscrit(@PathParam("idSortie") int idSortie, @PathParam("idParticipant") int idParticipant) {
		Boolean state = false;
		Inscrit unInscrit = new Inscrit();
		Sortie laSortie = new Sortie();
		laSortie.setId(idSortie);
		Participant leParticipant = new Participant();
		leParticipant.setId(idParticipant);
		unInscrit.setParticipant(leParticipant);
		unInscrit.setSortie(laSortie);
		state = getMgr().isInscrit(unInscrit);
		return state;
	}
	
	@GET
	@Path("/nbinscrit/{idSortie : \\d+}")
	public int isInscrit(@PathParam("idSortie") int idSortie) {
		int nb = 0;
		Inscrit unInscrit = new Inscrit();
		Sortie laSortie = new Sortie();
		laSortie.setId(idSortie);
		unInscrit.setSortie(laSortie);
		nb = getMgr().getNbInscrit(unInscrit);
		return nb;
	}
			
	@POST
	public Inscrit addInscription(@FormParam("idSortie") int idSortie, @FormParam("idParticipant") int idParticipant) throws ParseException {
		Inscrit unInscrit = new Inscrit();
		Sortie laSortie = new Sortie();
		Inscrit addedInscription = null;
		laSortie.setId(idSortie);
		Participant leParticipant = new Participant();
		leParticipant.setId(idParticipant);
		unInscrit.setParticipant(leParticipant);
		unInscrit.setSortie(laSortie);
		unInscrit = getMgr().InscritOneUserToOneSortie(unInscrit);
		return addedInscription;
	}
	
	@DELETE
	@Path("/delete/{idSortie : \\d+}/{idParticipant : \\d+}")
	public Boolean deleteOne(@PathParam("idSortie") int idSortie, @PathParam("idParticipant") int idParticipant) {
		Boolean state = false;
		Inscrit unInscrit = new Inscrit();
		Sortie laSortie = new Sortie();
		laSortie.setId(idSortie);
		Participant leParticipant = new Participant();
		leParticipant.setId(idParticipant);
		unInscrit.setParticipant(leParticipant);
		unInscrit.setSortie(laSortie);
		state = getMgr().deleteInscrit(unInscrit);
		return state;
	}
	
	@PUT
	@Path("/update/{idInscrit : \\d+}/{nomModif}")
	public Inscrit getAll(@PathParam("idInscrit") int id, @PathParam("nomModif") String nomInscrit) {
		Inscrit unInscrit = new Inscrit();
		return unInscrit;
	}
}