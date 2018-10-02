package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bo.Ville;

public class VilleDAOImpl implements VilleDAO{
private final String GETALL ="SELECT * FROM VILLES;";
	
	public ArrayList<Ville> getAll() throws SQLException{
		ArrayList<Ville> listeVille = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETALL, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeVille.add(mapPartipant(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeVille;
		
	}
	
	private Ville mapPartipant(ResultSet rs) {
		Ville unVille = new Ville();
		try {
			unVille.setId(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unVille;
	}

}
