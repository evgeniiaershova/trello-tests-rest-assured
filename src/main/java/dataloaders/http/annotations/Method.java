package dataloaders.http.annotations;

import dataloaders.http.requests.RestMethodTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static dataloaders.http.requests.RestMethodTypes.DELETE;
import static dataloaders.http.requests.RestMethodTypes.GET;
import static dataloaders.http.requests.RestMethodTypes.POST;
import static dataloaders.http.requests.RestMethodTypes.PUT;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Method {
    String value();
    RestMethodTypes[] types() default { GET, POST, PUT, DELETE };
}
