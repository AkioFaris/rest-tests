package mal;/// import beans.MyAnimeListApiAnswer;

import core.mal.MyAnimeListApi;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class TestMyAnimeList {

    @Test
    public void useBaseRequestAndResponseSpecifications() {
        given(MyAnimeListApi.baseRequestConfiguration())
                .log().all()
                .post().prettyPeek()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
