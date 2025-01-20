package models;

public class FakeJsonDataModel {
    public String userId;
    public String id;
    public String title;
    public String body;

    public FakeJsonDataModel(String userId, String id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static class DataBuilder {
        private String userId;
        private String id;
        private String title;
        private String body;

        public FakeJsonDataModel.DataBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public FakeJsonDataModel.DataBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public FakeJsonDataModel.DataBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public FakeJsonDataModel.DataBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public FakeJsonDataModel build() {
            return new FakeJsonDataModel(userId, id, title, body);
        }
    }
}
