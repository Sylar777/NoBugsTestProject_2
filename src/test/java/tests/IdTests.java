package tests;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import models.FakeJsonDataModel;
import models.JsonDataModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.step;
import static steps.RequestsRESTSteps.sendPostRequestToCreateData;
import static steps.RequestsRESTSteps.sendPostRequestWithIncorrectData;

public class IdTests extends BaseTest {
    /*
        6) Sending Request where Id contains char symbols: Bad Request - 400
        7) Sending Request where Id contains some specific symbols: Bad Request - 400
        8) Sending Request where Id = -1: Bad Request - 400
        9) Sending Request where Id equals existing value on the Server: Bad Request - 400
     */

    @ParameterizedTest
    @ValueSource(strings = {"a", "@"})
    void idContainsCharTest(String idChar) {
        step("1. Preparing json and sending POST request for creation data on the server side ", () -> {
            var userId = "a" + faker.number().numberBetween(0, 1000);
            var id = idChar + faker.number().numberBetween(101, 1000);
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

    @Test
    void idNotInRangeTest() {
        step("1. Preparing json and sending POST request for creation data on the server side ", () -> {
            userId = faker.number().numberBetween(0, 1000);
            id = -1;
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

    @Test
    void idDuplicateTest() {
        step("1. Preparing json and sending POST request for creation data on the server side " +
                "and verify that sending create request with the same id will be failed ", () -> {
            userId = faker.number().numberBetween(0, 1000);
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

            sendPostRequestToCreateData(jsonData);

            sendPostRequestWithIncorrectData(jsonData);
        });
    }
}
