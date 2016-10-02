package com.weddingapi;

import com.weddingapi.db.Wedding;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Neel Jain
 */
@Path("/weddinginfo")
public class WeddingListener {
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Wedding> getInfo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createCriteria(Wedding.class).list();
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            session.close();
        }

	}
}