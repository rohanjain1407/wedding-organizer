import com.weddingapi.db.Event;
import com.weddingapi.db.WeddingEvent;
import com.weddingapi.util.HibernateUtil;
import com.weddingapi.db.Wedding;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.junit.Test;


/**
 * @author Rohan Jain
 */

public class TestQuery {

    /**
     * Tests connectivity to database
     * by retreiving all data in wedding table
     * @param args
     */
    @org.junit.Ignore
    @Test
    public void testDatabaseConnectivity() {
        //test DB
        Session session = HibernateUtil.getSessionFactory().openSession();
        Wedding wed = (Wedding) session.createCriteria(Wedding.class)
                .add(Restrictions.eq("weddingId", "1")).uniqueResult();

        //List<Wedding> listOfTable = session.createCriteria(Wedding.class).list();
        //System.out.println(listOfTable.size());
        session.close();
    }
@Ignore
    @Test
    public void testInsertion() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {

            Event event = (Event) session.get(Event.class,1L);
            Wedding wedding = (Wedding) session.get(Wedding.class,"2");
            WeddingEvent weddingEvent = new WeddingEvent(event, wedding,"Mumbai",null,false);
            session.save(weddingEvent);
            tx.commit();
            //return Response.status(200).entity(token).header("Access-Control-Allow-Origin", "*").build();
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            session.close();
        }
    }

    @Ignore
    @Test
    public void testInsertEvent() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {

            //session.save(event);
            tx.commit();
            //return Response.status(200).entity(token).header("Access-Control-Allow-Origin", "*").build();
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            session.close();
        }
    }
}
