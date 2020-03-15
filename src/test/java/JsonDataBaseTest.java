import com.winkey.api.JsonDataBase;
import com.winkey.exception.JSONDataBaseException;
import org.junit.jupiter.api.*;

/**
 * @author Venkatesh Rajendran
 * @since  15-03-2020
 * */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonDataBaseTest {

    private JsonDataBase testJsonDataBase = null;

    @BeforeAll
    void testGetInstance() throws JSONDataBaseException {
        testJsonDataBase = JsonDataBase.getInstance();
        assert testJsonDataBase != null;
    }

    @Test
    @Order(1)
    void testCreate() throws JSONDataBaseException{
        boolean isCreated = testJsonDataBase.create("Venkatesh", "{name : 'Venkatesh Rajendran', age : 24, company : 'Capgemini'}");
        assert isCreated;
    }

    @Test
    @Order(2)
    void testRead() throws JSONDataBaseException{
        String resultJson = testJsonDataBase.read("Venkatesh");
        assert resultJson != null;
    }

    @Test
    @Order(3)
    void testDelete() throws JSONDataBaseException{
        boolean isDeleted = testJsonDataBase.delete("Venkatesh");
        assert isDeleted;
    }

    @AfterAll
    void releaseInstance(){
        this.testJsonDataBase = null;
    }

}
