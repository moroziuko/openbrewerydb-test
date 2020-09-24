import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BreweriesApi {

    private String getDomain() {
        // Hardcoded domain, but in real project it could be retrieved from system properties or property files etc.
        return "https://api.openbrewerydb.org";
    }

    public RequestSpecification init() {
        return RestAssured.given().relaxedHTTPSValidation().baseUri(getDomain()).contentType("application/json").log().all();
    }

    public Response searchBreweries(String searchTerm) {
        // Parameter Keys and Urls could be moved to enum classes in real project:
        return init().param("query", searchTerm).get("/breweries/search").prettyPeek();
    }
}
