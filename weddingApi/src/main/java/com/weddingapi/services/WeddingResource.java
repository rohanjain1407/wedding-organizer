package com.weddingapi.services;

import com.weddingapi.util.HibernateUtil;
import com.weddingapi.db.Wedding;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Neel Jain
 */
@Path("/wedding")
public class WeddingResource {

    @GET
    @Path("/get/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("id") String weddingId) {
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

    @GET
    @Path("/query")
    @Produces({MediaType.APPLICATION_JSON})
    public Response query() {
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
