package http.refactored.entities;

import com.epam.commons.map.MapArray;
import http.refactored.annotations.Header;
import http.refactored.annotations.QueryParameter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestMethod<T> {
    public RequestSpecification given = given();
    public String resource = null;

    RequestLoggingFilter requestLogUri = new RequestLoggingFilter(LogDetail.URI);
    RequestLoggingFilter requestLogMethod = new RequestLoggingFilter (LogDetail.PARAMS);
    RequestLoggingFilter requestLogBody = new RequestLoggingFilter (LogDetail.BODY);
    ResponseLoggingFilter responseLogBody = new ResponseLoggingFilter(LogDetail.BODY);
    ResponseLoggingFilter responseLogUri = new ResponseLoggingFilter(LogDetail.STATUS);

    public RestMethod() {}

    public RestMethod(String url, String resource) {
        given.baseUri(url);
        given.filters(requestLogUri, requestLogMethod, requestLogBody, responseLogBody, responseLogUri);
        this.resource = resource;
    }

    public void addHeader(String name, String value) {
        given.header(name, value);
    }
    public void addHeader(Header header) {
        addHeader(header.name(), header.value());
    }

    public void addHeaders(Header... headers) {
        for(Header header : headers)
            addHeader(header);
    }
    public void setContentType(ContentType ct) {
        given.contentType(ct);
    }

    void addQueryParameters(QueryParameter... params) {
        MapArray<String,String> queryParams = new MapArray<>();
        queryParams.addAll(new MapArray<>(params,
                QueryParameter::name, QueryParameter::value));
        if (queryParams.any()) {
            given.queryParams(queryParams.toMap());
        }
    }

}
