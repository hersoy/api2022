package delete_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiResponseBodyPojo;
import pojos.DummyDeletePojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiBaseUrl {

    /*
    URL: https://dummy.restapiexample.com/api/v1/delete/2
   HTTP Request Method: DELETE Request
   Test Case: Type by using Gherkin Language
   Assert:     i) Status code is 200
           ii) "status" is "success"
          iii) "data" is "2"
           iv) "message" is "Successfully! Record has been deleted"
 */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/delete/2
    When
        User send the delete request
    Then
        Status code is 200
    And
       "status" is "success"
    And
        "data" is "2"
    And
       "message" is "Successfully! Record has been deleted"
     */

    @Test
    public void delete01() {
        spec.pathParams("first","delete", "second", 2);
        DummyDeletePojo expectedData = new DummyDeletePojo("success", 2, "Successfully! Record has been deleted");

        Response response = given().spec(spec).body(expectedData).when().delete("/{first}/{second}");
        response.prettyPrint();

        DummyDeletePojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(), DummyDeletePojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getData(), actualData.getData());
        assertEquals(expectedData.getMessage(), actualData.getMessage());


    }
}
