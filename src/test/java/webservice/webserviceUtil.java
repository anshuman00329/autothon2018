package webservice;
import Listeners.MyListener;
import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.Reporter;
import com.google.gson.JsonObject;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class webserviceUtil extends MyListener {

    /**
     * Method to get the respons2e for specified webservice
     *
     * @return response : returns the response received
     */
     @Step("Get Response Step")
    public Response getResponse()
    {
        //System.out.println("JSON URL is: " + baseURL + basePath);
        Response response;
                 response = given()
                         .when()
                         .param("")
                         .get("http://network-manager.tlmqe1.manhdev.com/api/networkmanager/staticRoute/staticRouteId/VSR1");


       /* when().
                get("http://network-manager.tlmqe1.manhdev.com/api/networkmanager/staticRoute/staticRouteId/VSR3");
*/
        validateStatusCode(response, HttpStatus.SC_OK);
        System.out.println("Response"+response.asString());

        return response;
    }

    @Step("Post Response Step")
    public Response postRequest(String jsonPost)
    {
        Response response = given()
                            .body(jsonPost)
                            .when()
                            .contentType(ContentType.JSON)
                            .post("http://localhost:3000/posts");

        validateStatusCode(response, HttpStatus.SC_OK);
        return response;
    }
    public Response patchRequest(String jsonPatch) {

        Response response = given().when().contentType(ContentType.JSON)
                .body(jsonPatch).patch("http://localhost:3000/posts/2");

        System.out.println(response.asString());
        validateStatusCode(response, HttpStatus.SC_OK);
        return response;
    }

    public void deleteRequest(String deleteID) {
       // String deleteID = commonmethods.getInputData("Webservice", "DeleteID");

        Response response = given().when().contentType(ContentType.JSON)
                .delete("http://localhost:3000/posts/2");

        System.out.println(response.asString());
        validateStatusCode(response, HttpStatus.SC_OK);
    }


    public void validateStatusCode(Response response, int expectedStatus) {

        try{
            response.then().assertThat().statusCode(expectedStatus);
        }
        catch (Exception e)
        {
            test.log(LogStatus.INFO, ExceptionUtils.getStackTrace(e));
        }

    }

    public String validateResponse(Response response) throws JSONException {

        String body = response.asString();
        //JsonPath jpath = new JsonPath(body);

        JSONObject jObject = new JSONObject(body);
        String profile = jObject.getJSONObject("data").getString("ProfileId");
        System.out.println("Profile: " + profile);
        return profile;
    }

    public void ValidateJSONListResponse(Response response) throws JSONException{

        JSONArray jsonArray = new JSONArray(response.asString());
        for(int index = 0;index < jsonArray.length(); index++) {
            JSONObject jsonObject = jsonArray.getJSONObject(index);
            System.out.println(""+jsonObject);
            System.out.println(""+jsonObject.get("displayTitle"));
        }

    }

}
