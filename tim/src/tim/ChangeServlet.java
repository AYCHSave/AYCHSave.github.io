package tim;

import java.io.*;
import java.lang.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class ChangeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       

       int Account = Integer.parseInt(request.getParameter("account_nu"));
       int old = Integer.parseInt(request.getParameter("old"));
       int new_k = Integer.parseInt(request.getParameter("new_k"));
       int c_new = Integer.parseInt(request.getParameter("c_new"));
     
       
       if(c_new==new_k)
       {
    	   try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           //   Connection con = DriverManager.getConnection(
             //         "jdbc:mysql://localhost:3306/bank", "root", "admin123");
   
              Connection connect = null;
			try {
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin123");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         	 Statement state = null;
			try {
				state = connect.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         	  ResultSet result = null;
			try {
				result = state.executeQuery("select * from acc_bank where acc_nu='"+Account+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
                 boolean status = false;
				try {
					status = result.next();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 
                //  ServletRequest session = null;
  				//String u_name = (String)session.setAttribute("user_name");
                     //result.getInt(reword);
                     //result.getInt(change_pin); 
                  
          		if(status){ 
          			String f_name = null;
					try {
						f_name = result.getString("f_name");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                  String l_name = null;
				try {
					l_name = result.getString("l_name");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //  String acc_nu = result.getString("acc_nu");
                 // String acc_type =result.getString("acc_type");
                 // Double Balance=result.getDouble("balance");
            
               
              	
              	   //Connection connect1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin123");
              	   PreparedStatement ps;
				try {
					ps = connect
					             .prepareStatement("update acc_bank set change_pin=? where acc_nu=?");
				
					ps.setInt(1,new_k);
	              	   ps.setInt(2,Account);
	              	   ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              	   
              	
              	   out.print("CONGRATULATION! "+f_name+ ".   "+l_name+". YOUR PIN HAS BEEN CHANGED  " );
         			RequestDispatcher rd=request.getRequestDispatcher("main.jsp");
         			rd.include(request, response);     
                 }
                 else
                 {
              	   //out.print();
            			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
            			rd.include(request, response);  
                 }
                    // result.getInt("balance");
                  //String email =result.getString("email");
          		out.close();
          		}
          		
         
   
          
       
       else{
 			out.print("Sorry, please write right pin. please try later");
 			RequestDispatcher rd=request.getRequestDispatcher("change.jsp");
 			rd.include(request, response);
 		}
           
       	 
    }
 
}
