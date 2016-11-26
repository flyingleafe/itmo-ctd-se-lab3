package ru.akirakozov.sd.refactoring.test;

import junit.framework.TestCase;
import ru.akirakozov.sd.refactoring.helpers.HtmlHelper;

import java.io.StringWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HtmlHelperTest extends TestCase {

    HtmlHelper helper;
    StringWriter innerWriter;

    public void setUp() throws Exception {
        super.setUp();
        innerWriter = new StringWriter();
        helper = new HtmlHelper(new DummyHttpResponse(innerWriter));
    }

    public void testFinish() throws Exception {
        helper.echo("asdasd").echo("asdasd");
        assertEquals("", innerWriter.toString());
        helper.finish();
        assertEquals(innerWriter.toString(), "<html><body>\nasdasd\nasdasd\n</body></html>\n");
    }

    public void testEcho1() throws Exception {
        helper.echo(123123).finish();
        assertEquals(innerWriter.toString(), "<html><body>\n" +
                "123123\n" +
                "</body></html>\n");
    }

    public void testProducts() throws Exception {
        ArrayList<HashMap<String, String>> data = new ArrayList<>(
                Arrays.asList(new HashMap<>(), new HashMap<>(), new HashMap<>())
        );
        data.get(0).put("name", "vupsen");
        data.get(0).put("price", "100");
        data.get(1).put("name", "pupsen");
        data.get(1).put("price", "200");
        data.get(2).put("name", "luntik");
        data.get(2).put("price", "300");
        ResultSet dummySet = new DummyResultSet(data);

        helper.products(dummySet).finish();
        assertEquals(innerWriter.toString(), "<html><body>\n" +
                "vupsen\t100<br>\n" +
                "pupsen\t200<br>\n" +
                "luntik\t300<br>\n"+
                "</body></html>\n");
    }

    public void testHeader() throws Exception {
        helper.header("Wow!").finish();
        assertEquals(innerWriter.toString(), "<html><body>\n<h1>Wow!</h1>\n</body></html>\n");
    }
}