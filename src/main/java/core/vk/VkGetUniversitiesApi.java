package core.vk;

import beans.VkGetUniversitiesAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static core.vk.VkGetUniversitiesConstants.*;
import static org.hamcrest.Matchers.lessThan;

public class VkGetUniversitiesApi {
    //builder pattern
    private VkGetUniversitiesApi() {
    }

    private HashMap<String, String> params = new HashMap<String, String>();

    public static class ApiBuilder {
        VkGetUniversitiesApi vkApi;

        private ApiBuilder(VkGetUniversitiesApi gcApi) {
            vkApi = gcApi;
        }

        public VkGetUniversitiesApi.ApiBuilder searchQuery(String query) {
            vkApi.params.put(PARAM_SEARCH_QUERY, query);
            return this;
        }

        public VkGetUniversitiesApi.ApiBuilder countryId(String id) {
            vkApi.params.put(PARAM_COUNTRY_ID, id);
            return this;
        }

        public VkGetUniversitiesApi.ApiBuilder cityId(String id) {
            vkApi.params.put(PARAM_CITY_ID, id);
            return this;
        }

        public VkGetUniversitiesApi.ApiBuilder offset(String value) {
            vkApi.params.put(PARAM_OFFSET, value);
            return this;
        }

        public VkGetUniversitiesApi.ApiBuilder count(String numOfUni) {
            vkApi.params.put(PARAM_COUNT, numOfUni);
            return this;
        }

        public Response callApi() {
            return RestAssured.with()
                    .queryParams(vkApi.params)
                    .log().all()
                    .get(VK_GET_UNI_API_URI).prettyPeek();
        }
    }

    public static VkGetUniversitiesApi.ApiBuilder with() {
        VkGetUniversitiesApi api = new VkGetUniversitiesApi();
        return new VkGetUniversitiesApi.ApiBuilder(api);
    }

    //get ready Vk GetUniversities answers list form api response
    public static List<VkGetUniversitiesAnswer> getVkGetUniversitiesAnswers(Response response) {
        String responseStr = response.print();
        int beginIndex = responseStr.indexOf("{", 2);
        int endIndex = responseStr.lastIndexOf("]");
        String jsonStr = "[" + responseStr.substring(beginIndex, endIndex) + "]";
        Type listType = new TypeToken<List<VkGetUniversitiesAnswer>>() {
        }.getType();
        List<VkGetUniversitiesAnswer> list = new Gson().fromJson(jsonStr, listType);
        return list;
    }

    //set base request and response specifications tu use in tests
    public static ResponseSpecification successResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConfiguration() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .addHeader("custom header1", "header1.value")
                .addQueryParam("requestID", new Random().nextLong())
                .setBaseUri(VK_GET_UNI_API_URI)
                .build();
    }
}
