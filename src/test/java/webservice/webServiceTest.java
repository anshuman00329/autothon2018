package webservice;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.json.JSONException;
import Listeners.MyListener;
import org.testng.annotations.Test;

public class webServiceTest {
    //@Test(priority = 0)
    public void webServiceGetTest() throws JSONException {
        webserviceUtil wUtil = new webserviceUtil();
        Response response = wUtil.getResponse();
        System.out.println("status:"+response.getStatusCode());
        wUtil.validateResponse(response);
    }

    @Test(priority = 1, description = "Post REquest DEsc")
    public void postRequestTest()
    {
        webserviceUtil wUtil = new webserviceUtil();
        String jsonPost = "{\"id\":\"2\", \"title\":\"dummyTitle\", \"author\":\"Vidya\"}";
        Response postResponse = wUtil.postRequest(jsonPost);
        System.out.println("Rsponse:"+postResponse.asString());

    }

    @Test
    public void patchRequestTest()
    {
        webserviceUtil wUtil = new webserviceUtil();
        String jsonPatch = "{\"title\":\"PatchedTitle\"}";
        Response patchResponse = wUtil.patchRequest(jsonPatch);
        System.out.println("Rsponse:"+patchResponse.asString());

    }

    //@Test
    public void deleteRequestTest()
    {
        webserviceUtil wUtil = new webserviceUtil();
        wUtil.deleteRequest("2");

    }
}
