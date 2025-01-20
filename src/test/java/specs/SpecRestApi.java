package specs;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class SpecRestApi {
    public static final RequestSpecification defaultLoggingRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);
}
