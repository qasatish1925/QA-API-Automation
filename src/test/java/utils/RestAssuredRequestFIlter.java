package utils;

import io.restassured.filter.*;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;


public class RestAssuredRequestFIlter implements Filter {
    public Response filter(FilterableRequestSpecification requestSpec , FilterableResponseSpecification responseSpec , FilterContext ctx) {
        Response response = ctx.next(requestSpec , responseSpec);
        return response;
    }
}
