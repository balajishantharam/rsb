package com.learnjava.custom.rule.engine;

import java.util.HashMap;
import java.util.Map;

public class TypeValidationRuleEngine {

    private Map<Class<?>, ValidationRule<?>> rules = new HashMap<>();

    public <T> void registerRule(Class<T> type, ValidationRule<T> rule) {
        rules.put(type, rule);
    }

    public <T> boolean validate(T object) {
        ValidationRule<T> rule = (ValidationRule<T>) rules.get(object.getClass());
        if (rule != null) {
            return rule.validate(object);
        }
        return true; // Default to true if no rule is found
    }

    public interface ValidationRule<T> {
        boolean validate(T object);
    }

    public static void main(String[] args) {
        TypeValidationRuleEngine engine = new TypeValidationRuleEngine();

        // Register a rule for Integer type
        engine.registerRule(Integer.class, value -> value > 0);

        // Register a rule for String type
        engine.registerRule(String.class, value -> value.length() < 20);

        // Validate an Integer
        Integer num = 10;
        System.out.println("Validating Integer: " + engine.validate(num)); // True

        // Validate a String
        String str = "Hello World";
        System.out.println("Validating String: " + engine.validate(str)); // True

        // Validate an invalid Integer
        Integer invalidNum = -5;
        System.out.println("Validating Integer: " + engine.validate(invalidNum)); // False
    }
}