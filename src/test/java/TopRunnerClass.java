
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.Helper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class TopRunnerClass {
   @Test
   public void GetUsersHavingTaskCompletedGreaterThan50() {
	     RestAssured.baseURI="http://jsonplaceholder.typicode.com/";
	     // fetch users
	     List<Map<String, Object>> users = RestAssured.given().get("users").jsonPath().getList("$");
	     // fetch todos
	     List<Map<String, Object>> todos = RestAssured.given().get("todos").jsonPath().getList("$");
	    Helper helper = new Helper();
	    List<Map<String, Object>> fanCodeUsers = helper.filterUsersByCity(users);

	    System.out.println("List of users of City `FanCode` and have more than half of their todos task completed. ");
   for (Map<String, Object> user : fanCodeUsers) {
       int userId = (int) user.get("id");
       double completedPercentage = helper.calculateCompletedPercentage(todos, userId); 
       if(completedPercentage>50) {
    	   System.out.println(user.get("name"));
       }
   }
   }
}

