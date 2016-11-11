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
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        DatabaseHelper.dbUpdate("INSERT INTO PRODUCT (NAME, PRICE) VALUES (\"" + name + "\"," + price + ")");
        new HtmlHelper(response).echo("OK");
    }
}
