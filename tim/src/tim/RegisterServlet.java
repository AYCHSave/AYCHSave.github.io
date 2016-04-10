package tim;

import java.io.*;
import java.lang.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
        
        String f_name = request.getParameter("f_name");
        String l_name = request.getParameter("l_name");
        String acc_nu = request.getParameter("acc_nu");
        String acc_type = request.getParameter("acc_type");
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        int balance = Integer.parseInt(request.getParameter("balance"));
        String email = request.getParameter("email");
        int change_pin = Integer.parseInt(request.getParameter("change_pin"));
        int reword = 0;
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank", "root", "admin123");
 
            PreparedStatement ps = con
                    .prepareStatement("insert into acc_bank values(?,?,?,?,?,?,?,?,?,?)");
 
            ps.setString(1,f_name);
            ps.setString(2,l_name);
            ps.setString(3,acc_nu);
            ps.setString(4,acc_type);
            ps.setString(5,user_name);
            ps.setString(6,password);
            ps.setInt(7,balance);
            ps.setString(8,email);
            ps.setInt(9,reword);
            ps.setInt(10,change_pin);
          //  ps.setString(3, e);
           // ps.setString(4, c);
 
            int i = ps.executeUpdate();
            if (i > 0)
                out.print("You are successfully registered...");
 
        } catch (Exception e2) {
            System.out.println(e2);
        }
 
        out.close();
    }
 
}
