package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.jsonplaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    // De- Serialization: Json formatından Java objesine çevirmektir
    // Serialization: Java objesini JSON formatına çevirmek
    // De-Serialization ve Serialization 2 türlü yapılır.
    // Gson:(Google tarafından üretilmiştir, pek kullanmaaycağız)
    // Object Mapper: Daha popülerdir bunu kullanacağız


    @Test
    public void get01() {
        // 1. step: set the Url
        spec.pathParams("first", "todos", "second", 2);

        // 2. step: set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        expectedData.put("statusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");

        // 3. step: send the request and get response
        Response response = given().spec(spec).when().get("{first}/{second}");
        response.as(HashMap.class);     // response'ları hasMap'a cevirdik
        Map<String, Object> actualData = response.as(HashMap.class); // hashMap'a cevirdigimiz bu datalari bir Map'a atadik

        // 4. Step: Do assertion
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("statusCode"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.header("via"));
        assertEquals(expectedData.get("Server"), response.header("Server"));

    }

    @Test
    public void get02() {
        //1. Step: Set the Url
        spec.pathParams("first","todos","second",2);

        //2. Step: Set the expected data
        jsonplaceHolderTestData expectedData = new jsonplaceHolderTestData();
        Map<String,Object> expectedDataMap = expectedData.expectedDataWithAllKeys(1, "quis ut nam facilis et officia qui",false);
        expectedDataMap.put("StatusCode", 200);
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        //3. Step: Send the request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4. Step: Do Assertion

        assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));
        assertEquals(expectedDataMap.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"), response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"), response.getHeader("Server"));


    }
}
