package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */


    @Test
    public void get01ObjectMapper() {

        //1. step: set the url
        spec.pathParams("first", "todos", "second", 198);

        //2. step: set the expected data

        String expectedData = "{\n" +
                "\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "  }";                  // yukaridaki json body'i string olarak kullanacagimizdan
        // bir konteynır'a atama yapip asagida kullandik

        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);

        //3. step: send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4. step: Do assertion
        HashMap<String, Object> actuelDataMap = JsonUtil.convertJsonToJavaObject(response.asString(), HashMap.class);
        assertEquals(200, response.getStatusCode());

        assertEquals(expectedDataMap.get("userId"), actuelDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actuelDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actuelDataMap.get("completed"));

    }

    @Test
    public void get02ObjectMapper() {

        //1. step: set the url
        spec.pathParams("first", "todos", "second", 198);

        //2. step: set the expected data
        String expectedData = "{\n" +
                "\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "  }";                   // yukaridaki json body'i string olarak kullanacagimizdan
        // bir konteynır'a atama yapip asagida kullandik

        JsonPlaceHolderPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, JsonPlaceHolderPojo.class);

        //3. step: send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4. step: Do assertion
        JsonPlaceHolderPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.toString(), JsonPlaceHolderPojo.class);

        assertEquals(200, response.getStatusCode());

        assertEquals(expectedDataPojo.getUserId(), actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(), actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCamplated(), actualDataPojo.getCamplated());


    }

}
