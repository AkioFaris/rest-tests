package vk;
import beans.VkGetUniversitiesAnswer;
import core.vk.VkGetUniversitiesApi;
import io.restassured.RestAssured;
import org.junit.Test;

import java.util.List;

import static core.vk.VkGetUniversitiesConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class TestVkGetUniversities {
    // use base request and response specifications to form request and validate response.
    @Test
    public void useBaseRequestAndResponseSpecifications() {
        RestAssured
                .given(VkGetUniversitiesApi.baseRequestConfiguration())
                .param(PARAM_SEARCH_QUERY, searchQuery)
                .param(PARAM_COUNTRY_ID, countryId)
                .param(PARAM_CITY_ID, cityId)
                .param(PARAM_OFFSET, offset)
                .param(PARAM_COUNT, count)
                .log().all()
                .get().prettyPeek()
                .then().specification(VkGetUniversitiesApi.successResponse());
    }

    @Test
    public void reachBuilderUsage(){
        VkGetUniversitiesApi.with()
                .searchQuery(searchQuery)
                .countryId(countryId)
                .cityId(cityId)
                .offset(offset)
                .count(count)
                .callApi()
                .then().specification(VkGetUniversitiesApi.successResponse());
    }

    //validate an object we've got in API response
    @Test
    public void validateSpellerAnswerAsAnObject() {
        List<VkGetUniversitiesAnswer> answers = VkGetUniversitiesApi.getVkGetUniversitiesAnswers(
                        VkGetUniversitiesApi.with()
                                .searchQuery(searchQuery)
                                .countryId(countryId)
                                .cityId(cityId)
                                .offset(offset)
                                .count(count)
                                .callApi());
        assertThat(answers.size(), lessThanOrEqualTo(Integer.parseInt(count)));
        assertThat(answers.get(0).id, equalTo(1));
        assertThat(answers.get(0).title, equalTo("СПбГУ"));
        assertThat(answers.get(1).id, equalTo(13));
        assertThat(answers.get(1).title, equalTo("ВАШ при Администрации СПб"));
    }
}
