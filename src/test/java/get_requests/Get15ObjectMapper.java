package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends HerOkuAppBaseUrl {
    /*
    Given
    https://restful-booker.herokuapp.com/booking/2
    When
    I send GET Request to the URL
            Then
    Status code is 200
    {
    "firstname": "Oliver",
    "lastname": "Smith",
    "totalprice": 100,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-07-18",
        "checkout": "2022-07-19"
    },
    "additionalneeds": "Breakfast"
    }
     */


    @Test
    public void Get15ObjectMapper() {

        //1. step: set the url
        spec.pathParams("first", "booking", "second", 22);

        //2. step: set the expected data
        String expectedData = " {\n" +
                "    \"firstname\": \"Oliver\",\n" +
                "    \"lastname\": \"Smith\",\n" +
                "    \"totalprice\": 100,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-18\",\n" +
                "        \"checkout\": \"2022-07-19\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "    }";

        BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

        //3. step: send the Get request and get the response
        Response response = given().spec(spec).when().contentType(ContentType.JSON).get("/{first}/{second}");
        response.prettyPrint();

        //4. step: Do assertion

        BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), BookingPojo.class);

        // Hard Assert
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(), actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(), actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalPrice(), actualDataPojo.getTotalPrice());
        assertEquals(expectedDataPojo.getDepositpaid(), actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getAdditionalneeds(), actualDataPojo.getAdditionalneeds());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(), actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(), actualDataPojo.getBookingdates().getCheckout());


/*

        // Soft Assertion
            //1. SoftAsset objesi olustur
        SoftAssert softAssert = new SoftAssert();

        //2. Assertion yap
        softAssert.assertEquals(actualDataPojo.getFirstname(), expectedDataPojo.getFirstname(), "firstname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getLastname(), expectedDataPojo.getLastname(), "lastname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getTotalPrice(), expectedDataPojo.getTotalPrice(), "total Price uyusmadi");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(), expectedDataPojo.getDepositpaid(), "deposit Paid uyusmadi");
        softAssert.assertEquals(actualDataPojo.getAdditionalneeds(), expectedDataPojo.getAdditionalneeds(), "additional needs uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(), expectedDataPojo.getBookingdates().getCheckin(), "checkin uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(), expectedDataPojo.getBookingdates().getCheckout(), "checkout uyusmadi");

            //3. assertAll() metodunu calistir
        softAssert.assertAll();

*/
    }
}
