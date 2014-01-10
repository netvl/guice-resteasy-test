package grt.guice;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;
import grt.data.AuthInfo;
import grt.filters.AuthFilter;
import grt.resources.TestResource;

/**
 * Date: 10.01.14
 * Time: 16:50
 */
public class RootModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TestResource.class);
        bind(AuthInfo.class).in(RequestScoped.class);
        bind(AuthFilter.class);
    }
}
