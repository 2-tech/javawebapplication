

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

/**
 * Servlet implementation class register
 */
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		PrintWriter out=response.getWriter();
		 
	
		   String name=request.getParameter("nm");
		   String age=request.getParameter("age");
		   String contact=request.getParameter("no");
		   String email=request.getParameter("email");
		   String address=request.getParameter("add");
		   String department=request.getParameter("dept");
		  
		   try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/staffdetails","root","123456");
				
			    String s="insert into staff values(?,?,?,?,?,?)";
			    
			    PreparedStatement st=con.prepareStatement(s);
			    
			     
			      st.setString(1, name);
			      st.setString(2, age);
			      st.setString(3, contact);
			      st.setString(4, email);
			      st.setString(5, address);
			      st.setString(6, department);
			     
			    
			    int i=st.executeUpdate();
			

			    if(i>0)
			    {
			    	 out.print("Succesfully registered");
			    	RequestDispatcher rd=request.getRequestDispatcher("staffstatus.jsp");
					rd.forward(request, response);
			    	
			    }else
			    {
			    	 out.print("user not register");
			    }

			    
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
			


		doGet(request, response);
	}

}
