package com.weddingapi.services;

import com.weddingapi.db.Wedding;
import com.weddingapi.db.WeddingEvent;
import com.weddingapi.services.error.ApiError;
import com.weddingapi.services.error.ErrorResponse;
import com.weddingapi.util.HibernateUtil;
import com.weddingapi.util.StringUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Rohan Jain
 */
@Path("/weddingEvent")
public class WeddingEventResource {

    private static final String PROPERTY_EVENT_NAME = "eventName";
    private static final String PROPERTY_VENUE = "venue";
    private static final String PROPERTY_DUE_TIME = "dueTime";
    private static final String PROPERTY_EXPIRED = "expired";
    private static final String PROPERTY_DESCRIPTION = "description";
    private static final String PROPERTY_WEDDING = "Wedding";


    /**
     * Query wedding events based on weddingId
     *
     * @param weddingId
     * @return
     */
    @GET
    @Path("/query/weddingId/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response query(@PathParam("id") String weddingId) {
        if (StringUtil.isEmpty(weddingId)) {
            return new ErrorResponse(ApiError.INVALID_REQUEST).createError();
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Wedding wedding = (Wedding) session.get(Wedding.class, weddingId);
            if (wedding == null) {
                return new ErrorResponse(ApiError.RECORD_NOT_FOUND).createError();
            }
            List<WeddingEvent> weddingEvents = session.createCriteria(WeddingEvent.class)
                    .add(Restrictions.eq(PROPERTY_WEDDING, wedding)).list();

            tx.commit();
            return Response.status(200).entity(createResponse(weddingEvents))
                    .header("Access-Control-Allow-Origin", "*").build();

        } catch (Exception ex) {
            return new ErrorResponse(ApiError.UNKNOWN).createError();
        } finally {
            session.close();
        }
    }

    private static String createResponse(List<WeddingEvent> weddingEvents) {
        JSONArray jsonResponse = new JSONArray();
        for(WeddingEvent weddingEvent : weddingEvents) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(PROPERTY_EVENT_NAME, weddingEvent.getEvent().getEventName());
            jsonObject.put(PROPERTY_VENUE, weddingEvent.getVenue());
            jsonObject.put(PROPERTY_DUE_TIME, weddingEvent.getDueTime());
            jsonObject.put(PROPERTY_EXPIRED, weddingEvent.isExpired());
            jsonObject.put(PROPERTY_DESCRIPTION, weddingEvent.getDescription());
            jsonResponse.put(jsonObject);
        }
        return jsonResponse.toString();
    }
}
