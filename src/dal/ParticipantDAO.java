package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Participant;
import bo.Ville;
import bo.Participant;

public interface ParticipantDAO {
	
	public Participant createParticipant(Participant unParticipant) throws SQLException;
	
	public ArrayList<Participant> getAll() throws SQLException;
	
	public Boolean authentification(Participant unParticipant) throws SQLException;
	
	public Participant getInfoAuthenticatedUser(Participant unParticipant) throws SQLException;
	
	public Participant updateParticipant(Participant unParticipant) throws SQLException;
	
	public Participant searchParticipant(int id) throws SQLException;

	public Participant getByPseudo(Participant unParticipant) throws SQLException;
	
	public Participant getParticipantById(Participant unParticipant) throws SQLException;

}
