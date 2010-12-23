package pl.touk.esb.commons.camel.binding;

import org.apache.camel.language.LanguageAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 * User: mproch
 * Date: Feb 27, 2010
 * Time: 10:02:01 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD })
@LanguageAnnotation(language = "bean", factory= CommonBindableExpressionFactory.class)                  
public @interface Bindable {
}
