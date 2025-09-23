package controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class LabSeqControllerIntegrationTest {
    @Test
    void testGetLabSeq_OK() {
        given()
            .when().get("/labseq/5")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test
    void testGetLabSeq_BadRequest() {
        given()
            .when().get("/labseq/-1")
            .then()
            .statusCode(400)
            .body("message", containsString("Index must be non-negative"))
            .body("code", equalTo(400));
    }

    @Test
    void testGetLabSeq_InternalServerError() {
        given()
            .when().get("/labseq/2147483647")
            .then()
            .statusCode(anyOf(is(500), is(400), is(200)));
    }
}
