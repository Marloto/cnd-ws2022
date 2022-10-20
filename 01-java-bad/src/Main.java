import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(8080);

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/");
        server.setHandler(ctx);

        ServletHolder serHol = ctx.addServlet(Servlet.class, "/*");
        serHol.setInitOrder(1);

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            System.out.println("Error while starting server");
            ex.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}