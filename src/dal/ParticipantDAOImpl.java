package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bo.Participant;

public class ParticipantDAOImpl implements ParticipantDAO{
	private final String INSERTONE = "INSERT INTO PARTICIPANTS(pseudo,nom,prenom,telephone,mail,mot_de_passe,ville,administrateur,actif) VALUES(?,?,?,?,?,?,?,?,?);";
	private final String GETALL ="SELECT * FROM Participants;";
	private final String AUTHENTIFICATION = "SELECT * FROM Participants WHERE pseudo=? AND mot_de_passe=?;";

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
			pstmt.setString(6, unParticipant.getMotDePasse());
			pstmt.setInt(7, unParticipant.getVille().getId());
			pstmt.setBoolean(8, false);
			pstmt.setBoolean(9, false);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
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
	
	public Boolean authentification(Participant unParticipant) throws SQLException{
		Boolean state = false;
		Participant participantAuthentifie = new Participant();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(AUTHENTIFICATION);
			pstmt.setString(1, unParticipant.getPseudo());
			pstmt.setString(2, unParticipant.getMotDePasse());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				participantAuthentifie = mapPartipant(rs);
				state = true;
			}else {
				state = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return state;
	}
	
	@Override
	public Participant getInfoAuthenticatedUser(Participant unParticipant) throws SQLException {
		Participant user = new Participant();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(AUTHENTIFICATION);
			pstmt.setString(1, unParticipant.getPseudo());
			pstmt.setString(2, unParticipant.getMotDePasse());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				user = mapPartipant(rs);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return user;
	}
	
	private Participant mapPartipant(ResultSet rs) {
		Participant unParticipant = new Participant();
		try {
			unParticipant.setId(rs.getInt(1));
			unParticipant.setPseudo(rs.getString(2));
			unParticipant.setNom(rs.getString(3));
			unParticipant.setPrenom(rs.getString(4));
			unParticipant.setTelephone(rs.getString(5));
			unParticipant.setMail(rs.getString(6));
			unParticipant.setAdmin(rs.getBoolean(7));
			unParticipant.setActif(rs.getBoolean(8));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unParticipant;
	}

	
}
