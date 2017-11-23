import com.project.praktickecvicenia.dao.NoteDAOImpl;
import com.project.praktickecvicenia.entities.Note;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class NoteDAOTest {

    private EmbeddedDatabase db;
    
    @Before
    public void setUp() {
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.addScript("create-db.sql")
    		.addScript("insert-data.sql")
    		.build();
    }

    @Test
    public void testFindByname() {
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    	NoteDAOImpl userDao = new NoteDAOImpl();
    	userDao.setNamedParameterJdbcTemplate(template);
    	
    	Note user = userDao.findById(1);
  
    	Assert.assertNotNull(user);
    	Assert.assertEquals(1, user.getId());
    	Assert.assertEquals("Make coffee.", user.getNote());
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}