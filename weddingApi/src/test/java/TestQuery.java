import com.weddingapi.HibernateUtil;
import com.weddingapi.db.Wedding;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * @author Rohan Jain
 */

public class TestQuery {

    /**
     * Tests connectivity to database
     * by retreiving all data in wedding table
     * @param args
     */
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
}
