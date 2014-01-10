package grt.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import javax.servlet.ServletContextListener;

/**
 * Date: 10.01.14
 * Time: 16:49
 */
public final class GuiceIntegration {
    private GuiceIntegration() {
    }

    public static ServletContextListener guiceContextListenerFor(final Injector injector) {
        return new GuiceServletContextListener() {
            @Override
            protected Injector getInjector() {
                return injector;
            }
        };
    }

    public static ServletContextListener resteasyContextListenerFor(Injector injector) {
        return injector.getInstance(GuiceResteasyBootstrapServletContextListener.class);
    }

    public static Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                install(new RootModule());

                bind(HttpServletDispatcher.class).in(Singleton.class);

                serve("/*").with(HttpServletDispatcher.class);
            }
        });
    }
}
