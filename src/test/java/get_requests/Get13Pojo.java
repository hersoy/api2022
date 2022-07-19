package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
/*
    Given
    https://gorest.co.in/public/v1/users/2508
    When
    User send GET Request to the URL
            Then
    Status Code should be 200
    And
    Response body should be like
            {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Akshita Nehru",
                "email": "nehru_akshita@jast.info",
                "gender": "female",
                "status": "active"
            }
        }

 */

    @Test
    public void getPojo01() {
        // 1. step: set the url
        spec.pathParams("first","users", "second", 2508);

        // 2. step: set the expected data
        GoRestDataPojo gorest = new GoRestDataPojo(2508, "Akshita Nehru", "nehru_akshita@jast.info", "female", "active");
        GoRestPojo goRestPojo = new GoRestPojo(null, gorest);

        // 3. step: send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        // 4. step: do assertion
        GoRestPojo actualDataPojo = response.as(GoRestPojo.class);
        assertEquals(200, response.getStatusCode());

        assertEquals(goRestPojo.getMeta(), actualDataPojo.getMeta());

        assertEquals(goRestPojo.getData().getId(), actualDataPojo.getData().getId());
        assertEquals(goRestPojo.getData().getName(), actualDataPojo.getData().getName());
        assertEquals(goRestPojo.getData().getEmail(), actualDataPojo.getData().getEmail());
        assertEquals(goRestPojo.getData().getGender(), actualDataPojo.getData().getGender());
        assertEquals(goRestPojo.getData().getStatus(), actualDataPojo.getData().getStatus());



    }
}
