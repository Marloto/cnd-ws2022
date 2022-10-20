import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        PrintWriter out = response.getWriter();
        try {
            switch (pathInfo) {
                case "/list":
                    Connection con = DatabaseConnection.initializeDatabase();
                    PreparedStatement st = con.prepareStatement("select id, name from employees");

                    // Execute the insert command using executeUpdate()
                    // to make changes in database
                    ResultSet resultSet = st.executeQuery();
                    String result = "";
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        result += "<li>" + id + ": " + name + "</li>";
                    }

                    // Close all the connections
                    st.close();
                    con.close();
                    out.println("<html><body><ul>" + result + "</ul><a href=\"/form\">Add new</a></body></html>");
                    break;
                case "/form":
                    out.println("<html><body><form action=\"insert\" method=\"post\"><input name=\"id\"><input name=\"name\"><button>Save</button></form><a href=\"/list\">Back</a></body></html>");
                    break;
                case "/hello":
                    out.println("<html><body><b>Hello, World!</b></body></html>");
                    break;
                default:
                    response.setStatus(404);
                    out.println("<html><body><b>Not Found!</b></body></html>");
                    break;
            }
        } catch (Exception e) {
            response.setStatus(500);
            out.println("<html><body><b>Internal Server Error!</b></body></html>");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        PrintWriter out = response.getWriter();
        try {
            switch (pathInfo) {
                case "/insert":
                    Connection con = DatabaseConnection.initializeDatabase();
                    PreparedStatement st = con.prepareStatement("insert into employees values(?, ?)");
                    st.setInt(1, Integer.valueOf(request.getParameter("id")));
                    st.setString(2, request.getParameter("name"));

                    // Execute the insert command using executeUpdate()
                    // to make changes in database
                    st.executeUpdate();

                    // Close all the connections
                    st.close();
                    con.close();
                    out.println("<html><body><b>Element insert!</b><a href=\\\"/list\\\">Back to List</a></body></html>");
                    break;
                default:
                    response.setStatus(404);
                    out.println("<html><body><b>Not Found!</b></body></html>");
                    break;
            }
        } catch (Exception e) {
            response.setStatus(500);
            out.println("<html><body><b>Internal Server Error!</b></body></html>");
            e.printStackTrace();
        }
    }
}
