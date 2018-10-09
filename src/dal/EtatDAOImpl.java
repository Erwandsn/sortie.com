package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bo.Etat;
import bo.Etat;

public class EtatDAOImpl implements EtatDAO{
	private final String INSERTONE = "INSERT INTO ETATS(libelle) VALUES(?)";
	private final String INSERTETAT = "INSERT INTO ETATS(libelle) VALUES(?,?)";
	private final String GETALL ="SELECT no_etat,libelle FROM ETATS";
	private final String DELETEONEBYID = "DELETE FROM ETATS WHERE no_etat=?;";
	private final String UPDATEETAT = "UPDATE ETATS SET libelle=? where no_etat=?";


	@Override
	public Etat createEtat(Etat unEtat) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTETAT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, unEtat.getLibelle());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				unEtat.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unEtat;
	}

	@Override
	public ArrayList<Etat> getAll() throws SQLException {
		ArrayList<Etat> listeEtats = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETALL, PreparedStatement.RETURN_GENERATED_KEYS);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeEtats.add(mapEtat(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeEtats;
	}


	private Etat mapEtat(ResultSet rs) {
		Etat unEtat = new Etat();
		try {
			unEtat.setId(rs.getInt(1));
			unEtat.setLibelle(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unEtat;
	}

	@Override
	public ArrayList<Etat> searchByNomEtat(String nomEtat) throws SQLException {
		String GETSEARCH ="SELECT no_etat,libelle FROM ETATS where libelle like '"+nomEtat+"%';";
		ArrayList<Etat> listeEtats = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomEtat + "'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeEtats.add(mapEtat(rs));
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeEtats;
	}

	@Override
	public Etat searchEtat(String nomEtat) throws SQLException {
		String GETSEARCH ="SELECT no_etat,libelle FROM ETATS where libelle like '"+nomEtat+"%';";
		Etat etat = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomEtat + "'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				etat= mapEtat(rs);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return etat;
	}
	
	@Override
	public Boolean deleteOneById(Etat unEtat) throws SQLException {

		Boolean state = false;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETEONEBYID);
			pstmt.setInt(1, unEtat.getId());
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
	public Etat updateEtat(Etat unEtat) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATEETAT);
			pstmt.setString(1, unEtat.getLibelle());
			pstmt.setInt(2, unEtat.getId());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unEtat;
	}

	@Override
	public Etat ajoutEtat(String nomEtat) throws SQLException {
		// TODO Auto-generated method stub
		Etat unEtat = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTETAT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,nomEtat);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				unEtat.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unEtat;
	}

	@Override
	public Etat searchEtat(int id) throws SQLException {
		String GETSEARCH ="SELECT no_etat,libelle FROM ETATS where no_etat = '"+id+"';";
		Etat etat = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				etat= mapEtat(rs);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return etat;
	}
}
