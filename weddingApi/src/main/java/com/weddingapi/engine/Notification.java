package com.weddingapi.engine;

import com.weddingapi.db.DeviceToken;
import com.weddingapi.db.Event;
import com.weddingapi.db.WeddingEvent;
import com.weddingapi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohan Jain
 */
public class Notification {

    /**
     * This method accepts weddingEvent id as a parameter to
     * send event notification to devices registered for
     * associated wedding
     * @return
     */
    public static void main(String[] args) {
        long weddingEventId=Long.parseLong(args[0]);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            WeddingEvent weddingEvent = (WeddingEvent) session.get(WeddingEvent.class, weddingEventId);
            if(weddingEvent == null) {
                //TODO log an exception
            }
            Event event = weddingEvent.getEvent();
            List<DeviceToken> deviceTokens = session.createCriteria(DeviceToken.class).add(
                    Restrictions.eq("Wedding", weddingEvent.getWedding())).list();
            tx.commit();
            List<String> tokens = new ArrayList<String>();

            for(DeviceToken deviceToken : deviceTokens) {
                tokens.add(deviceToken.getDeviceToken());
            }
            pushNotification(event.getEventName(), tokens);
        }
        catch (Exception e) {
            //TODO log an exception
        }
        finally {
            session.close();
        }
    }

    private static void pushNotification(String eventName, List<String> tokens) {
        //TODO send an api call to push the notification//
    }
}
