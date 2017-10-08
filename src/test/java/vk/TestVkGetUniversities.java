package vk;

import beans.VkGetUniversitiesAnswer;
import core.vk.VkGetUniversitiesApi;
import core.vk.enums.Universities;
import io.restassured.RestAssured;
import org.junit.Test;

import java.util.List;

import static core.vk.enums.Cities.SPB;
import static core.vk.enums.Countries.RUS;
import static core.vk.enums.QueryParamLabels.*;
import static core.vk.enums.TestConstants.*;
import static core.vk.enums.Universities.RIMSKY_KORSAKOV_SPBGK;
import static core.vk.enums.Universities.SPBU;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class TestVkGetUniversities {
    // use base request and response specifications to form request and validate response.
    @Test
    public void useBaseRequestAndResponseSpecifications() {
        RestAssured
                .given(VkGetUniversitiesApi.baseRequestConfiguration())
                .param(PARAM_SEARCH_QUERY.label, UNI_SEARCH_QUERY.text)
                .param(PARAM_COUNTRY_ID.label, RUS.id)
                .param(PARAM_CITY_ID.label, SPB.id)
                .param(PARAM_OFFSET.label, SEARCH_RESULTS_OFFSET.number)
                .param(PARAM_COUNT.label, SEARCH_RESULTS_COUNT.number)
                .log().all()
                .get().prettyPeek()
                .then().specification(VkGetUniversitiesApi.successResponse());
    }

    @Test
    public void reachBuilderUsage() {
        VkGetUniversitiesApi.with()
                .searchQuery(UNI_SEARCH_QUERY.text)
                .countryId(RUS.id)
                .cityId(SPB.id)
                .offset(SEARCH_RESULTS_OFFSET.number)
                .count(SEARCH_RESULTS_COUNT.number)
                .callApi()
                .then().specification(VkGetUniversitiesApi.successResponse());
    }

    private int getOccurrencesInAnswers(List<VkGetUniversitiesAnswer> answers, Universities uni) {
        int occurrences = 0;
        for (VkGetUniversitiesAnswer answer : answers) {

            if (answer.id == (uni.id) && answer.title.equals((uni.title)))
                ++occurrences;
        }
        return occurrences;
    }

    //validate an object we've got in API response
    @Test
    public void validateSpellerAnswerAsAnObject() {
        List<VkGetUniversitiesAnswer> answers = VkGetUniversitiesApi.getVkGetUniversitiesAnswers(
                VkGetUniversitiesApi.with()
                        .searchQuery(UNI_SEARCH_QUERY.text)
                        .countryId(RUS.id)
                        .cityId(SPB.id)
                        .offset(SEARCH_RESULTS_OFFSET.number)
                        .count(SEARCH_RESULTS_COUNT.number)
                        .callApi());
        assertThat(answers.size(), lessThanOrEqualTo(SEARCH_RESULTS_COUNT.number));
        assertThat(answers.get(0).id, equalTo(SPBU.id));
        assertThat(answers.get(0).title, equalTo(SPBU.title));

        assertThat(getOccurrencesInAnswers(answers, RIMSKY_KORSAKOV_SPBGK), equalTo(1));
    }
}
