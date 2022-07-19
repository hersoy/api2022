package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void postPojo01() {
        // 1. step: set the url
        spec.pathParam("first","todos");

        // 2. step: set the expected data
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        // 3. step: send POST request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");

        // 4. step: do assertion
       JsonPlaceHolderPojo actualBody = response.as(JsonPlaceHolderPojo.class);
      // assertEquals(requestBody.getUserId(),actualBody.getUserId());
      // assertEquals(requestBody.getTitle(),actualBody.getTitle());
      // assertEquals(requestBody.getCamplated(),actualBody.getCamplated());

        assertEquals(requestBody.toString(),actualBody.toString());
        System.out.println(requestBody.toString());
        System.out.println(actualBody.toString());

    }
}
