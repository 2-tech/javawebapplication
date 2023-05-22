

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		   PrintWriter out = response.getWriter();
			String username= request.getParameter("un");
			String password= request.getParameter("pass");
		
			if(login.validate(username,password)) {
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
		     rd.forward(request, response);   
		    
		    
		     
		
			out.print("Sucessfully Login");
			
		}
		else
		{
			out.print("invalid username and password");
			 
		}
		
		doGet(request, response);
	}

 public static boolean validate(String username, String password) {
	  
	boolean status= false;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","123456");
		
	    String s="select * from  users where username=? and password=?";
		 
	  
		
		PreparedStatement st=con.prepareStatement(s);
	    
	 
	    st.setString(1, username);
	    st.setString(2, password);
	  
	    
	   ResultSet rs=st.executeQuery();
	   
	   status=rs.next();
	     
	}
	   
	  
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return status;
	}

}
		   
		   
