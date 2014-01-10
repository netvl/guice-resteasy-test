package grt;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import grt.guice.GuiceIntegration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Date: 10.01.14
 * Time: 16:48
 */
public final class Main {
    public static void main(String[] args) throws Exception {
        Server server = createServer(9090);
        server.start();

        server.join();
    }

    private static Server createServer(int port) {
        FilterHolder guiceFilter = new FilterHolder();
        guiceFilter.setHeldClass(GuiceFilter.class);

        ServletHolder servletHolder = new ServletHolder();
        servletHolder.setHeldClass(EmptyServlet.class);

        ServletContextHandler handler = createContextHandler(guiceFilter, servletHolder);

        Server server = new Server(port);
        server.setHandler(handler);

        return server;
    }

    private static ServletContextHandler createContextHandler(FilterHolder guiceFilter, ServletHolder servletHolder) {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");

        Injector injector = GuiceIntegration.getInjector();
        handler.addEventListener(GuiceIntegration.guiceContextListenerFor(injector));
        handler.addEventListener(GuiceIntegration.resteasyContextListenerFor(injector));

        handler.addServlet(servletHolder, "/*");
        handler.addFilter(guiceFilter, "/*", EnumSet.allOf(DispatcherType.class));
        return handler;
    }
}

