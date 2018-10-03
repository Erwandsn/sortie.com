package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bo.Participant;

public class ParticipantDAOImpl implements ParticipantDAO{
	private final String INSERTONE = "INSERT INTO PARTICIPANTS(pseudo,nom,prenom,telephone,mail,mot_de_passe,ville) VALUES(?);";
	private final String GETALL ="SELECT * FROM Participants;";

	public ArrayList<Participant> getAll() throws SQLException{
		ArrayList<Participant> listeParticipant = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETALL, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeParticipant.add(mapPartipant(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeParticipant;

	}

	private Participant mapPartipant(ResultSet rs) {
		Participant unParticipant = new Participant();
		try {
			unParticipant.setId(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unParticipant;
	}

	@Override
	public Participant createParticipant(Participant unParticipant) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTONE, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, unParticipant.getPseudo());
			pstmt.setString(2, unParticipant.getPrenom());
			pstmt.setString(3, unParticipant.getNom());
			pstmt.setString(4, unParticipant.getTelephone());
			pstmt.setString(5, unParticipant.getMail());
			pstmt.setString(6, unParticipant.getMotDePase());
			pstmt.setString(7, unParticipant.getVille().getNomVille());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				unParticipant.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unParticipant;
	}
}
