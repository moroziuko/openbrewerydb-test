import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.Brewery;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


class SearchBreweriesTest {

    private static final String SEARCH_URL_NO_PARAM_VALUE = "/breweries/search?query=";
    private static final String SEARCH_URL_INVALID_PARAM_KEY = "/breweries/search?qwerty=dog";

    private static final BreweriesApi BREWERIES_API = new BreweriesApi();

    @ParameterizedTest
    @DisplayName("Search for breweries based on a search term. Valid search data")
    @ValueSource(strings = {"dog", "cat?", "123", "hello_", "123 W", "@gmail.com"})
    void searchBreweriesTest(String searchTerm) {
        Response response = BREWERIES_API.searchBreweries(searchTerm);
        assertThat(response.getStatusCode()).isEqualTo(200); // This assertion can be performed in searchBreweries method using rest assured capabilities, but in this case I would need to think about complex solutions for different status codes (like status codes as parameters, abstractions etc.)

        Brewery[] breweries = response.getBody().as(Brewery[].class);
        int breweryIndex = new Random().nextInt(breweries.length);
        assertThat(breweries).isNotEmpty();
        assertThat(breweries[breweryIndex]).hasNoNullFieldsOrPropertiesExcept("longitude", "latitude"); // Improvisation due to lack of requirements :)

        // Based on requirements, in real project could be created:
        // 1) Method that helps to verify actual Brewery object has required fields
        // 2) Factory that generates expected data in order to verify with it with actual data (in case if expected data is known and can be retrieved somehow)
    }

    @ParameterizedTest
    @DisplayName("Search for breweries based on a search term. Empty search results")
    @ValueSource(strings = {"", " ", "qwerty", "1234567890", "@", "%"})
    void searchBreweriesEmptyResultTest(String searchTerm) {
        Response response = BREWERIES_API.searchBreweries(searchTerm);
        verifySearchResultIsEmpty(response);
    }

    @Test
    @DisplayName("Search for breweries based on a search term. No search parameter value")
    void searchBreweriesEmptySearchDataTest() {
        Response response = BREWERIES_API.init().get(SEARCH_URL_NO_PARAM_VALUE).prettyPeek();
        verifySearchResultIsEmpty(response);
    }

    @Test
    @DisplayName("Search for breweries based on a search term. Invalid parameter key")
    void searchBreweriesInvalidKeyTest() {
        Response response = BREWERIES_API.init().get(SEARCH_URL_INVALID_PARAM_KEY).prettyPeek();
        verifySearchResultIsEmpty(response);
    }

    private void verifySearchResultIsEmpty(Response response) {
        assertThat(response.getStatusCode()).isEqualTo(200);
        Brewery[] breweries = response.getBody().as(Brewery[].class);
        assertThat(breweries).isEmpty();
    }
}
