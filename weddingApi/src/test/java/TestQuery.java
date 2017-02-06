import com.weddingapi.util.HibernateUtil;
import com.weddingapi.db.DeviceToken;
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

            Wedding wedding = (Wedding) session.get(Wedding.class, "1");
            DeviceToken token = new DeviceToken("dev1", wedding);
            session.save(token);
            tx.commit();
            //return Response.status(200).entity(token).header("Access-Control-Allow-Origin", "*").build();
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            Wedding wedding2 = (Wedding) session.get(Wedding.class, "1");
            wedding2.getDeviceTokens();
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
