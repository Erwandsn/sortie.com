package rest;

import java.text.ParseException;

import javax.ws.rs.FormParam;
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
			
	@POST
	public Site addNote(@FormParam("nomSite") String nomSite) throws ParseException {
		Site unSite = new Site();
		unSite.setNom(nomSite);
		unSite = getMgr().createSite(unSite);
		return unSite;
	}
	
}
