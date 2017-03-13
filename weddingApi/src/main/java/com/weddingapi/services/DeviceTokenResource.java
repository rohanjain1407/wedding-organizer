package com.weddingapi.services;

import com.weddingapi.db.DeviceToken;
import com.weddingapi.services.error.ApiError;
import com.weddingapi.services.error.ErrorResponse;
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
    public Response add(final String jsonBody) {

        JSONObject jsonObject = new JSONObject(jsonBody);

        String weddingId = jsonObject.getString("weddingId");
        String deviceToken = jsonObject.getString("deviceToken");

        if (StringUtil.isEmpty(weddingId) || StringUtil.isEmpty(deviceToken)) {
            return new ErrorResponse(ApiError.INVALID_REQUEST).createError();
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Wedding wedding = (Wedding) session.get(Wedding.class, weddingId);
            if(wedding == null) {
                return new ErrorResponse(ApiError.RECORD_NOT_FOUND).createError();
            }
            com.weddingapi.db.DeviceToken token = new com.weddingapi.db.DeviceToken(deviceToken, wedding);
            session.save(token);
            tx.commit();
            return Response.status(200).entity(token).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            //log an exception here//
            return new ErrorResponse(ApiError.UNKNOWN).createError();
        } finally {
            session.close();
        }
    }


    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(final String jsonBody) {
        JSONObject jsonObject = new JSONObject(jsonBody);
        String deviceToken = jsonObject.getString("deviceToken");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            DeviceToken token = (DeviceToken) session.get(DeviceToken.class, deviceToken);
            if (token == null) {
                return new ErrorResponse(ApiError.RECORD_NOT_FOUND).createError();
            }
            session.delete(token);
            tx.commit();
            return Response.status(200).entity(getSuccessResponse()).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception e) {
            return new ErrorResponse(ApiError.UNKNOWN).createError();
        } finally {
            session.close();
        }
    }

    //this is temporary method//
    //will change once Neel is done with his changes on ApiError class//
    private static String getSuccessResponse() {
        JSONObject object = new JSONObject();
        object.put("success", "true");
        return object.toString();
    }
}
