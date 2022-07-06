package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
     */

    @Test
    public void get01() {
        // 1. step: set the Url
        // String Url = "https://jsonplaceholder.typicode.com/todos/23";   // tercih edilmez
        spec.pathParams("first", "todos", "second", 23);

        // 2. step: Set the expected data

        // 3. step: Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        // spec ile base url'i, first ile todos'u, second ile 23'u aliyorum

        // 4. step: Do assertion

        // 1.YOL:
        response.then().assertThat().statusCode(200)
                .contentType("application/json")
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(2));

        // 2.YOL:
        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));


    }

    /*
    Not 1: Assertion yaparken Java calismayi durdurdugunda hata sonra kodlar calismaz.
            boylece hata sonrasi assertion lar hakkinda bilgi sahibi olanayiz
            fakat hata oncesi assertion lar gecmistir

     Not 2: Eger kodumuzu hata noktasinda duracak sekilde yazarsak "Hard Assertion" yapmis oluruz
     Not 3: Eger kodumuzu hata noktasinda durmayacak sekilde yazarsak "Soft Assertion" yapmis oluruz
     Not 4: Eger coklu body() metodu icinde assert yaparsak "Hard Assert",
            tek body() metodu icinde assert yaparsak "Soft Assert" yapmis oluruz.
     */

}
