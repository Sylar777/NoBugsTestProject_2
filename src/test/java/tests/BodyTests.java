package tests;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import models.JsonDataModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static steps.RequestsRESTSteps.sendPostRequestWithIncorrectData;

public class BodyTests extends BaseTest {
    @Test
    void createDataWithOutBodyTest() {
        step("1. Preparing json and sending POST request for creation data on the server side " +
                "and check that data won't be created with 400 ", () -> {
            userId = faker.number().numberBetween(0, 1000);
            id = faker.number().numberBetween(100, 1000);
            title = faker.book().title();

            JsonDataModel jsonDataModel = new JsonDataModel.DataBuilder()
                    .setUserId(userId)
                    .setId(id)
                    .setTitle(title)
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            var jsonData = objectMapper.writeValueAsString(jsonDataModel);

            sendPostRequestWithIncorrectData(jsonData);
        });
    }
}
