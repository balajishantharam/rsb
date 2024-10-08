package com.learnjava.custom.rule.engine;

import java.util.List;
import java.util.ArrayList;

public class RuleEngine {

    public interface Rule {
        boolean evaluate(Object data);
        String getMessage();
    }

    private List<Rule> rules = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public List<String> validate(Object data) {
        List<String> errors = new ArrayList<>();
        for (Rule rule : rules) {
            if (!rule.evaluate(data)) {
                errors.add(rule.getMessage());
            }
        }
        return errors;
    }

    public static void main(String[] args) {
        RuleEngine engine = new RuleEngine();

        // Add validation rules
        engine.addRule(new Rule() {
            @Override
            public boolean evaluate(Object data) {
                User user = (User) data;
                return user.getAge() >= 18;
            }

            @Override
            public String getMessage() {
                return "User must be 18 years or older.";
            }
        });

        engine.addRule(new Rule() {
            @Override
            public boolean evaluate(Object data) {
                User user = (User) data;
                return user.getEmail().contains("@");
            }

            @Override
            public String getMessage() {
                return "Invalid email format.";
            }
        });

        // Create a user object
        User user = new User("John", 20, "john@example.com");

        // Validate the user object
        List<String> errors = engine.validate(user);

        // Print validation results
        if (errors.isEmpty()) {
            System.out.println("Validation successful.");
        } else {
            System.out.println("Validation failed:");
            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}

class User {
    private String name;
    private int age;
    private String email;

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getters
    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}