import annotations.Header;
import annotations.QueryParameter;
import com.epam.commons.PrintUtils;
import com.epam.commons.linqinterfaces.JActionT;
import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;

import com.google.gson.Gson;
import dataloaders.PropertyLoader;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static java.lang.System.currentTimeMillis;


/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestMethod<T> {
    public static String resource = null;
    public static String baseUri = null;
    private static PropertyLoader commonData =  new PropertyLoader();

    static RequestLoggingFilter requestLogUri = new RequestLoggingFilter(LogDetail.URI);
    static RequestLoggingFilter requestLogMethod = new RequestLoggingFilter (LogDetail.PARAMS);
    static ResponseLoggingFilter responseLogBody = new ResponseLoggingFilter(LogDetail.BODY);
    static ResponseLoggingFilter responseLogUri = new ResponseLoggingFilter(LogDetail.STATUS);

    public static RequestSpecification given = given()
            .baseUri(baseUri)
            .contentType(ContentType.JSON)
            .queryParam("key", commonData.getApiKey())
            .queryParam("token", commonData.getToken());

    public RestMethod(String baseUri, String resource) {
        this.baseUri = baseUri;
        this.resource = resource;
    }

//    public RequestSpecification spec = given();


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
    public void setContentType(ContentType contentType) {
        given.contentType(contentType);
    }

    public void addQueryParameters(QueryParameter parameter) {
        given.queryParams(parameter.name(), parameter.value());
    }

    public RequestSpecification getSpec() {
        if (data == null)
            return spec;
        if (data.pathParams.any() && data.url.contains("{"))
            for (Pair<String, String> param : data.pathParams)
                data.url = data.url.replaceAll("\\{" + param.key + "}", param.value);
        spec.contentType(data.contentType);
        spec.baseUri(data.url);
        if (data.queryParams.any()) {
            spec.queryParams(data.queryParams.toMap());
            data.url += "?" + PrintUtils.print(data.queryParams.toMap(), "&", "{0}={1}");
        }
        if (data.body != null)
            spec.body(data.body);
        if (data.headers.any())
            spec.headers(data.headers.toMap());
        return spec;
    }



}
