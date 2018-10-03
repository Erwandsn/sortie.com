package dal;

public class DaoFactory {
	
	public static ParticipantDAOImpl getParticipantDao() {
		return new ParticipantDAOImpl();
	}
	
	public static SiteDAOImpl getSiteDao() {
		return new SiteDAOImpl();
	}
	
	public static VilleDAOImpl getVilleDao() {
		return new VilleDAOImpl();
	}
	
	
}
