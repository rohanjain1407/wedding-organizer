package com.weddingapi.getAPI;

import com.weddingapi.util.HibernateUtil;
import com.weddingapi.db.Wedding;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Rohan Jain
 */
@Path("/weddinginfo")
public class WeddingGet {

    @GET
    @Path("/getWedding/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getWeddingById(@PathParam("id") String weddingId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Wedding wed = (Wedding) session.createCriteria(Wedding.class)
                    .add(Restrictions.eq("weddingId", weddingId)).uniqueResult();
            tx.commit();
            wed = wed != null ? wed : new Wedding();
            return Response.status(200).entity(wed).header("Access-Control-Allow-Origin", "*").build();
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            session.close();
        }
    }
}
