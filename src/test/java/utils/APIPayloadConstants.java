package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String generateTokenPayload(){
        String generateToken = "{\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\"\n" +
                "}";
        return generateToken;
    }

    public static String createEmployeePayload(){
        String createEmployee = "{\n" +
                "  \"emp_firstname\": \"sak\",\n" +
                "  \"emp_lastname\": \"shn\",\n" +
                "  \"emp_middle_name\": \"zak\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1990-06-12\",\n" +
                "  \"emp_status\": \"OFF\",\n" +
                "  \"emp_job_title\": \"IT MANAGER\"\n" +
                "}";
        return createEmployee;
    }

    //passing the body from json object
    public static String createEmployeePayloadViaJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname","sak");
        obj.put("emp_lastname","shn");
        obj.put("emp_middle_name","zak");
        obj.put("emp_gender","M");
        obj.put("emp_birthday","1990-06-12");
        obj.put("emp_status","OFF");
        obj.put("emp_job_title","IT MANAGER");
        return obj.toString();
    }

    //passing the body from json object
    public static String createEmployeePayloadViaDynamicScenario(String firstName, String lastName, String middleName, String gender, String dob, String status, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname",firstName);
        obj.put("emp_lastname",lastName);
        obj.put("emp_middle_name",middleName);
        obj.put("emp_gender",gender);
        obj.put("emp_birthday",dob);
        obj.put("emp_status",status);
        obj.put("emp_job_title",jobTitle);
        return obj.toString();
    }
}
