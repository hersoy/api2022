package get_requests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void test02() {

        String url = "https://restful-booker.herokuapp.com/booking/1";

        Response response = given().when().get(url);
        response.prettyPrint();

        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        // Respomse body de bulunan spesifik bir veri nasil assert edilir:
        // assertTrue() metodu parantezin icindeki deger true ise testi gecirir
        assertTrue(response.asString().contains("Not Found"));

        // Respomse body de spesifik bir verinin bulunmadigi nasil assert edilir:
        // assertFalse() metodu parantezin icindeki deger false ise testi gecirir
        assertFalse(response.asString().contains("TechProEd"));

        // assertEquals() netodu parantez icindeki iki deger esit ise testi gecirir
        assertEquals("Cowboy",response.header("Server"));


    }


}
