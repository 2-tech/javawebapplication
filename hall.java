

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
 * Servlet implementation class hall
 */
public class hall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hall() {
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
		   String firstname=request.getParameter("fn");
		   String lastname=request.getParameter("ln");
		   String mobile=request.getParameter("mob");
		   String email =request.getParameter("email");
		   String checkin =request.getParameter("checkin");
		   String checkout=request.getParameter("checkout");
		   String  hall= request.getParameter("hl");
		     
		   
		   
		   
		   
		   try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/halls","root","123456");
				
			    String s="insert into hall values(?,?,?,?,?,?,?)";
			    
			    PreparedStatement st=con.prepareStatement(s);
			    
			      st.setString(1, firstname);
			      st.setString(2, lastname);
			     st.setString(3, mobile);
			     st.setString(4, email);
			     st.setString(5, checkin);
			     st.setString(6, checkout);
			     st.setString(7, hall);
			    
			    int i=st.executeUpdate();
			   

			    if(i>0)
			    {
			    	 out.print("Succesfully booking");
			    	RequestDispatcher rd=request.getRequestDispatcher("halls.jsp");
					rd.forward(request, response);
			    	
			    }else
			    {
			    	 out.print("user not booking");
			    }

			    
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
		
		
		doGet(request, response);
	}

}
