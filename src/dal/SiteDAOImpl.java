package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bo.Site;

public class SiteDAOImpl implements SiteDAO{
	
	private final String INSERTONE = "INSERT INTO sites(nom_site) VALUES(?);";
	private final String GETALL = "SELECT * FROM sites;";
	private final String DELETEONEBYID = "DELETE FROM sites WHERE no_site=?;";
	private final String UPDATESITE = "UPDATE sites SET nom_site=? where no_site=?";

	@Override
	public Site createSite(Site unSite) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTONE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, unSite.getNom());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				unSite.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unSite;
	}

	@Override
	public ArrayList<Site> getAll() throws SQLException {
		ArrayList<Site> listeSites = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETALL);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeSites.add(mapSite(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeSites;
	}

	@Override
	public Boolean deleteOneById(Site unSite) throws SQLException {
		Boolean state = false;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETEONEBYID);
			pstmt.setInt(1, unSite.getId());
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
	
	private Site mapSite(ResultSet rs) {
		Site unSite = new Site();
		try {
			unSite.setId(rs.getInt(1));
			unSite.setNom(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unSite;
	}

	@Override
	public Site updateSite(Site unSite) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATESITE);
			pstmt.setString(1, unSite.getNom());
			pstmt.setInt(2, unSite.getId());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unSite;
	}
	
}
