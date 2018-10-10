package dal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import bll.EtatManager;
import bll.ParticipantManager;
import bo.Etat;
import bo.Lieu;
import bo.Participant;
import bo.Sortie;
import bo.Ville;
import bo.Sortie;

public class SortieDAOImpl implements SortieDAO{
	private final String INSERTSORTIE = "INSERT INTO SORTIES(nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,ville"
			+ ") VALUES(?,?,?,?,?,?,?,?,?,?)";
	//	private final String INSERTSORTIE = "INSERT INTO SORTIES(nom_ville,code_postal) VALUES(?,?)";
	private final String GETALL ="SELECT no_sortie,nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,ville FROM SORTIES";
	private final String DELETEONEBYID = "DELETE FROM SORTIES WHERE no_sortie=?;";
	private final String UPDATESORTIE = "UPDATE SORTIES SET nom=? where no_sortie=?";
	private final String GETONEBYID = "SELECT no_sortie,nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,ville FROM sorties WHERE no_sortie=?;";
	private final String GETPARTICIPANTOFSORTIE = "SELECT no_participant from participants, sorties, INSCRIPTIONS where no_sortie = sorties_no_sortie and participants_no_participant = no_participant and no_sortie=?;";


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
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String query ="SELECT no_sortie,nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,ville FROM SORTIES where datecloture> '"+new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())+"' ";

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

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
			java.sql.Timestamp ts = rs.getTimestamp(3);
			if(ts != null) {
				Date dateDebut = new Date(ts.getTime());
				unSortie.setDateheureDebut(dateDebut);
			}

			unSortie.setDuree(rs.getInt(4));
			java.sql.Timestamp tsfin = rs.getTimestamp(5);
			if(tsfin != null) {
				Date dateFin = new Date(tsfin.getTime());
				unSortie.setDateLimiteInscription(dateFin);
			}


			unSortie.setNbInscriptionsMax(rs.getInt(6));
			unSortie.setInfosSortie(rs.getString(7));
//			On r�cup�re les informations du participant
			ParticipantManager participantManager = new ParticipantManager();
			Participant userOrga = null;
			Participant orga = new Participant();
			orga.setId(rs.getInt(8));
			userOrga = participantManager.getParticipantById(orga);
			unSortie.setOrganisateur(userOrga);
//			On r�cup�re les informations du lieu
			Lieu lieu = new Lieu();
			lieu.setId(rs.getInt(9));
			unSortie.setLieu(lieu);
