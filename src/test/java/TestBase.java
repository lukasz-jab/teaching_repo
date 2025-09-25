import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase extends ApplicationManager {

    @BeforeClass
    public void setup() {
        init();
    }


    @AfterClass
    public void tearDown() {
        stop();
    }
}
