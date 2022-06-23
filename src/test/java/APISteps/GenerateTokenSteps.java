package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;
import utils.APIPayloadConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    @Given("a JWT is generated")
    public void aJWTIsGenerated() {
        RequestSpecification request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).body(APIPayloadConstants.generateTokenPayload());

        Response response = request.when().post(APIConstants.GENERATE_TOKEN_URI);
//        response.prettyPrint();
        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);

    }
}
