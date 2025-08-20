package steps;
import io.cucumber.java.BeforeAll;
import org.junit.After;
import org.junit.Before;
import pages.*;

public class Hooks {

    public static commonAPIFunctions commonAPIFunctions;

    public Hooks() {
    }

    @Before
    public void initializeTest() {
        commonAPIFunctions = new commonAPIFunctions();
    }
}
