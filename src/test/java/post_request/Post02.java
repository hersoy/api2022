package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {

    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */

    @Test
    public void post01() {
        // 1. step: set the url
        spec.pathParam("first", "booking");

        // 2. step: set the expected data
        HerOkuAppTestData herokuapp = new HerOkuAppTestData();
        Map<String, String> bookingdatesMap = herokuapp.bookingdatesSetUp("2021-09-09", "2021-09-21");

        Map<String, Object> expectedDataMap = herokuapp.expectedDataSetUp("John", "Doe", 11111, true, bookingdatesMap);

        // 3. step: send the request get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        // 4. step: Do assertion
        Map<String, Object> actuelDataMap = response.as(HashMap.class);

        assertEquals(response.statusCode(), 200);

        assertEquals(expectedDataMap.get("firstname"), ((Map) actuelDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map) actuelDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map) actuelDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map) actuelDataMap.get("booking")).get("depositpaid"));

        assertEquals(bookingdatesMap.get("checkin"), ((Map) ((Map) actuelDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map) ((Map) actuelDataMap.get("booking")).get("bookingdates")).get("checkout"));


    }
}
