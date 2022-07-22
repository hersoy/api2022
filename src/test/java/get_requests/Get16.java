package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {

    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    /*
    given
        URL: https://dummy.restapiexample.com/api/v1/employees
    when
        user send get request
    then
         Status code is 200
    and
        There are 24 employees
    and
        "Tiger Nixon" and "Garrett Winters" are among the employees
    and
        The greatest age is 66
    and
        The name of the lowest age is "Tatyana Fitzpatrick"
    and
        Total salary of all employees is 6,644,770

     */

    @Test
    public void get01() {
        // 1. step: set the url
        spec.pathParams("first", "employees");

        // 2. step: set the expected data

        // 3. step: send the Get request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        // 4. step: Do assertion
        response.
                then().
                assertThat().
                statusCode(200).    // i) Status code is 200
                body("data.id", hasSize(24),  // ii) There are 24 employees
                "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));    // iii) "Tiger Nixon" and "Garrett Winters" are among the employees

        // iv) The greatest age is 66
        JsonPath json = response.jsonPath();
        List<Integer> ageList = json.getList("data.findAll{it.id>0}.employee_age");
        Collections.sort(ageList);
        System.out.println(ageList);
        System.out.println(ageList.get(ageList.size() - 1));

        assertEquals(66,(int)ageList.get(ageList.size()-1));


        // v) The name of the lowest age is "Tatyana Fitzpatrick"
        String grovyString = "data.findAll{it.employee_age=="+ageList.get(0)+"}.employee_name";
        String minAgeEmployeeName=json.getString(grovyString);
        System.out.println(minAgeEmployeeName);

        assertEquals(minAgeEmployeeName,"[Tatyana Fitzpatrick]");


        // vi) Total salary of all employees is 6,644,770

        List<Integer>salaryList = json.getList("data.findAll{it.employee_salary>0}.employee_salary");
        System.out.println(salaryList);

        int salaryTotal = salaryList.stream().reduce(0,Integer::sum);
        System.out.println(salaryTotal);
        assertEquals(6644770, salaryTotal);

    }

}
