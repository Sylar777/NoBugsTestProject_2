package models;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class JsonDataModel {
    public int userId;
    public int id;
    public String title;
    public String body;

    public JsonDataModel(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static class DataBuilder {
        private int userId;
        private int id;
        private String title;
        private String body;

        public DataBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public DataBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public DataBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public DataBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public JsonDataModel build() {
            return new JsonDataModel(userId, id, title, body);
        }
    }
}
