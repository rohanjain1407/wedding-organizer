package com.weddingapi.queryAPI;

//project includes
import com.weddingapi.util.HibernateUtil;
import com.weddingapi.db.Wedding;

//hibernate includes
import org.hibernate.Session;
import org.hibernate.Transaction;

//java rest api libraries
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//utils
import java.util.List;

/**
 * @author Neel Jain
 */

@Path("/weddinginfo")
public class WeddingListener {
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getInfo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            List<Wedding> weddingList = session.createCriteria(Wedding.class).list();
            tx.commit();
        	GenericEntity<List<Wedding>> entity = new GenericEntity<List<Wedding>>(weddingList){};
        	return Response.status(200).entity(entity).header("Access-Control-Allow-Origin", "*").build();
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            session.close();
        }
	}
}