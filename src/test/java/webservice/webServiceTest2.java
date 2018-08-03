package webservice;

import org.testng.annotations.Test;

public class webServiceTest2 {
    @Test
    public void deleteRequestTest()
    {
        webserviceUtil wUtil = new webserviceUtil();
        wUtil.deleteRequest("2");

    }
}