//			On r�cup�re les information de l'�tat
			EtatManager etatManager = new EtatManager();
			Etat etat = new Etat();
			etat.setId(rs.getInt(10));
			etat.setLibelle(etatManager.getOneById(etat).getLibelle());
			//On recupere la liste de participant pour cette sortie
			ArrayList<Participant> listeParticipants = getParticipantOfOneSortie(unSortie);
			unSortie.setListeParticipants(listeParticipants);
			unSortie.setEtat(etat);
			Ville ville = new Ville();
			ville.setId(rs.getInt(11));
			unSortie.setVille(ville);
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

	@Override
	public ArrayList<Sortie> search(String site,String recherche, String organisateur, String inscrit, String pasInscrit, String sortiePassee,
			String debut, String fin) throws SQLException {
		String GETSEARCH ="";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Calendar calActif = Calendar.getInstance();
		calActif.add(Calendar.MONTH, 1);
//		Date newDate = DateUtils.addMonths(new Date(), 1);
		
//		  SELECT no_sortie,SORTIES.nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,
//		  lieux_no_lieu,etats_no_etat,SORTIES.ville FROM SORTIES,PARTICIPANTS,SITES  where PARTICIPANTS.no_participant = SORTIES.organisateur 
//		  and PARTICIPANTS.sites_no_site = SITES.no_site except SELECT no_sortie,SORTIES.nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,
//		  lieux_no_lieu,etats_no_etat,SORTIES.ville FROM INSCRIPTIONS,PARTICIPANTS,SORTIES where INSCRIPTIONS.participants_no_participant = PARTICIPANTS.no_participant 
//		  and SORTIES.no_sortie = INSCRIPTIONS.sorties_no_sortie and PARTICIPANTS.no_participant =1 ;
		if(inscrit.equals("true")  && inscrit.equals("false")) {//inscrit
			GETSEARCH ="SELECT no_sortie,SORTIES.nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,SORTIES.ville FROM SORTIES,INSCRIPTIONS,PARTICIPANTS,SITES where INSCRIPTIONS.sorties_no_sortie = SORTIES.no_sortie  and PARTICIPANTS.no_participant = SORTIES.organisateur and PARTICIPANTS.sites_no_site = SITES.no_site "+(!recherche.equals("null")? " AND SORTIES.nom like '"+recherche+"%'": "AND SORTIES.nom like '%'")+""
			 +(!organisateur.equals("null") ? " and organisateur = '"+organisateur+"'" : "")+""
					+(sortiePassee.equals("true") ? " and datecloture between '"+new SimpleDateFormat("yyyy-MM-dd").format(calActif.getTime())+"'" : "")+"' and '"+date+" "+(!site.equals("null")?" and SITES.nom_site like '"+site+"%'": " and SITES.nom_site like '%' ")+"and datedebut between '"+debut+"' and '"+fin+"' and datecloture>'"+new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())+"'"+" ";
		}else if(inscrit.equals("false")  && inscrit.equals("true")){//pas inscrits
			GETSEARCH ="SELECT no_sortie,SORTIES.nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,SORTIES.ville FROM SORTIES,PARTICIPANTS,SITES "
					+ " where PARTICIPANTS.no_participant = SORTIES.organisateur and PARTICIPANTS.sites_no_site = SITES.no_site and "+(!recherche.equals("null")? "  SORTIES.nom like '"+recherche+"%'": " SORTIES.nom like '%'")+" "+
			""+(!organisateur.equals("false") ? " and organisateur = '"+organisateur+"'" : "")+""
					+(sortiePassee.equals("true") ? " and datecloture between '"+new SimpleDateFormat("yyyy-MM-dd").format(calActif.getTime())+"' and '"+date+"'" : "")+" "+(!site.equals("null")?" and SITES.nom_site like '"+site+"%'": " "
							+ "and SITES.nom_site like '%' and datedebut between '"+debut+"' and '"+fin+"' ")+" except SELECT no_sortie,SORTIES.nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,\r\n" + 
									"  lieux_no_lieu,etats_no_etat,SORTIES.ville FROM INSCRIPTIONS,PARTICIPANTS,SORTIES where INSCRIPTIONS.participants_no_participant = PARTICIPANTS.no_participant \r\n" + 
									"  and SORTIES.no_sortie = INSCRIPTIONS.sorties_no_sortie and PARTICIPANTS.no_participant ='"+organisateur+"'";
		}else {//tous
			GETSEARCH ="SELECT no_sortie,SORTIES.nom,datedebut,duree,datecloture,nbinscriptionsmax,descriptioninfos,organisateur,lieux_no_lieu,etats_no_etat,SORTIES.ville FROM SORTIES,PARTICIPANTS,SITES "
					+ " where PARTICIPANTS.no_participant = SORTIES.organisateur and PARTICIPANTS.sites_no_site = SITES.no_site and "+(!recherche.equals("null")? "  SORTIES.nom like '"+recherche+"%'": " SORTIES.nom like '%'")+" "+
			""+(!organisateur.equals("false") ? " and organisateur = '"+organisateur+"'" : "")+""
					+(sortiePassee.equals("true") ? " and datecloture between '"+new SimpleDateFormat("yyyy-MM-dd").format(calActif.getTime())+"' and '"+date+"'" : "")+" "+(!site.equals("null")?" and SITES.nom_site like '"+site+"%'": " "
							+ "and SITES.nom_site like '%' and datedebut between '"+debut+"' and '"+fin+"' ")+"";
		}

		System.out.println(GETSEARCH);


//		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
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
	public Sortie getOneById(Sortie uneSortie) throws SQLException {
		// TODO Auto-generated method stubGETONEBYID
		Sortie sortie = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETONEBYID);
			pstmt.setInt(1, uneSortie.getId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
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

	public ArrayList<Participant> getParticipantOfOneSortie(Sortie uneSortie) throws SQLException {
		ArrayList<Participant> listeParticipants = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETPARTICIPANTOFSORTIE);
			pstmt.setInt(1, uneSortie.getId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Participant unParticipant = new Participant();
				unParticipant.setId(rs.getInt(1));
				listeParticipants.add(unParticipant);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		System.out.println(listeParticipants);
		return listeParticipants;
	}
}
