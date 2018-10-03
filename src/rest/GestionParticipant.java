package rest;

import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import bll.ParticipantManager;
import bo.Participant;

@Path("/profile")
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
	public Participant addNote(@FormParam("nomParticipant") String nom) throws ParseException {
		Participant unParticipant = new Participant();
		unParticipant.setNom(nom);
		unParticipant = getMgr().createParticipant(unParticipant);
		return unParticipant;
	}
	
}
