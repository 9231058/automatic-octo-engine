package home.parham.loginsample.filters;

import java.io.IOException;

/**
 * Created by parham on 9/19/14.
 */
public class LogFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        System.out.println("INFO: Request is coming from : " + req.getRemoteAddr() + " : " + req.getRemotePort() );
        chain.doFilter(req, resp);
        System.out.println("INFO: Request is going");
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}
