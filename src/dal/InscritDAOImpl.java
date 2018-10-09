package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


	@Override
	public Inscrit createInscrit(Inscrit unInscrit) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTINSCRIT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, unInscrit.getNomInscrit());
			pstmt.setString(2, unInscrit.getCodePostal());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
//			if(rs.next())
//			{
//				unInscrit.setId(rs.getInt(1));
//			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unInscrit;
	}

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
			unInscrit.setId(rs.getInt(1));
			unInscrit.setNomInscrit(rs.getString(2));
			unInscrit.setCodePostal(rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unInscrit;
	}

	@Override
	public ArrayList<Inscrit> searchByNomInscrit(String nomInscrit) throws SQLException {
		String GETSEARCH ="SELECT no_inscrit,nom_inscrit,code_postal FROM INSCRIPTIONS where nom_inscrit like '"+nomInscrit+"%';";
		ArrayList<Inscrit> listeInscrits = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomInscrit + "'");
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

	@Override
	public Inscrit searchInscrit(String nomInscrit) throws SQLException {
		String GETSEARCH ="SELECT no_inscrit,nom_inscrit,code_postal FROM INSCRIPTIONS where nom_inscrit like '"+nomInscrit+"%';";
		Inscrit inscrit = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomInscrit + "'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				inscrit= mapInscrit(rs);
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
	public Boolean deleteOne(Sortie sortie,Participant participant) throws SQLException {

		Boolean state = false;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETEONEBYID);
			pstmt.setInt(1, sortie.getId());
			pstmt.setInt(2, participant.getId());
			pstmt.executeUpdate();
			state = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return state;

	}

	@Override
	public Inscrit updateInscrit(Inscrit unInscrit) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATESITE);
//			pstmt.setString(1, unInscrit.getNomInscrit());
//			pstmt.setInt(2, unInscrit.getId());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unInscrit;
	}

	@Override
	public Inscrit ajoutInscrit(String nomInscrit, String participant) throws SQLException {
		// TODO Auto-generated method stub
		Inscrit unInscrit = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTINSCRIT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,nomInscrit);
			pstmt.setString(2,participant);
			pstmt.executeQuery();
//			ResultSet rs = pstmt.getGeneratedKeys();
//			if(rs.next())
//			{
//				unInscrit.setId(rs.getInt(1));
//			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unInscrit;
	}

	@Override
	public Inscrit searchInscrit(int id) throws SQLException {
		String GETSEARCH ="SELECT no_inscrit,nom_inscrit,code_postal FROM INSCRIPTIONS where no_inscrit = '"+id+"';";
		Inscrit inscrit = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				inscrit= mapInscrit(rs);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return inscrit;
	}
}
