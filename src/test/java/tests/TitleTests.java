package tests;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import models.JsonDataModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static steps.RequestsRESTSteps.sendPostRequestWithIncorrectData;

public class TitleTests extends BaseTest {
    /*
        10) Sending Request where Title is empty value: Bad Request - 400
        11) Sending Request where Title field not mentioned in the JSON: Bad Request - 400
        12) Sending Request where Title = 10001: Bad Request - 400
     */

    @Test
    void createDataWithEmptyTitleTest() {
        step("1. Preparing json and sending POST request for creation data on the server side " +
                "and check that data won't be created with 400 ", () -> {
            userId = faker.number().numberBetween(0, 1000);
            id = faker.number().numberBetween(100, 1000);
            title = "";
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
    void createDataWithOutTitleTest() {
        step("1. Preparing json and sending POST request for creation data on the server side " +
                "and check that data won't be created with 400 ", () -> {
            userId = faker.number().numberBetween(0, 1000);
            id = faker.number().numberBetween(100, 1000);
            body = faker.lorem().sentence(10);

            JsonDataModel jsonDataModel = new JsonDataModel.DataBuilder()
                    .setUserId(userId)
                    .setId(id)
                    .setBody(body)
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            var jsonData = objectMapper.writeValueAsString(jsonDataModel);

            sendPostRequestWithIncorrectData(jsonData);
        });
    }

    @Test
    void createDataWithLongTitleTest() {
        step("1. Preparing json and sending POST request for creation data on the server side " +
                "and check that data won't be created with 400 ", () -> {
            userId = faker.number().numberBetween(0, 1000);
            id = faker.number().numberBetween(100, 1000);
            title = faker.lorem().characters(10001);
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
