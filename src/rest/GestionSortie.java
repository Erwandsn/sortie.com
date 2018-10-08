package rest;

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
	public ArrayList<Sortie> getRecherche(@PathParam("nomSortie") String nomSortie){
		ArrayList<Sortie> listSorties = new ArrayList<>();
		listSorties = getMgr().getSearchByNomSortie(nomSortie);
		return listSorties;
	}
	
	@GET
	public ArrayList<Sortie> getAll(){
		ArrayList<Sortie> listSorties = new ArrayList<>();
		listSorties  = getMgr().getAll();
		return listSorties;
	}
			
	@POST
	public Sortie addSortie(@FormParam("nomSortie") String nom) throws ParseException {
		Sortie unSortie = new Sortie();
		unSortie.setNom(nom);
		unSortie = getMgr().createSortie(unSortie);
		return unSortie;
	}
	
	
	@POST
	@Path("/ajoutSortie")
	public Sortie ajoutSortie(@FormParam("nom") String nom,@FormParam("date") String date ,@FormParam("dateInscription") String dateInscription,
			@FormParam("place") String place , @FormParam("duree") String duree,@FormParam("description") String description,@FormParam("ville") String ville,
			@FormParam("lieu") String lieu,@FormParam("currentUser") String currentUser ) throws ParseException {
		Sortie unSortie = new Sortie();
		unSortie.setNom(nom);
		System.out.println(date);
		String[] sHour = date.split(" ");
		String[] sDate = sHour[0].split("/");
//		System.out.println(" size= "+sDate.length);
		String strDate= sDate[2]+"-"+ sDate[1]+"-"+ sDate[0]+" "+sHour[1];
//		System.out.println(strDate+" size= "+sDate.length);
		unSortie.setDateheureDebut(new SimpleDateFormat("yyyy-mm-dd HH:mm").parse(strDate));
		String[] sDateInsc = dateInscription.split("/");
		String strDateInsc= sDateInsc[2]+"-"+ sDateInsc[1]+"-"+ sDateInsc[0];
//		System.out.println(strDateInsc+" size= "+sDateInsc.length);
		unSortie.setDateLimiteInscription(new SimpleDateFormat("yyyy-mm-dd").parse(strDateInsc));
		unSortie.setDuree(Integer.parseInt(duree));
		unSortie.setInfosSortie(description);
		VilleManager vManager = new VilleManager();
		unSortie.setVille(vManager.searchVille(ville));
//		LieuManager lManager = new LieuManager();
//		unSortie.setLieu(lManager.searchLieu(lieu));
		unSortie = getMgr().createSortie(unSortie);
		return unSortie;
	}
	
	
	@DELETE
	@Path("/delete/{idSortie : \\d+}")
	public Boolean deleteOne(@PathParam("idSortie") int idSortie) {
		Sortie unSortie = new Sortie();
		unSortie.setId(idSortie);
		Boolean state = getMgr().deleteSortie(unSortie);
		return state;
	}
	
	@PUT
	@Path("/update/{idSortie : \\d+}/{nomModif}")
	public Sortie updateSortie(@PathParam("idSortie") int id, @PathParam("nomModif") String nomSortie) {
		Sortie unSortie = new Sortie();
		unSortie.setId(id);
		unSortie.setNom(nomSortie);
		unSortie.setNom(getMgr().updateSortie(unSortie).getNom());
		return unSortie;
	}
}