

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Struct;

/**
 * Servlet implementation class find
 */
public class find extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public find() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		
		String mobile = request.getParameter("mb");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booking","root","123456");
			
		   
		    
		    PreparedStatement ps=con.prepareStatement("select*from book where mobile=?");
		    //PreparedStatement ps=con.prepareStatement("select*from record where id=?");
			//PreparedStatement ps=con.prepareStatement("select record. id,name,fathername,mothername,address,gender from record full outer join admin on record.course,rollno,submitfees,pendingfees,tottalammount order by id=?");
		     
			 
		    
		     ps.setString(1, mobile);
		     out.print("<center>");
		   out.print(" <table width=50% border=1>");
		   out.print("<caption> Customer Details:</caption>");
		    
		   ResultSet rs= ps.executeQuery();
		   ResultSetMetaData rsmd =rs.getMetaData();
		    int totalcoulmn = rsmd.getColumnCount();
		    out.print("<tr>");
		    for(int i=1; i<=totalcoulmn;i++) {
		    	out.print("<th>"+rsmd.getColumnName(i)+"</th>");
		    	
		    }

		    out.print("<tr>");
		    while(rs.next()) {
		    	out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>");
		    	
		    }
		   
            out.print("</table>");
            out.print("</center>");
		    
		} catch (Exception e) {
			
			e.printStackTrace();
		}
 
	 
		
		
		
		
		
		
		
		
		
		
		
		
		
		doGet(request, response);
	}

}
