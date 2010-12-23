package pl.touk.esb.commons.camel.binding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 * User: mproch
 * Date: Feb 28, 2010
 * Time: 4:52:17 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD })
public @interface FactoryMethod {
    
}
