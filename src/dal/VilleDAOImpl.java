package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bo.Ville;
import bo.Ville;

public class VilleDAOImpl implements VilleDAO{
	private final String INSERTONE = "INSERT INTO VILLES(nom_ville) VALUES(?)";
	private final String GETALL ="SELECT no_ville,nom_ville,code_postal FROM VILLES";


	@Override
	public Ville createVille(Ville unVille) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTONE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, unVille.getNomVille());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				unVille.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unVille;
	}

	@Override
	public ArrayList<Ville> getAll() throws SQLException {
		ArrayList<Ville> listeVilles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETALL, PreparedStatement.RETURN_GENERATED_KEYS);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeVilles.add(mapVille(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeVilles;
	}


	private Ville mapVille(ResultSet rs) {
		Ville unVille = new Ville();
		try {
			unVille.setId(rs.getInt(1));
			unVille.setNomVille(rs.getString(2));
			unVille.setCodePostal(rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unVille;
	}

	@Override
	public ArrayList<Ville> searchByNomVille(String nomVille) throws SQLException {
		String GETSEARCH ="SELECT no_ville,nom_ville,code_postal FROM VILLES where nom_ville like '"+nomVille+"%';";
		ArrayList<Ville> listeVilles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomVille + "'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeVilles.add(mapVille(rs));
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeVilles;
	}

	@Override
	public Ville searchVille(String nomVille) throws SQLException {
		String GETSEARCH ="SELECT no_ville,nom_ville,code_postal FROM VILLES where nom_ville like '"+nomVille+"%';";
		Ville ville = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomVille + "'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				ville= mapVille(rs);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return ville;
	}
}
