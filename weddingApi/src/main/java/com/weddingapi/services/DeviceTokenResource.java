package com.weddingapi.services;

import com.weddingapi.util.HibernateUtil;
import com.weddingapi.db.Wedding;
import com.weddingapi.util.StringUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Rohan Jain
 */
@Path("/deviceToken")
public class DeviceTokenResource {

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(final String jsonString) {

        JSONObject jsonObject = new JSONObject(jsonString);

        String weddingId = jsonObject.getString("weddingId");
        String deviceToken = jsonObject.getString("deviceToken");

        if (StringUtil.isEmpty(weddingId) || StringUtil.isEmpty(deviceToken)) {
            return Response.status(400).entity("Request body is missing Wedding Id or " +
                    "Device Token or has Invalid data").build();
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Wedding wedding = (Wedding) session.get(Wedding.class, weddingId);
            com.weddingapi.db.DeviceToken token = new com.weddingapi.db.DeviceToken(deviceToken, wedding);
            session.save(token);
            tx.commit();
            return Response.status(200).entity(token).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            //log an exception here//
            return Response.status(400).entity("Error processing the request").build();
        } finally {
            session.close();
        }
    }
}
