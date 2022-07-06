package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    /*
    1) Postman manuel API testi icin kullanilir.
    2) API Otomaston testi icin Rest-Assured Library kullaniyoruz
    3) Otomasyon kodlarinin yazimi icin su adimlari izliyoruz:
        a) Gereksinimleri anlama - understanging requirement
        b) Test case'i yazma
            - Test case yazimi icin 'Gherkin Language' kullaniriz
                Gherkin  bazi keywordlere sahip, bunlar:
                x) Given: on kosullar
                y) When : yapacagimiz actionlar --> Get, Put, Delete gibi
                z) Then : Donutler --> Assert, Dogrulama ....
                t) And : Coklu islemler icin kullanilir

        c) Otomasyonda test kodunun yazımı yapılacak
                a) Set the URL (URl'yi kurmak)
                b) Set the expected Data (beklenen datanın oluşturulması)   """POST, PUT PATCH"""
                c) Type code to send request (talep göndermek için kod yazma)
                d) Do Assertion (doğrulama yapma)
     */
    /*
        Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
    */

    @Test
    public void get01() {
        // a) Set the URL (URl'yi kurmak)
        String url= "https://restful-booker.herokuapp.com/booking/555";

        // b) Set the expected Data (beklenen datanın oluşturulması)   """POST, PUT PATCH"""

        // c) Type code to send request (talep göndermek için kod yazma)
        Response response = given().when().get(url);
        response.prettyPrint();

        // d) Do Assertion (doğrulama yapma)
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        // Status Code nasil yazdirilir:
        System.out.println("Status Code : "+response.statusCode());     //Status Code : 200

        // Content type nasil yazdirilir:
        System.out.println("Content Type : "+response.contentType());   //Content Type : application/json; charset=utf-8

        // Status Line nasil yazdirilir:
        System.out.println("Status Line : "+response.statusLine());     //Status Line : HTTP/1.1 200 OK

        // Header nasil yazdirilir:
        System.out.println(response.header("User-Agent"));

        // Headers nasil yazdirilir:
        System.out.println("Headers:\n"+response.headers());

        // Time nasil yazdirilir:
        System.out.println("Time : "+response.getTime());


    }





}
