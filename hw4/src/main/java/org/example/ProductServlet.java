package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private List<Product> products = new ArrayList<>();

    @Override
    public void init() {
        products.add(new Product("Телефон", 10000));
        products.add(new Product("Ноутбук", 30000));
        products.add(new Product("Наушники", 2000));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Список товаров</h2>");
        for (Product p : products) {
            out.println("<p>" + p.getName() + " — " + p.getPrice() +
                    " руб. <a href='add?name=" + p.getName() +
                    "&price=" + p.getPrice() + "'>Добавить в корзину</a></p>");
        }
        out.println("<a href='cart'>Перейти в корзину</a>");
    }
}
