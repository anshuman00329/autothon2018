package webservice;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONException;
import Listeners.MyListener;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class webServiceTest {
    static Logger logger = Logger.getLogger(webServiceTest.class);

    @BeforeTest
    public void beforetestSet()
    {
        String log4jConfigFile = System.getProperty("user.dir")
                + File.separator + "log4j.properties";
            PropertyConfigurator.configure(log4jConfigFile);

}
    @Test(priority = 0)
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
        String jsonPost = "{\"id\":\"6\", \"title\":\"dummyTitle\", \"author\":\"Vidya\"}";
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
