package com.example.demo;


import java.lang.reflect.Field;

public interface JsonConvertible {

    static String objectToJson(Object obj) {
        if (obj == null) {
            return "null";
        }

        Class<?> clazz = obj.getClass();
        StringBuilder jsonBuilder = new StringBuilder("{");

        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                String fieldName = field.getName();
                Object fieldValue = field.get(obj);

                if (fieldValue != null) {
                    jsonBuilder.append("\"").append(fieldName).append("\":");

                    if (field.getType().equals(String.class)) {
                        jsonBuilder.append("\"").append(fieldValue).append("\"");
                    } else {
                        jsonBuilder.append(fieldValue);
                    }

                    jsonBuilder.append(",");
                }
            }

            if (jsonBuilder.charAt(jsonBuilder.length() - 1) == ',') {
                jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Remove trailing comma
            }

            jsonBuilder.append("}");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return jsonBuilder.toString();
    }



}
