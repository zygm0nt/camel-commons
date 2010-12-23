package pl.touk.esb.commons.camel.binding;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.component.bean.BeanInfo;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Created by IntelliJ IDEA.
 * User: mproch
 * Date: Feb 27, 2010
 * Time: 10:20:23 AM
 */
public class CommonBindableExpression<T> implements Expression {

    private Class<T> objectToReturn;

	private BeanInfo beanInfo;

    public <Y> Y evaluate(Exchange exchange, Class<Y> classToReturn) {
		T objectToReturn = instantiate(exchange);
		setProperties(objectToReturn, exchange);
		return (Y) objectToReturn;
    }

    public CommonBindableExpression(Class<T> objectToReturn, CamelContext ctx) {
        this.objectToReturn = objectToReturn;
		beanInfo = new BeanInfo(ctx, objectToReturn);
    }

	private void setProperties(T obj, Exchange exchange) {
        try {
            for (PropertyDescriptor desc : PropertyUtils.getPropertyDescriptors(obj)) {
				setOneProperty(obj, desc.getWriteMethod(), exchange);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

	private void setOneProperty(T obj, Method writeMethod, Exchange exchange) throws Throwable {
        if (writeMethod != null && writeMethod.getAnnotation(Bindable.class) != null) {
			beanInfo.createInvocation(writeMethod, obj, exchange).proceed(null, null);
        }
    }

    //TODO: konstruktory
	private T instantiate(Exchange exchange) {
        try {
          Method factoryMethod = findFactoryMethod();
          if (factoryMethod != null) {
				return (T) beanInfo.createInvocation(factoryMethod, null, exchange).proceed(null, null);
          }
          return objectToReturn.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Method findFactoryMethod() {
        for (Method m : objectToReturn.getMethods()) {
            if (m.getAnnotation(FactoryMethod.class) != null && objectToReturn.isAssignableFrom(m.getReturnType())
                    && Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers())) {
                return m;
            }
        }
        return null;
    }
}
