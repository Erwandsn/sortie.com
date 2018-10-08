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

import bll.LieuManager;
import bll.VilleManager;
import bo.Lieu;

@Path("/lieu")
public class GestionLieu 
{
private LieuManager mgr;
	
	public GestionLieu() {
		this.mgr = new LieuManager();
	}
	
	public LieuManager getMgr() {
		return this.mgr;
	}
	
	@GET
	@Path("/{nomLieu}")
	public ArrayList<Lieu> getRecherche(@PathParam("nomLieu") String nomLieu){
		ArrayList<Lieu> listLieus = new ArrayList<>();
		listLieus = getMgr().getSearchByNomLieu(nomLieu);
		return listLieus;
	}
	
	@GET
	public ArrayList<Lieu> getAll(){
		ArrayList<Lieu> listLieus = new ArrayList<>();
		listLieus  = getMgr().getAll();
		return listLieus;
	}
			
	@POST
	public Lieu addLieu(@FormParam("nomLieu") String nom) throws ParseException {
		Lieu unLieu = new Lieu();
		unLieu.setNom(nom);
		unLieu = getMgr().createLieu(unLieu);
		return unLieu;
	}
	
	
	@POST
	@Path("/ajoutLieu")
	public Lieu ajoutLieu(@FormParam("nomLieu") String nomLieu ,@FormParam("rue") String rue,@FormParam("latitude") String latitude ,
			@FormParam("longitude") String longitude,@FormParam("ville") String ville) throws ParseException {
		Lieu unLieu = new Lieu();
		unLieu.setNom(nomLieu);
		unLieu.setRue(rue);
		unLieu.setLatitude(Float.parseFloat(latitude));
		unLieu.setLongitude(Float.parseFloat(longitude));
		VilleManager vManager = new VilleManager();
		unLieu.setVille(vManager.searchVille(ville));
		unLieu = getMgr().createLieu(unLieu);
		return unLieu;
	}
	
	
	@DELETE
	@Path("/delete/{idLieu : \\d+}")
	public Boolean deleteOne(@PathParam("idLieu") int idLieu) {
		Lieu unLieu = new Lieu();
		unLieu.setId(idLieu);
		Boolean state = getMgr().deleteLieu(unLieu);
		return state;
	}
	
	@PUT
	@Path("/update/{idLieu : \\d+}/{nomModif}")
	public Lieu updateLieu(@PathParam("idLieu") int id, @PathParam("nomModif") String nomLieu) {
		Lieu unLieu = new Lieu();
		unLieu.setId(id);
		unLieu.setNom(nomLieu);
		unLieu.setNom(getMgr().updateLieu(unLieu).getNom());
		return unLieu;
	}
}