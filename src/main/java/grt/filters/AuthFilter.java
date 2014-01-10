package grt.filters;

import com.google.inject.Inject;
import com.google.inject.Provider;
import grt.data.AuthInfo;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * Date: 10.01.14
 * Time: 16:54
 */
@javax.ws.rs.ext.Provider
public class AuthFilter implements ContainerRequestFilter {
    private final Provider<AuthInfo> authInfoProvider;

    @Inject
    AuthFilter(Provider<AuthInfo> authInfoProvider) {
        this.authInfoProvider = authInfoProvider;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        authInfoProvider.get().setName(requestContext.getUriInfo().getQueryParameters().getFirst("name"));
    }
}
