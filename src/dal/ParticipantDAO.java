package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Participant;
import bo.Participant;

public interface ParticipantDAO {
	
	public Participant createParticipant(Participant unParticipant) throws SQLException;
	
	public ArrayList<Participant> getAll() throws SQLException;
	
}
