package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Participant;
import bo.Participant;
import dal.DaoFactory;
import dal.ParticipantDAO;

public class ParticipantManager {
	
	private ParticipantDAO participantDao;
	
	public ParticipantManager() {
		this.participantDao = DaoFactory.getParticipantDao();
	}
	
	private ParticipantDAO getParticipantDao() {
		return this.participantDao;
	}
	
	public ArrayList<Participant> getAll() {
		ArrayList<Participant> listeParticipant = new ArrayList<>();
		ParticipantDAO dao = getParticipantDao();
		try {
			listeParticipant = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeParticipant;
	}
	
	public Participant createParticipant(Participant unParticipant) {
		Participant createdParticipant = new Participant();
		try {
			getParticipantDao().createParticipant(unParticipant);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdParticipant;
	}
}
