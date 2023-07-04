package com.example.commerceServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
	private static Map<String, User> userMap = new HashMap<>();

	public static void addUser(User user) {
		userMap.put(user.getUsername(), user);
	}

	public static User getUser(String username) {
		return userMap.get(username);
	}


}
