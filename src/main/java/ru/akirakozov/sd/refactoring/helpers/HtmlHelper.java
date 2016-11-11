package ru.akirakozov.sd.refactoring.helpers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author flyingleafe
 */
public class HtmlHelper {

    private HttpServletResponse response;

    public HtmlHelper(HttpServletResponse rsp) throws IOException {
        response = rsp;
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        echo("<html><body>");
    }

    public void finish() throws IOException {
        echo("</body></html>");
    }

    public HtmlHelper echo(String body) throws IOException {
        response.getWriter().println(body);
        return this;
    }

    public HtmlHelper echo(int val) throws IOException {
        return echo(Integer.toString(val));
    }

    public HtmlHelper products(ResultSet rs) throws IOException, SQLException {
        while (rs.next()) {
            String name = rs.getString("name");
            int price = rs.getInt("price");
            echo(name + "\t" + price + "</br>");
        }
        return this;
    }

    public HtmlHelper header(String header) throws IOException {
        return echo("<h1>" + header + "</h1>");
    }
}
