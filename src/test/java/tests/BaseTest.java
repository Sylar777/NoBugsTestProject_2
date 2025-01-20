package tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected int userId;
    protected int id;
    protected String title;
    protected String body;

    protected Faker faker;

    public BaseTest() {
        faker = new Faker();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/";
    }
}
