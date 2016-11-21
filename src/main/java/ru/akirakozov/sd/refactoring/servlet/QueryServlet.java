package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.helpers.DatabaseHelper;
import ru.akirakozov.sd.refactoring.helpers.HtmlHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        HtmlHelper helper = new HtmlHelper(response);

        switch (command) {
            case "max":
                DatabaseHelper.dbQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1", rs -> helper
                        .header("Product with max price: ")
                        .products(rs));
                break;
            case "min":
                DatabaseHelper.dbQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1", rs -> helper
                        .header("Product with min price: ")
                        .products(rs));
                break;
            case "sum":
                DatabaseHelper.dbQuery("SELECT SUM(price) FROM PRODUCT", rs -> {
                    helper.echo("Summary price: ");
                    if (rs.next()) {
                        helper.echo(rs.getInt(1));
                    }
                });
                break;
            case "count":
                DatabaseHelper.dbQuery("SELECT COUNT(*) FROM PRODUCT", rs -> {
                    helper.echo("Number of products: ");
                    if (rs.next()) {
                        helper.echo(rs.getInt(1));
                    }
                });
                break;
            default:
                response.getWriter().println("Unknown command: " + command);
        }

        helper.finish();
    }

}
