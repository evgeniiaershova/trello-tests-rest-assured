package dataloaders.http.requests;

import dataloaders.http.response.ResponseStatusType;
import dataloaders.http.response.RestResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static dataloaders.http.ExceptionHandler.exception;
import static dataloaders.http.JdiHttpSettigns.verifyOkStatus;
import static java.lang.System.currentTimeMillis;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestRequest {
    public static RestResponse doRequest(
            RestMethodTypes methodType, RequestSpecification spec, ResponseStatusType excpecedtStatus) {
        Response response;
        long time;
        try {
            time = currentTimeMillis();
            response = methodType.method.apply(spec);
            time = currentTimeMillis() - time;
        } catch (Exception ex) { throw exception("Request failed"); }
        RestResponse resp = new RestResponse(response, time);
        if (verifyOkStatus)
            resp.isStatus(excpecedtStatus);
        return resp;
    }
    private static String printRS(RequestSpecification rs) {
        return rs.toString();
    }
}
