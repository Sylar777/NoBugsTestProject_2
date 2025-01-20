package tests;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import models.JsonDataModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static steps.RequestsRESTSteps.*;

public class CrudTests extends BaseTest {
    @Test
    void readTest() {
        step("1. Preparing json and sending POST request for creation data on the server side ", () -> {
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
        });

        step("2. Get all Data from Server side and verify that response is not empty", () -> {
            var response = getAllData();
            int recordCount = response.jsonPath().getList("$").size();

            Assertions.assertNotNull(recordCount);
        });
    }

    @Test
    void createTest() {
        step("1. Preparing json and sending POST request for creation data on the server side ", () -> {
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
        });

        step("2. Verify that record was created on the server side", () -> {
            var getResponse = getDataById(id);
            Assertions.assertEquals(id, (Integer) getResponse.jsonPath().get("id"));
        });
    }

    @Test
    void updateTest() {
        step("1. Preparing json and sending POST request for creation data on the server side ", () -> {
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
        });

        step("2. Preparing json and sending POST request for updating data on the server side ", () -> {
            userId = faker.number().numberBetween(0, 1000);
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

            sendPostRequestToUpdateData(jsonData);
        });

        step("3. Verify that record was updated on the server side", () -> {
            var getResponse = getDataById(id);
            Assertions.assertEquals(userId, (Integer) getResponse.jsonPath().get("userId"));
            Assertions.assertEquals(title, getResponse.jsonPath().get("title"));
            Assertions.assertEquals(body, getResponse.jsonPath().get("body"));
        });
    }

    @Test
    void deleteTest() {
        step("1. Preparing json and sending POST request for creation data on the server side ", () -> {
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
        });

        step("2. Sending DELETE request for deleting data on the server side ", () -> {
            sendDeleteRequest(id);
        });

        step("3. Verify that record was deleted on the server side", () -> {
            var getResponse = getDataById(id);
            Assertions.assertEquals(SC_NOT_FOUND, getResponse.getStatusCode());
        });
    }
}
