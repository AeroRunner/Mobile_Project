package helpers;

import static io.restassured.RestAssured.given;

public class BrowserStackVideoAPI {
    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic("dmel_h1GNUp", "AV22pkQ4hvJw52o48UGz")
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
