package stepDefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import genericUtils.APIList;
import genericUtils.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.CommentsModel;
import pojo.PostsModel;
import pojo.UserModel;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class MyStepdefs extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    static Integer userID=null;
    static List<Integer> postID=null;
    static List<Integer> commentsID=null;
    static List<String> emails=null;
    String regexPattern;
    Pattern pattern;
    Matcher matcher;
    @When("User will call {string} API with {string} http request")
    public void user_will_call_api_with_http_request(String resource, String httpMethod) throws IOException {
        APIList apiList = APIList.valueOf(resource);
        res = given().spec(requestSpecification());
        if(httpMethod.equalsIgnoreCase("POST")) {
            response=res.when().post(apiList.getResource());
        }
        else if(httpMethod.equalsIgnoreCase("GET"))
        {
            response=res.when().get(apiList.getResource());
        }

    }
    @Then("verify the response and get the userID for {string} user")
    public void verify_the_response_and_get_the_user_id_for_user(String name) {
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        List<UserModel> users = Arrays.asList(response.getBody().as(UserModel[].class));
        System.out.println("No.of users->"+users.size());
        Integer id=null;
        for (int i=0;i < users.size();i++)
        {
            if(users.get(i).getUsername().equalsIgnoreCase("Delphine")) {
                id = users.get(i).getId();
                userID=id;
            }
        }
        System.out.println("Delphine UserId is ->" + userID );

    }


    @Then("verify the response and get the posts written by user")
    public void verifyTheResponseAndGetThePostsWrittenByUser() {
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        List<PostsModel> postsList = Arrays.asList(response.getBody().as(PostsModel[].class));
        int counter=0;
        List<Integer> postid = new ArrayList<>();

        for (int i=0;i < postsList.size();i++)
        {
            if(postsList.get(i).getUserId() == userID) {
                counter=counter+1;
                 postid.add(postsList.get(i).getId());
                System.out.println("PostID ->" + postsList.get(i).getId());
                postID=postid;
            }

        }
        System.out.println("Total posts by the user ->"+ counter);
        }

    @Then("verify the response and get the comments for each post")
    public void verifyTheResponseAndGetTheCommentsForEachPost() {
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        final int[] counter = {0};
        List<Integer> commentsid = new ArrayList<>();
        List<String> emailList = new ArrayList<>();
        List<CommentsModel> commentsList = Arrays.asList(response.getBody().as(CommentsModel[].class));
        postID.forEach(post -> {
            for (int i = 0; i < commentsList.size(); i++) {
                if (commentsList.get(i).getPostId() == post) {
                    counter[0] = counter[0] + 1;
                    commentsid.add(commentsList.get(i).getId());
                    emailList.add(commentsList.get(i).getEmail());
                    System.out.println("For Post ->" + post + "\t" + "CommentID ->" + commentsList.get(i).getId());
                    commentsID = commentsid;
                    emails=emailList;
                }
            }
            System.out.println("==========================================================");
        });

    }

    @And("validate the email in each comment")
    public void validateTheEmailInEachComment() {
        regexPattern = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(regexPattern);
        emails.forEach(email ->{
              matcher = pattern.matcher(email);
              Assert.assertTrue(matcher.matches());
                System.out.println(email +" : "+ matcher.matches()+"\n");
        });
    }
}


