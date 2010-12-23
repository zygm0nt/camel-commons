package pl.touk.esb.commons.camel.binding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by IntelliJ IDEA.
 * User: mproch
 * Date: Feb 27, 2010
 * Time: 7:41:39 PM
 */
public class CommonBindableExpressionTest {

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private DefaultCamelContext ctx;

    private CommonBindableExpression<CommonBindableExpressionTest> expr;

    @Before
    public void before() {
        ctx = new DefaultCamelContext();
        expr = new CommonBindableExpression<CommonBindableExpressionTest>(CommonBindableExpressionTest.class, ctx);
    }

    @Test
    public void testFields() throws Exception {
        Exchange ex = new DefaultExchange(ctx);
        ex.getIn().setHeader("h1", "a");
        ex.getIn().setHeader("h2", "b");
        ex.getIn().setHeader("h4", "c");
		CommonBindableExpressionTest ret = expr.evaluate(ex, null);
        assertEquals("a", ret.field1);
        assertEquals("b", ret.field2);
        assertEquals("c", ret.field4);
        assertNull(ret.field3);
   }


    @Bindable
	public void setField1(@Header(value = "h1") String field1) {
        this.field1 = field1;
    }

    @Bindable
	public void setField2(@Header(value = "h2") String field2) {
        this.field2 = field2;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    @FactoryMethod
	public static CommonBindableExpressionTest makeTest(@Header(value = "h4") String h4) {
        CommonBindableExpressionTest t = new CommonBindableExpressionTest();
        t.field4 = h4;
        return t;
    }
}
