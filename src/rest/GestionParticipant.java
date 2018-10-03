package rest;

import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import bll.ParticipantManager;
import bll.VilleManager;
import bo.Participant;
import dal.VilleDAO;
import dal.VilleDAOImpl;

@Path("/creationParticipant")
public class GestionParticipant {
	
	private ParticipantManager mgr;
	
	public GestionParticipant() {
		this.mgr = new ParticipantManager();
	}
	
	public ParticipantManager getMgr() {
		return this.mgr;
	}
	
	@GET
	public ArrayList<Participant> getAll(){
		ArrayList<Participant> listParticipants = new ArrayList<>();
		listParticipants = getMgr().getAll();
		return listParticipants;
	}
			
	@POST
	public Participant addParticipant(@FormParam("pseudo") String pseudo, @FormParam("prenom") String prenom, @FormParam("nom") String nom,@FormParam("telephone") String telephone,
			@FormParam("email") String email,@FormParam("motDePasse") String motDePasse,@FormParam("ville") String ville) throws ParseException {
		Participant unParticipant = new Participant();
		unParticipant.setPseudo(pseudo);
		unParticipant.setPrenom(prenom);
		unParticipant.setNom(nom);
		unParticipant.setTelephone(telephone);
		unParticipant.setMail(email);
		unParticipant.setMotDePasse(motDePasse);
		VilleManager  manager = new VilleManager();
		unParticipant.setVille(manager.searchVille(ville));
		unParticipant = getMgr().createParticipant(unParticipant);
		boolean vf = false;
		unParticipant.setAdmin(vf);
		return unParticipant;
	}
	
}
