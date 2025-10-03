import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RegisterServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Форма регистрации</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Форма регистрации</h1>");
        
        String error = (String) request.getAttribute("error");
        if (error != null) {
            out.println("<p style='color:red'>" + error + "</p>");
        }
        
        out.println("<form method='post'>");
        out.println("Логин: <input type='text' name='login'><br>");
        out.println("Email: <input type='text' name='email'><br>");
        out.println("Пароль: <input type='password' name='password'><br>");
        out.println("Сообщение: <textarea name='message'></textarea><br>");
        out.println("<input type='submit' value='Отправить'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message = request.getParameter("message");
        
        if (login == null || login.isEmpty() || 
            email == null || email.isEmpty() || 
            password == null || password.isEmpty() || 
            message == null || message.isEmpty()) {
            
            request.setAttribute("error", "Все поля должны быть заполнены");
            doGet(request, response);
            return;
        }
        
        if (!email.contains("@")) {
            request.setAttribute("error", "Email должен содержать @");
            doGet(request, response);
            return;
        }
        
        String data = "Логин: " + login + " | Email: " + email + " | Пароль: " + password + " | Сообщение: " + message + "\n";
        
        String path = getServletContext().getRealPath("/data.txt");
        FileWriter writer = new FileWriter(path, true);
        writer.write(data);
        writer.close();
        
        response.sendRedirect("thankyou.html");
    }
}