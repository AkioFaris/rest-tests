package core.mal;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static core.mal.MyAnimeListConstants.*;
import static io.restassured.RestAssured.basic;

public class MyAnimeListApi {



    //builder pattern
    private MyAnimeListApi() {
    }
    private HashMap<String, String> params = new HashMap<String, String>();

    public static class ApiBuilder {
        MyAnimeListApi malApi;

        private ApiBuilder(MyAnimeListApi gcApi) {
            malApi = gcApi;
        }

        public ApiBuilder animeId(String id) {
            malApi.params.put(PARAM_ANIME_ID, id);
            return this;
        }

        public ApiBuilder animeData(String data) {
            malApi.params.put(PARAM_ANIME_DATA, data);
            return this;
        }

        public Response callApi() {
            return RestAssured.with()
                    .queryParams(malApi.params)
                    .log().all()
                    .get(MAL_ADD_ANIME_API_URI).prettyPeek();
        }
    }

    public static ApiBuilder with() {
        MyAnimeListApi api = new MyAnimeListApi();
        return new ApiBuilder(api);
    }

/*
    //get ready Speller answers list form api response
    public static List<MyAnimeListAnswer> getMyAnimeListAnswers(Response response){
        return new Gson().fromJson(response.asString(), new TypeToken<List<MyAnimeListAnswer>>() {}.getType());
    }


    //set base request and response specifications tu use in tests
    public static ResponseSpecification successResponse(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }
*/

    public static RequestSpecification baseRequestConfiguration(){
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .addHeader("custom header1", "header1.value")
                // ? .addQueryParam("requestID", new Random().nextLong())
                .setBaseUri(MAL_ADD_ANIME_API_URI)
                .setAuth(basic(USER_LOGIN, USER_PASSWORD))
                .build();
    }
}
