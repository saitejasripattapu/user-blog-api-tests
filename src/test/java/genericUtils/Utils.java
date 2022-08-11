package genericUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException
    {

        if(req==null)
        {
            PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
            req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;


    }

    public static String getGlobalValue(String key) throws IOException
    {
        Properties prop =new Properties();

        System.out.println("CucumberOptions->"+System.getProperty("user.dir")+"//src//test//resources//cucumber.properties");
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//cucumber.properties");
        // FileInputStream fis =new FileInputStream(".\\src\\test\\resources\\cucumber.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
}
