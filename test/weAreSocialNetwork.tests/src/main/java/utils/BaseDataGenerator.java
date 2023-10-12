package utils;

import com.github.javafaker.Faker;

public class BaseDataGenerator {
    protected Faker faker;

    public BaseDataGenerator(){
        faker = new Faker();
    }
}
