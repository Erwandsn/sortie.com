package rest;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;

import bll.ParticipantManager;
import bll.VilleManager;
import bo.Participant;
import bo.Ville;
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
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Participant> getAll(){
		ArrayList<Participant> listParticipants = new ArrayList<>();
		listParticipants = getMgr().getAll();
		return listParticipants;
	}
			
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Participant addParticipant(@FormParam("pseudo") String pseudo, @FormParam("prenom") String prenom, @FormParam("nom") String nom,@FormParam("telephone") String telephone,
			@FormParam("email") String email,@FormParam("motDePasse") String motDePasse,@FormParam("ville") int ville) throws ParseException {
		Participant unParticipant = new Participant();
		unParticipant.setPseudo(pseudo);
		unParticipant.setPrenom(prenom);
		unParticipant.setNom(nom);
		unParticipant.setTelephone(telephone);
		unParticipant.setMail(email);
		unParticipant.setMotDePasse(motDePasse);
		Ville uneVille = new Ville();
		uneVille.setId(ville);
		unParticipant.setVille(uneVille);
		unParticipant = getMgr().createParticipant(unParticipant);
		boolean vf = false;
		unParticipant.setAdmin(vf);
		return unParticipant;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Participant updateParticipant(@FormParam("pseudo") String pseudo, @FormParam("prenom") String prenom, @FormParam("nom") String nom,@FormParam("telephone") String telephone,
			@FormParam("email") String email,@FormParam("ville") int ville) throws ParseException{
		Participant user = new Participant();
		Ville villeParticipant = new Ville();
		villeParticipant.setId(ville);
		user.setPseudo(pseudo);
		user.setPrenom(prenom);
		user.setNom(nom);
		user.setTelephone(telephone);
		user.setMail(email);
		user.setVille(villeParticipant);
		user = getMgr().updateParticipant(user);
		return user;
	}
	
	
}
