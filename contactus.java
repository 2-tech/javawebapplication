

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
 * Servlet implementation class contactus
 */
public class contactus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contactus() {
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
		String Your_name=request.getParameter("nm");
		String Email=request.getParameter("email");
		String Subject=request.getParameter("sub");
		String Message=request.getParameter("msg");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_us","root","123456");
			
		    String s="insert into contact values(?,?,?,?)";
		    
		    PreparedStatement st=con.prepareStatement(s);
		    
		      st.setString(1, Your_name);
		      st.setString(2, Email);
		     st.setString(3, Subject);
		     st.setString(4, Message);
		     
		    
		    int i=st.executeUpdate();
		   

		    if(i>0)
		    {
		    	 out.print("Congratulations! You have successfully contacted our faculty");
		    	RequestDispatcher rd=request.getRequestDispatcher("contact.jsp");
				rd.forward(request, response);
		    	
		    }else
		    {
		    	 out.print("Sorry for the inconvenience caused");
		    }

		    
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		doGet(request, response);
	}

}
