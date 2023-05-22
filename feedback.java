

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
 * Servlet implementation class feedback
 */
public class feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public feedback() {
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
		   String address=request.getParameter("add"); 		  
		   String email=request.getParameter("email");	
		   String phone=request.getParameter("phn");
		   String message=request.getParameter("msg");
		  
		   try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback","root","123456");
				
			    String s="insert into feed values(?,?,?,?,?)";
			    
			    PreparedStatement st=con.prepareStatement(s);
			    
			     
			      st.setString(1, name);
			      st.setString(2, address);
			      st.setString(3, email);			
			      st.setString(4, phone);
			      st.setString(5, message);
			    
			    int i=st.executeUpdate();
			

			    if(i>0)
			    {
			    	 out.print("Succesfully registered");
			    	RequestDispatcher rd=request.getRequestDispatcher("feedback.jsp");
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
