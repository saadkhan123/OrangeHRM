package RestAssured;

import Utils.BaseApiTest;
import Utils.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ApiTest extends BaseApiTest {

    private static final String BASE_URL = "https://reqres.in/api";


    // GET /users?page=2
    @Test
    public void testGetUsersPage2() {

        ExtentTest test = ExtentManager.getAPIExtent().createTest("GET /users?page=2");
        test.info("Sending GET request to fetch users on page 2");


        Response response = RestAssured
                .given()
                .baseUri(BASE_URL)
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().response();

        int statusCode = response.getStatusCode();
        test.info("Received response with status code: " + statusCode);

        if (statusCode == 200) {
            test.pass("Status code is 200 as expected.");
        } else {
            test.fail("Expected status code 200, but got " + statusCode);
        }


        int totalUsers = response.jsonPath().getList("data").size();
        test.info("Total users on page 2: " + totalUsers);

        if (totalUsers > 0) {
            test.pass("User list contains data.");
        } else {
            test.fail("User list is empty.");
        }

        Assert.assertTrue(totalUsers > 0, "User list should not be empty.");
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200.");
    }

    // POST /users
    @Test(dataProvider = "userDataProvider")
    public void testCreateUser(String name, String job) {

        ExtentTest test = Utils.ExtentManager.getAPIExtent().createTest("POST /users - Create User: " + name);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("job", job);

        test.info("Preparing JSON request with Name: " + name + ", Job: " + job);

        Response response = RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody)
                .when()
                .post("/users");


        int statusCode = response.getStatusCode();
        test.info("Received status code: " + statusCode);

        if (statusCode == 201) {
            test.pass("User created successfully with status code 201.");
        } else {
            test.fail("Expected status code 201 but got: " + statusCode);
        }

        String responseName = response.jsonPath().getString("name");
        String responseJob = response.jsonPath().getString("job");
        String responseId = response.jsonPath().getString("id");
        String createdAt = response.jsonPath().getString("createdAt");

        test.info("Validating response body");
        test.info("Response name: " + responseName);
        test.info("Response job: " + responseJob);
        test.info("ID: " + responseId);
        test.info("CreatedAt: " + createdAt);

        // Assertions
        try {
            Assert.assertEquals(responseName, name, "Name does not match.");
            Assert.assertEquals(responseJob, job, "Job does not match.");
            Assert.assertNotNull(responseId, "ID should not be null.");
            Assert.assertNotNull(createdAt, "createdAt should not be null.");
            test.pass("All response validations passed.");
        } catch (AssertionError e) {
            test.fail("Assertion failed: " + e.getMessage());
            throw e; // Rethrow to let TestNG handle failure
        }
    }
    @DataProvider(name = "userDataProvider")
    public Object[][] userDataProvider() {
        return new Object[][]{
                {"Mark", "Chapman"},
                {"Sohaib", "Maqsood"},
                {"Virat", "Kohli"}
        };
    }

    // DELETE /users/{id}
    @Test
    public void testDeleteUser() {
        int userId = 2;
        ExtentTest test = ExtentManager.getAPIExtent().createTest("DELETE /users/" + userId);

        test.info("Sending DELETE request to delete user with ID: " + userId);

        Response response = RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("x-api-key","reqres-free-v1")
                .when()
                .delete("/users/" + userId);
        int statusCode = response.getStatusCode();
        test.info("Received status code: " + statusCode);

        if (statusCode == 204) {
            test.pass("User deletion simulated successfully with status code 204.");
        } else {
            test.fail("Expected status code 204 but got: " + statusCode);
        }

        // TestNG assertion to fail test if response is wrong
        Assert.assertEquals(statusCode, 204, "Expected 204 status code for deletion.");
    }
}
