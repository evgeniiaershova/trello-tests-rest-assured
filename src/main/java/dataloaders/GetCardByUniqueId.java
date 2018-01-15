package dataloaders;

import dataloaders.PropertyLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetCardByUniqueId{

    public static String resource = "/cards/{card_id}";
    private static PropertyLoader commonData =  new PropertyLoader();

    static RequestLoggingFilter requestLogUri = new RequestLoggingFilter(LogDetail.URI);
    static RequestLoggingFilter requestLogMethod = new RequestLoggingFilter (LogDetail.PARAMS);
    static ResponseLoggingFilter responseLogBody = new ResponseLoggingFilter(LogDetail.BODY);
    static ResponseLoggingFilter responseLogUri = new ResponseLoggingFilter(LogDetail.STATUS);

    public static RequestSpecification given = given()
             .baseUri(commonData.getBaseUrl())
                     .contentType(ContentType.JSON)
            .queryParam("key", commonData.getApiKey())
            .queryParam("token", commonData.getToken());
//            .filters(requestLogUri, requestLogMethod, responseLogBody, responseLogUri);;

}
