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
import bo.Sortie;

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
		System.out.println("===================================== ville"+ville);
		unSortie.setNom(nom);
//		System.out.println(date);
		String[] sHour = date.split(" ");
		String[] sDate = sHour[0].split("/");
//		System.out.println(" size= "+sDate.length);
		String strDate= sDate[2]+"-"+ sDate[1]+"-"+ sDate[0]+" "+sHour[1];
//		System.out.println(strDate+" size= "+sDate.length);
		
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        java.util.Date parsed = format.parse("20110210");
//        java.sql.Date sql = new java.sql.Date(parsed.getTime());
//		
		
//		unSortie.setDateheureDebut((Date)new SimpleDateFormat("yyyy-mm-dd HH:mm").parse(strDate));
//		unSortie.setDateheureDebut(java.sql.Date.valueOf(strDate ));
		java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd HH:mm").parse(strDate);
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//	    System.out.println("utilDate:" + utilDate);
//	    System.out.println("sqlDate:" + sqlDate);
	    unSortie.setDateheureDebut(sqlDate);
		String[] sDateInsc = dateInscription.split("/");
		String strDateInsc= sDateInsc[2]+"-"+ sDateInsc[1]+"-"+ sDateInsc[0];
//		System.out.println(strDateInsc+" size= "+sDateInsc.length);
//		unSortie.setDateLimiteInscription((Date)new SimpleDateFormat("yyyy-mm-dd").parse(strDateInsc));
		unSortie.setDateLimiteInscription(java.sql.Date.valueOf(strDateInsc ));
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
//		LieuManager lManager = new LieuManager();
//		unSortie.setLieu(lManager.searchLieu(lieu));
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
	@Path("/update/{idSortie : \\d+}/{nomModif}")
	@Produces(MediaType.APPLICATION_JSON)
	public Sortie updateSortie(@PathParam("idSortie") int id, @PathParam("nomModif") String nomSortie) {
		Sortie unSortie = new Sortie();
		unSortie.setId(id);
		unSortie.setNom(nomSortie);
		unSortie.setNom(getMgr().updateSortie(unSortie).getNom());
		return unSortie;
	}
	
//	@PUT
//	@Path("/annulerSortie/{idSortie : \\d+}/{motif}/{etat}")
//	public Sortie annulerSortie(@PathParam("idSortie") int id, @PathParam("motif") String motif,@PathParam("etat") String etat) {
//		SortieManager manager = new SortieManager();
//		Sortie unSortie = manager.searchSortie(id);
////		unSortie.setId(id);
////		unSortie.setNom(motif);
//		EtatManager eManager = new EtatManager();
//		unSortie.setEtat(eManager.searchEtat(etat));
//		unSortie.setInfosSortie(motif);
//		unSortie.setNom(getMgr().updateSortie(unSortie).getInfosSortie());
//		return unSortie;
//	}
	

	@POST
	@Path("/annulerSortie")
	@Produces(MediaType.APPLICATION_JSON)
	public Sortie annulerSortie(@FormParam("idSortie") int id, @FormParam("motif") String motif) {
		SortieManager manager = new SortieManager();
		Sortie unSortie = manager.searchSortie(id);
//		unSortie.setId(id);
//		unSortie.setNom(motif);
		EtatManager eManager = new EtatManager();
		unSortie.setEtat(eManager.searchEtat(5));
		unSortie.setInfosSortie(motif);
		unSortie.setNom(getMgr().updateSortie(unSortie).getInfosSortie());
		return unSortie;
	}

}