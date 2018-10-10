package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.map.ser.StdSerializers.UtilDateSerializer;

import bo.Inscrit;
import bo.Participant;
import bo.Sortie;
import bo.Inscrit;

public class InscritDAOImpl implements InscritDAO{
	private final String INSERTONE = "INSERT INTO INSCRIPTIONS(nom_inscrit) VALUES(?)";
	private final String INSERTINSCRIT = "INSERT INTO INSCRIPTIONS(date_inscription,sorties_no_sortie,participants_no_participant) VALUES(?,?,?)";
	private final String GETALL ="SELECT date_inscription,sorties_no_sortie,participants_no_participant FROM INSCRIPTIONS";
	private final String DELETEONEBYID = "DELETE FROM INSCRIPTIONS WHERE sorties_no_sortie=? and participants_no_participant=?;";
	private final String UPDATESITE = "UPDATE INSCRIPTIONS SET nom_inscrit=? where no_inscrit=?";
	private final String INSCRITONEUSERTOONESORTIE = "INSERT INTO inscriptions(date_inscription, sorties_no_sortie, participants_no_participant) VALUES(?,?,?);";
	private final String DESINSCRITUSERTOONESORTIE = "DELETE FROM inscriptions WHERE sorties_no_sortie=? and participants_no_participant=?";
	private final String ISINSCRIT = "select count(*) from INSCRIPTIONS where sorties_no_sortie=? and participants_no_participant=?;";
	private final String GETNBINSCRIT = "SELECT count(*) from inscriptions WHERE sorties_no_sortie=?;";

	@Override
	public ArrayList<Inscrit> getAll() throws SQLException {
		ArrayList<Inscrit> listeInscrits = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETALL, PreparedStatement.RETURN_GENERATED_KEYS);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeInscrits.add(mapInscrit(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeInscrits;
	}


	private Inscrit mapInscrit(ResultSet rs) {
		Inscrit unInscrit = new Inscrit();
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unInscrit;
	}

	@Override
	public Boolean deleteOne(Inscrit unInscrit) throws SQLException {
		Boolean state = false;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DESINSCRITUSERTOONESORTIE);
			pstmt.setInt(1, unInscrit.getSortie().getId());
			pstmt.setInt(2, unInscrit.getParticipant().getId());
			int nbLigne = pstmt.executeUpdate();
			if(nbLigne == 1) {
				state = true;
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
	public Inscrit InscritOneUserToOneSortie(Inscrit unInscrit) throws SQLException {
		Inscrit inscrit = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			Date today = new Date(Calendar.getInstance().getTime().getTime()); 
			PreparedStatement pstmt = cnx.prepareStatement(INSCRITONEUSERTOONESORTIE);
			pstmt.setDate(1, today);
			pstmt.setInt(2, unInscrit.getSortie().getId());
			pstmt.setInt(3, unInscrit.getParticipant().getId());
			int nbLigne = pstmt.executeUpdate();
			if(nbLigne == 1) {
				inscrit = unInscrit;
				inscrit.setDateInscription(today);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return inscrit;
	}


	@Override
	public Boolean isInscrit(Inscrit unInscrit) throws SQLException {
		Boolean state = false;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(ISINSCRIT);
			pstmt.setInt(1, unInscrit.getSortie().getId());
			pstmt.setInt(2, unInscrit.getParticipant().getId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) == 1) {
					state = true;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return state;
	}
	
	public int getNbInscrit(Inscrit unInscrit) throws SQLException{
		int nbInscription = 0;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETNBINSCRIT);
			pstmt.setInt(1, unInscrit.getSortie().getId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				nbInscription = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return nbInscription;
	}
}
