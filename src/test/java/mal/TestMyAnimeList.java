package mal;/// import beans.MyAnimeListApiAnswer;

import core.mal.MyAnimeListApi;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

import static core.mal.MyAnimeListConstants.*;
import static core.mal.XmlToStringTransformer.*;



public class TestMyAnimeList {
    // use base request and response specifications to form request and validate response.
    // simple usage of RestAssured library: direct request call and response validations in test.
    @Test
    public void simpleMalApiCall() {
        given()
                .param("q", "dog")
                // .accept(ContentType.XML)
                .auth().basic(USER_LOGIN, USER_PASSWORD)
                .header("custom header1", "header1.value")
                .and()
                /// .body("some body payroll")
                .log().everything()
                .when()
                .get("https://myanimelist.net/api/anime|manga/search.xml")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                // .contentType(ContentType.JSON)
                .time(lessThan(20000L)); // Milliseconds
    }

    @Test
    public void useBaseRequestAndResponseSpecifications() {
        String animeMajorData = GenerateStringFromXml("src/test/resources/AnimeValues.xml");
        System.out.println("\n" + animeMajorData + "\n");
                given(MyAnimeListApi.baseRequestConfiguration())
                .param(PARAM_ANIME_ID, "21")
                .param(PARAM_ANIME_DATA, animeMajorData)
                        .log().all()
                .post().prettyPeek()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
/*
    @Test
    public void reachBuilderUsage() {
        MyAnimeListApi.with()
                .options("5")
                .text(wrongWordUK)
                .callApi()
                .then().specification(MyAnimeListApi.successResponse());
    }


    //validate an object we've got in API response
    @Test
    public void validateSpellerAnswerAsAnObject() {
        List<MyAnimeListAnswer> answers =
                MyAnimeListApi.getMyAnimeListAnswers(
                        MyAnimeListApi.with().text("motherr fatherr," + wrongWordEn).callApi());
        assertThat("expected number of answers is wrong.", answers.size(), equalTo(3));
        assertThat(answers.get(0).word, equalTo("motherr"));
        assertThat(answers.get(1).word, equalTo("fatherr"));
        assertThat(answers.get(0).s.get(0), equalTo("mother"));
        assertThat(answers.get(1).s.get(0), equalTo("father"));
        assertThat(answers.get(2).s.get(0), equalTo(rightWordEn));
    }
*/
}
