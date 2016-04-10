package tim;

import java.io.*;
import java.lang.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class DthServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       

       int Account = Integer.parseInt(request.getParameter("acc_nu"));
       int atm= Integer.parseInt(request.getParameter("atm"));
       String dth = request.getParameter("dth");
       Double balance = Double.parseDouble(request.getParameter("Amount"));
        //String password = request.getParameter("password");
     
        try {
            Class.forName("com.mysql.jdbc.Driver");
         //   Connection con = DriverManager.getConnection(
           //         "jdbc:mysql://localhost:3306/bank", "root", "admin123");
 
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin123");
       	 Statement state =connect.createStatement();
       	  ResultSet result = state.executeQuery("select * from acc_bank where acc_nu='"+Account+"'and change_pin='"+atm+"'");
       
               boolean status =result.next();
               
              //  ServletRequest session = null;
				//String u_name = (String)session.setAttribute("user_name");
                   //result.getInt(reword);
                   //result.getInt(change_pin); 
                
        		if(status){ String f_name = result.getString("f_name");
                String l_name = result.getString("l_name");
              //  String acc_nu = result.getString("acc_nu");
                String acc_type =result.getString("acc_type");
                Double Balance=result.getDouble("balance");
          
               if(Balance>=balance)
               {
            	   double k=balance;
            	   k=Balance-balance;
            	   //Connection connect1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin123");
            	   PreparedStatement ps = connect
                           .prepareStatement("update acc_bank set balance=? where acc_nu=?");
            	   ps.setDouble(1,k);
            	   ps.setInt(2,Account);
            	   ps.executeUpdate();
            	   PreparedStatement ps1 = connect
                           .prepareStatement("update acc_bank set reword=reword+1 where acc_nu=?");
            	   ps1.setInt(1, Account);
            	   ps1.executeUpdate();
            	 /*  ResultSet result1 = state.executeQuery("select * from acc_bank where acc_nu='"+Account2+"'");
            	   boolean status1 =result1.next();
            	   if(status1)
            	   {
            		   Double Balance1=result.getDouble("balance");
            		   Balance1=Balance1+balance;
            		   PreparedStatement ps2 = connect
                               .prepareStatement("update acc_bank where acc_nu='"+Account2+"' set balance=balance+'"+Balance1+"'");
            		   ps2.executeUpdate();
            	   }*/
            	
            	   
            	   out.print("WELCOME! "+f_name+ " "+l_name+"  "
       					+ "Your Balance   is"+k+".  your service amount increase by YOUR COMPANY BY"+balance);
       			RequestDispatcher rd=request.getRequestDispatcher("main.jsp");
       			rd.include(request, response);     
               }
               else
               {
            	   out.print("WELCOME! "+f_name+ " "+l_name+"  "
          					+ "Your Balance   is NOT sufficient to send");
          			RequestDispatcher rd=request.getRequestDispatcher("main.jsp");
          			rd.include(request, response);  
               }
                  // result.getInt("balance");
                //String email =result.getString("email");
        			
        		}
        		else{
        			out.print("Sorry,Account NUmber or atm pin is failed. please try later");
        			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
        			rd.include(request, response);
        		}
        } catch (Exception e2) {
            System.out.println(e2);
        }
 
        out.close();
    }
 
}
