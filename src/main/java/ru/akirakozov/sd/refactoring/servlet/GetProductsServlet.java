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
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DatabaseHelper.dbQuery("SELECT * FROM PRODUCT", rs -> new HtmlHelper(response).products(rs).finish());
    }
}
