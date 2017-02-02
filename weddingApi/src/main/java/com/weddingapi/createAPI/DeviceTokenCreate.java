package com.weddingapi.createAPI;

import com.weddingapi.util.HibernateUtil;
import com.weddingapi.db.DeviceToken;
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
@Path("/DeviceToken")
public class DeviceTokenCreate {

    @Path("/createToken")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDeviceToken(final String jsonString) {

        JSONObject jsonObject = new JSONObject(jsonString);

        String weddingId = jsonObject.getString("weddingId");
        String deviceToken = jsonObject.getString("device_token");

        if(StringUtil.isEmpty(weddingId) || StringUtil.isEmpty(deviceToken)) {
            return Response.status(400).entity("Request body is missing Wedding Id or " +
                    "Device Token or has Invalid data").build();
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Wedding wedding = (Wedding) session.get(Wedding.class, weddingId );
            DeviceToken token = new DeviceToken(deviceToken, wedding);
            session.save(token);
            tx.commit();
            return Response.status(200).entity(token).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            //log an exception here//
            return Response.status(400).entity("Unable to fulfill request").build();
        } finally {
            session.close();
        }
    }

    /**
     * To retreive device tokens from wedding object -
     *
     session.createCriteria(DeviceToken.class).add(Restrictions.eq(
     "Wedding",wedding
     )).list()

     */
}
