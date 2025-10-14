package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.FileWriter;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            try (FileWriter writer = new FileWriter("order.txt", true)) {
                for (Product p : cart.getProducts()) {
                    writer.write(p.getName() + " - " + p.getPrice() + "\n");
                }
                writer.write("----\n");
            }
            cart.getProducts().clear();
        }
        resp.sendRedirect("products");
    }
}
