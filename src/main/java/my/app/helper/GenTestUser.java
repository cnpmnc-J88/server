package my.app.helper;

import my.app.model.User;

public class GenTestUser {
    public static User genUser() {
        return new User("test", "***");
    }

    public static User GenTestClient() {
        return new User("client", "***");
    }
}
