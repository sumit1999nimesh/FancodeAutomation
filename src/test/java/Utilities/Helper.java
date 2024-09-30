package Utilities;


import io.restassured.path.json.JsonPath;

import java.util.*;
import java.util.stream.Collectors;
public class Helper {
	// Method to fetch user of city fancode
	 public List<Map<String, Object>> filterUsersByCity(List<Map<String, Object>> users) {
	        return users.stream()
	                .filter(user -> {
	                    Map<String, Object> address = (Map<String, Object>) user.get("address");
	                    Map<String, String> geo = (Map<String, String>) address.get("geo");
	                    float lat = Float.parseFloat(geo.get("lat"));
	                    float lng = Float.parseFloat(geo.get("lng"));
	                    return lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100;
	                })
	                .collect(Collectors.toList());
	    }

		// Method to fetch user have task completed more than 50%
	 public double calculateCompletedPercentage(List<Map<String, Object>> todos, int userId) {
	        List<Map<String, Object>> userTodos = todos.stream().filter(todo -> (int) todo.get("userId") == userId).collect(Collectors.toList());
	        if (userTodos.isEmpty()) {
	            return 0;
	        }
	        long completedCount = userTodos.stream()
	                .filter(todo -> (boolean) todo.get("completed"))
	                .count();
	        return (double) completedCount / userTodos.size() * 100;
	    }
	 
}
