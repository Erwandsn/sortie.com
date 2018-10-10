package rest;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bll.EtatManager;
import bll.LieuManager;
import bll.ParticipantManager;
import bll.SortieManager;
import bll.VilleManager;
import bo.Etat;
import bo.Lieu;
import bo.Sortie;
import bo.Ville;

@Path("/sortie")
public class GestionSortie 
{
private SortieManager mgr;
	
	public GestionSortie() {
		this.mgr = new SortieManager();
	}
	
	public SortieManager getMgr() {
		return this.mgr;
	}
	
	@GET
	@Path("/{nomSortie}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Sortie> getRecherche(@PathParam("nomSortie") String nomSortie){
		ArrayList<Sortie> listSorties = new ArrayList<>();
		listSorties = getMgr().getSearchByNomSortie(nomSortie);
		return listSorties;
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Sortie getOneById(@PathParam("id") int idSortie){
		Sortie uneSortie = new Sortie();
		uneSortie.setId(idSortie);
		Sortie returnedSortie = null;
		returnedSortie = getMgr().getOneById(uneSortie);
		return returnedSortie;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Sortie> getAll(){
		ArrayList<Sortie> listSorties = new ArrayList<>();
		listSorties  = getMgr().getAll();
		return listSorties;
	}
	
	@GET
	@Path("/{site}/{recherche}/{organisateur}/{inscrit}/{pasInscrit}/{sortiePassee}/{debut}/{fin}")
	public ArrayList<Sortie> getRecherche(@PathParam("site") String site,@PathParam("recherche") String recherche,@PathParam("organisateur") String organisateur,
			@PathParam("inscrit") String inscrit,@PathParam("pasInscrit") String pasInscrit,@PathParam("sortiePassee") String sortiePassee,
			@PathParam("debut") String debut,@PathParam("fin") String fin){
		ArrayList<Sortie> listSorties = new ArrayList<>();
		listSorties = getMgr().getSearch(site,recherche,organisateur,inscrit,pasInscrit,sortiePassee,debut,fin);
		return listSorties;
	}
	
			
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Sortie addSortie(@FormParam("nomSortie") String nom) throws ParseException {
		Sortie unSortie = new Sortie();
		unSortie.setNom(nom);
		unSortie = getMgr().createSortie(unSortie);
		return unSortie;
	}
	
	
	@POST
	@Path("/ajoutSortie")
	@Produces(MediaType.APPLICATION_JSON)
	public Sortie ajoutSortie(@FormParam("nom") String nom,@FormParam("date") String date ,@FormParam("dateInscription") String dateInscription,
			@FormParam("place") String place , @FormParam("duree") String duree,@FormParam("description") String description,@FormParam("ville") String ville,
			@FormParam("lieu") String lieu,@FormParam("currentUser") String currentUser,@FormParam("etat") String etat ) throws ParseException {
		Sortie unSortie = new Sortie();
		unSortie.setNom(nom);
		String[] sHour = date.split(" ");
		String[] sDate = sHour[0].split("/");
		if(sDate.length >2) {
			String strDate= sDate[2]+"-"+ sDate[1]+"-"+ sDate[0]+" "+sHour[1];
			java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd HH:mm").parse(strDate);
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    unSortie.setDateheureDebut(sqlDate);
		}
		
		String[] sDateInsc = dateInscription.split("/");
		if(sDateInsc.length >2) {
			String strDateInsc= sDateInsc[2]+"-"+ sDateInsc[1]+"-"+ sDateInsc[0];
			unSortie.setDateLimiteInscription(java.sql.Date.valueOf(strDateInsc ));
		}
		unSortie.setDuree(Integer.parseInt(duree));
		unSortie.setInfosSortie(description);
		VilleManager vManager = new VilleManager();
		unSortie.setVille(vManager.searchVille(Integer.parseInt(ville)));
		ParticipantManager pManager = new ParticipantManager();
		unSortie.setOrganisateur(pManager.searchParticipant(Integer.parseInt(currentUser)));
		LieuManager lManager = new LieuManager(); 
		unSortie.setLieu(lManager.searchLieu(Integer.parseInt(lieu)));
		EtatManager eManager = new EtatManager(); 
		unSortie.setEtat(eManager.searchEtat(Integer.parseInt(etat)));
		unSortie.setNbInscriptionsMax(Integer.parseInt(place));
		unSortie = getMgr().createSortie(unSortie);
		return unSortie;
	}
	
	
	@DELETE
	@Path("/delete/{idSortie : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean deleteOne(@PathParam("idSortie") int idSortie) {
		Sortie unSortie = new Sortie();
		unSortie.setId(idSortie);
		Boolean state = getMgr().deleteSortie(unSortie);
		return state;
	}
	
	@PUT
	@Path("/update/{idSortie : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Sortie updateSortie(@PathParam("idSortie") int id, @PathParam("nomModif") String nomSortie) {
		Sortie unSortie = new Sortie();
		unSortie.setId(id);
		unSortie.setNom(nomSortie);
		unSortie.setNom(getMgr().updateSortie(unSortie).getNom());
		return unSortie;
	}
	
	@PUT
	@Path("/annulerSortie/{idSortie : \\d+}/{motif}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean annulerSortie(@PathParam("idSortie") int id, @PathParam("motif") String motif) {
		Boolean state = null;
		SortieManager manager = new SortieManager();
		Sortie uneSortie = new Sortie();
		uneSortie.setId(id);
		uneSortie.setInfosSortie(motif);
		state = manager.cancelSortie(uneSortie);
		return state;
	}
	
	@POST
	@Path("/modif")
	public Boolean modifierSortie(@FormParam("idSortie") int idSortie, @FormParam("nom") String nom, @FormParam("dateDebut") String dateDebut ,@FormParam("dateFin") String dateFin,
			@FormParam("nbPlace") String nbPlace , @FormParam("duree") int duree,@FormParam("description") String description,@FormParam("ville") int ville,
			@FormParam("lieu") String lieu,@FormParam("etat") String etat) throws ParseException {
		Boolean state = null;
		Sortie laSortie = new Sortie();
		laSortie.setId(idSortie);
		laSortie.setNom(nom);
		String[] sHour = dateDebut.split(" ");
		String[] sDate = sHour[0].split("/");
		if(sDate.length >2) {
			String strDate= sDate[2]+"-"+ sDate[1]+"-"+ sDate[0]+" "+sHour[1];
			java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd HH:mm").parse(strDate);
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    laSortie.setDateheureDebut(sqlDate);
		}
		String[] sDateInsc = dateFin.split("/");
		if(sDateInsc.length >2) {
			String strDateInsc= sDateInsc[2]+"-"+ sDateInsc[1]+"-"+ sDateInsc[0];
			laSortie.setDateLimiteInscription(java.sql.Date.valueOf(strDateInsc ));
		}
		laSortie.setNbInscriptionsMax(Integer.parseInt(nbPlace));
		laSortie.setDuree(duree);
		laSortie.setInfosSortie(description);
		Ville laVille = new Ville();
		laVille.setId(ville);
		laSortie.setVille(laVille);
		Lieu leLieu = new Lieu();
		leLieu.setId(Integer.parseInt(lieu));
		laSortie.setLieu(leLieu);
		Etat lEtat = new Etat();
		lEtat.setId(Integer.parseInt(etat));
		SortieManager mgr = new SortieManager();
		state = mgr.cancelSortie(laSortie);
		return state;
	}
}