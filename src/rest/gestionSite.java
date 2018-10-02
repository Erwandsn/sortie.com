package rest;

import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
	public ArrayList<Site> getAll(){
		ArrayList<Site> listSites = new ArrayList<>();
		listSites = getMgr().getAll();
		return listSites;
	}
			
	@POST
	public Site addNote(@FormParam("nomSite") String nom) throws ParseException {
		Site unSite = new Site();
		unSite.setNom(nom);
		unSite = getMgr().createSite(unSite);
		return unSite;
	}
	
}
