package core.mal;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static core.mal.MalConstants.*;
import static io.restassured.RestAssured.basic;

public class MyAnimeListApi {

    //builder pattern
    private MyAnimeListApi() {
    }

    public static RequestSpecification baseRequestConfiguration() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .setBaseUri(VERIFY_CREDENTIALS_API_URI.text)
                .setAuth(basic(USER_LOGIN.text, USER_PASSWORD.text))
                .build();
    }

}
