package model;

import com.github.javafaker.Faker;

public class ProfileGenerator {

    public static UserProfile getRandom() {

        Faker faker = new Faker();
        return new UserProfile(faker.name()
                .firstName(), faker.internet()
                .emailAddress(), faker
                .internet().password());
    }
}
