package com.henry.expenseTracker.service.abstract_service;

import java.util.Map;
import java.util.Set;

public interface ModifyUserService {


    Map<String, Boolean> enabled(String username);

    Map<String, Set<String>> addRole(String username, String role);

    Map<String, Set<String>> removeRole(String username, String role);


}
