package ApiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.net.MalformedURLException;


public class GetPeopleListAPI {

    @Test
    public void JsonPathUsage() throws MalformedURLException {
        RestAssured.baseURI = "https://swapi.dev/api/people/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");
        System.out.println("Response Body -> " + response.body().asPrettyString());

        //assert response
        response.then().assertThat().body("results.name[2]", Is.is("R2-D2"));
        response.then().assertThat().body("results.skin_color[2]", Is.is("white, blue"));

        People[] peoples = response.jsonPath().getObject("results", People[].class);

        // Iterate over the list and print each person details
        for (People people : peoples) {

            System.out.println("People List:" + people.name);

        }

    }
}
