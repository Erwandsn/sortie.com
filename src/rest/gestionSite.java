package rest;

import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bll.SiteManager;
import bo.Site;

@Path("/site")
public class gestionSite {
	
	private SiteManager mgr;
	
	public gestionSite() {
		this.mgr = new SiteManager();
	}
	
	public SiteManager getMgr() {
		return this.mgr;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Site> getAll(){
		ArrayList<Site> listSites = new ArrayList<>();
		listSites = getMgr().getAll();
		return listSites;
	}
			
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Site addSite(@FormParam("nomSite") String nom) throws ParseException {
		Site unSite = new Site();
		unSite.setNom(nom);
		unSite = getMgr().createSite(unSite);
		return unSite;
	}
	
	@DELETE
	@Path("/delete/{idSite : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean deleteOne(@PathParam("idSite") int idSite) {
		Site unSite = new Site();
		unSite.setId(idSite);
		Boolean state = getMgr().deleteSite(unSite);
		return state;
	}
	
	@PUT
	@Path("/update/{idSite : \\d+}/{nomModif}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site updateSite(@PathParam("idSite") int id, @PathParam("nomModif") String nomSite) {
		Site unSite = new Site();
		unSite.setId(id);
		unSite.setNom(nomSite);
		unSite.setNom(getMgr().updateSite(unSite).getNom());
		return unSite;
	}
}
