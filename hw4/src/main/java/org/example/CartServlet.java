package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getProducts().isEmpty()) {
            out.println("<p>Корзина пуста</p>");
        } else {
            out.println("<h2>Корзина</h2>");
            for (Product p : cart.getProducts()) {
                out.println("<p>" + p.getName() + " — " + p.getPrice() + " руб.</p>");
            }
            out.println("<a href='order'>Сделать заказ</a>");
        }
    }
}
