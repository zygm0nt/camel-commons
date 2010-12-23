package pl.touk.esb.commons.camel.binding;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.component.bean.AnnotationExpressionFactory;
import org.apache.camel.language.LanguageAnnotation;

import java.lang.annotation.Annotation;

/**
 * Created by IntelliJ IDEA.
 * User: mproch
 * Date: Feb 27, 2010
 * Time: 10:05:10 AM
 */
public class CommonBindableExpressionFactory implements AnnotationExpressionFactory {

    @SuppressWarnings("unchecked")
    public Expression createExpression(CamelContext camelContext, Annotation annotation, LanguageAnnotation languageAnnotation, final Class expressionReturnType) {
        return new CommonBindableExpression(expressionReturnType, camelContext);
    }
    


}

