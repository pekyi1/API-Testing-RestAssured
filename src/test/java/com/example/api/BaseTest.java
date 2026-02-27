package com.example.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        // Allow passing baseUrl from maven, default to jsonplaceholder
        String baseUrl = System.getProperty("baseUrl", "https://jsonplaceholder.typicode.com");
        RestAssured.baseURI = baseUrl;
    }
}
