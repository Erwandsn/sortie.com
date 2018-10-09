package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bll.ParticipantManager;
import bo.Sortie;
import bo.Sortie;

public class SortieDAOImpl implements SortieDAO{
	private final String INSERTSORTIE = "INSERT INTO SORTIES(nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,ville"
			+ ") VALUES(?,?,?,?,?,?,?,?,?,?)";
//	private final String INSERTSORTIE = "INSERT INTO SORTIES(nom_ville,code_postal) VALUES(?,?)";
	private final String GETALL ="SELECT no_sortie,nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,ville FROM SORTIES";
	private final String DELETEONEBYID = "DELETE FROM SORTIES WHERE no_sortie=?;";
	private final String UPDATESORTIE = "UPDATE SORTIES SET nom=? where no_sortie=?";


	@Override
	public Sortie createSortie(Sortie unSortie) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTSORTIE, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, unSortie.getNom());
//			pstmt.setString(2, unSortie.getDateheureDebut().toString());
			pstmt.setDate(2, (Date) unSortie.getDateheureDebut());
			pstmt.setInt(3, unSortie.getDuree());
			pstmt.setDate(4, (Date) unSortie.getDateLimiteInscription());
//			(4, .toString());
			pstmt.setInt(5, unSortie.getNbInscriptionsMax());
			pstmt.setString(6, unSortie.getInfosSortie() );
			pstmt.setInt(7, unSortie.getOrganisateur().getId());
			pstmt.setInt(8, unSortie.getLieu().getId());
			pstmt.setInt(9, unSortie.getEtat().getId());
			pstmt.setInt(10, unSortie.getVille().getId());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				unSortie.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unSortie;
	}

	@Override
	public ArrayList<Sortie> getAll() throws SQLException {
		ArrayList<Sortie> listeSorties = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETALL, PreparedStatement.RETURN_GENERATED_KEYS);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeSorties.add(mapSortie(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeSorties;
	}


	private Sortie mapSortie(ResultSet rs) {
		Sortie unSortie = new Sortie();
		try {
			unSortie.setId(rs.getInt(1));
			unSortie.setNom(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unSortie;
	}

	@Override
	public ArrayList<Sortie> searchByNomSortie(String nomSortie) throws SQLException {
		String GETSEARCH ="SELECT no_sortie,nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,ville FROM SORTIES where nom like '"+nomSortie+"%';";
		ArrayList<Sortie> listeSorties = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomSortie + "'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeSorties.add(mapSortie(rs));
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeSorties;
	}

	@Override
	public Sortie searchSortie(String nomSortie) throws SQLException {
		String GETSEARCH ="SELECT no_sortie,nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,ville FROM SORTIES nom like '"+nomSortie+"%';";
		Sortie sortie = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomSortie + "'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				sortie= mapSortie(rs);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return sortie;
	}
	
	@Override
	public Boolean deleteOneById(Sortie unSortie) throws SQLException {

		Boolean state = false;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETEONEBYID);
			pstmt.setInt(1, unSortie.getId());
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
	public Sortie updateSortie(Sortie unSortie) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATESORTIE);
			pstmt.setString(1, unSortie.getNom());
			pstmt.setInt(2, unSortie.getId());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unSortie;
	}

	@Override
	public Sortie ajoutSortie(String nomSortie, String codePostal) throws SQLException {
		// TODO Auto-generated method stub
		Sortie unSortie = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTSORTIE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,nomSortie);
			pstmt.setString(2,codePostal);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				unSortie.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unSortie;
	}

	@Override
	public Sortie searchSortie(int id) throws SQLException {
		String GETSEARCH ="SELECT no_sortie,nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,ville FROM SORTIES where no_sortie = '"+id+"';";
		Sortie sortie = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				sortie= mapSortie(rs);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return sortie;
	}
}
