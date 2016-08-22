import com.shyslav.util.DatabaseConnection;
import com.sukhaniuk.insert.DatabaseInsert;
import junit.framework.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Created by shyslav on 20.08.16.
 */
public class ActionTest {
    @Test
    public void insertBlobTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testData/panda.jpg").getFile());
        Assert.assertNotNull("file not found exception",file);
        FileInputStream inputStream = new FileInputStream(file);
        boolean result = DatabaseInsert.prepareInsert("blobTest",
                new Object[]{"imageTest",inputStream},
                new String []{"name","image"});
        Assert.assertTrue("execute statement error",result);
    }

    @Test
    public void selectBlobTest(){
        String command = "select * from blobTest";
        DatabaseConnection db = new DatabaseConnection();
        db.openConnection();
        InputStream inputStream = null;
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                inputStream = db.rs.getBinaryStream("image");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            Assert.fail();
        } finally {
            db.closeConnection();
        }
        if(inputStream == null)
        {
            Assert.fail();
        }
    }
}
