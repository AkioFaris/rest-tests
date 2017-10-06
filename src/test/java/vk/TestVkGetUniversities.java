package vk;
import beans.VkGetUniversitiesAnswer;
import core.vk.VkGetUniversitiesApi;
import io.restassured.RestAssured;
import org.junit.Test;

import java.util.List;

import static core.vk.VkIntConstants.*;
import static core.vk.VkTextConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class TestVkGetUniversities {
    // use base request and response specifications to form request and validate response.
    @Test
    public void useBaseRequestAndResponseSpecifications() {
        RestAssured
                .given(VkGetUniversitiesApi.baseRequestConfiguration())
                .param(PARAM_SEARCH_QUERY.text, SEARCH_QUERY.text)
                .param(PARAM_COUNTRY_ID.text, COUNTRY_ID.number)
                .param(PARAM_CITY_ID.text, CITY_ID.number)
                .param(PARAM_OFFSET.text, OFFSET.number)
                .param(PARAM_COUNT.text, COUNT.number)
                .log().all()
                .get().prettyPeek()
                .then().specification(VkGetUniversitiesApi.successResponse());
    }

    @Test
    public void reachBuilderUsage(){
        VkGetUniversitiesApi.with()
                .searchQuery(SEARCH_QUERY.text)
                .countryId(COUNTRY_ID.number)
                .cityId(CITY_ID.number)
                .offset(OFFSET.number)
                .count(COUNT.number)
                .callApi()
                .then().specification(VkGetUniversitiesApi.successResponse());
    }

    //validate an object we've got in API response
    @Test
    public void validateSpellerAnswerAsAnObject() {
        List<VkGetUniversitiesAnswer> answers = VkGetUniversitiesApi.getVkGetUniversitiesAnswers(
                        VkGetUniversitiesApi.with()
                                .searchQuery(SEARCH_QUERY.text)
                                .countryId(COUNTRY_ID.number)
                                .cityId(CITY_ID.number)
                                .offset(OFFSET.number)
                                .count(COUNT.number)
                                .callApi());
        assertThat(answers.size(), lessThanOrEqualTo(COUNT.number));
        assertThat(answers.get(0).id, equalTo(1));
        assertThat(answers.get(0).title, equalTo("СПбГУ"));
        assertThat(answers.get(1).id, equalTo(13));
        assertThat(answers.get(1).title, equalTo("ВАШ при Администрации СПб"));
    }
}
