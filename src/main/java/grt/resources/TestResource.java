package grt.resources;

import com.google.inject.Inject;
import grt.data.AuthInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Date: 10.01.14
 * Time: 16:51
 */
@Path("/test")
public class TestResource {
    private final AuthInfo authInfo;

    @Inject
    TestResource(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String authName() {
        return authInfo.getName();
    }
}
