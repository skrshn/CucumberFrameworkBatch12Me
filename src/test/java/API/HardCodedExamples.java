package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTUzMzQ5MDYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NTM3ODEwNiwidXNlcklkIjoiODMifQ.VbxU858Xh4Pzz_k6_0JNp02t6vDROvQi5v4j_N76J5g";

    static String employee_id;

    @Test
    public void acreateEmployee() {
        RequestSpecification request = given().header("Content-Type", "application/json").header("Authorization", token).body("{\n" +
                "  \"emp_firstname\": \"zakstring\",\n" +
                "  \"emp_lastname\": \"string\",\n" +
                "  \"emp_middle_name\": \"string\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2022-06-12\",\n" +
                "  \"emp_status\": \"string\",\n" +
                "  \"emp_job_title\": \"string\"\n" +
                "}");

        Response response = request.when().post("/createEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);

        //Hamcrest Matchers
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname", equalTo("zakstring"));
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
//        response.then().assertThat().body("Employee.employee_id", equalTo(employee_id));
    }

    @Test
    public void bgetCreatedEmployee() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token).queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        String temp_employee_id = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(temp_employee_id, employee_id);
    }

    @Test
    public void cupdateEmployee() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token).body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_firstname\": \"zakz2\",\n" +
                        "  \"emp_lastname\": \"string\",\n" +
                        "  \"emp_middle_name\": \"string\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2022-06-13\",\n" +
                        "  \"emp_status\": \"Manager\",\n" +
                        "  \"emp_job_title\": \"string\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

//        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
//        response.then().assertThat().body("Employee.emp_firstname", equalTo("zakz2"));
    }

    @Test
    public void dgetUpdatedEmployee() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void egetAllEmployees() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token);
        Response response = preparedRequest.when().get("/getAllEmployees.php");
        response.then().assertThat().statusCode(200);
        String allEmployees = response.prettyPrint();

        JsonPath js = new JsonPath(allEmployees);
        int count = js.getInt("Employees.size()");
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            String empId = js.getString("Employees[" + i + "].employee_id");
            System.out.println(empId);
        }
    }

}
