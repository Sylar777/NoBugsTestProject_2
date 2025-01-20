package tests;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import models.FakeJsonDataModel;
import models.JsonDataModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.step;
import static steps.RequestsRESTSteps.sendPostRequestWithIncorrectData;

public class UserIdTests extends BaseTest {

    /*
        2) Sending Request where userId contains char symbols: Bad Request - 400
        3) Sending Request where userId contains some specific symbols: Bad Request - 400
        4) Sending Request where userId = -1: Bad Request - 400
        5) Sending Request where userId = 1001: Bad Request - 400
     */
    @ParameterizedTest
    @ValueSource(strings = {"a", "@"})
    void userIdContainsCharTest(String userIdChar) {
        step("1. Preparing json and sending POST request for creation data on the server side ", () -> {
            var userId = userIdChar + faker.number().numberBetween(0, 1000);
            var id = "a" + faker.number().numberBetween(101, 1000);
            title = faker.book().title();
            body = faker.lorem().sentence(10);

            FakeJsonDataModel fakeJsonDataModel = new FakeJsonDataModel.DataBuilder()
                    .setUserId(userId)
                    .setId(id)
                    .setTitle(title)
                    .setBody(body)
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            var jsonData = objectMapper.writeValueAsString(fakeJsonDataModel);

            sendPostRequestWithIncorrectData(jsonData);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "1001"})
    void userIdNotInRange(String value) {
        step("1. Preparing json and sending POST request for creation data on the server side ", () -> {
            userId = Integer.parseInt(value);
            id = faker.number().numberBetween(101, 1000);
            title = faker.book().title();
            body = faker.lorem().sentence(10);

            JsonDataModel jsonDataModel = new JsonDataModel.DataBuilder()
                    .setUserId(userId)
                    .setId(id)
                    .setTitle(title)
                    .setBody(body)
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            var jsonData = objectMapper.writeValueAsString(jsonDataModel);

            sendPostRequestWithIncorrectData(jsonData);
        });
    }
}
