package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utils.RestAssuredRequestFIlter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class commonAPIFunctions {

    public static Response response;
     static final Properties config = new Properties();
    public static Map<String, Object> session = new HashMap<>();
    public static  Map<String , String> valueStore = new HashMap<>();

    static {
        try {
            config.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "config.properties"));
        } catch (IOException e) {
            Assert.fail("Failed to open a connection");
        }
    }

    public static RequestSpecification requestSetup() {
        String env = config.getProperty("environment");
        RestAssured.reset();
        RestAssured.baseURI = config.getProperty(env + ".api.url");
        return RestAssured.given()
                .filter(new RestAssuredRequestFIlter())
                .contentType("application/json")
                .accept("application/json");
    }
}
