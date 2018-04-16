package se.coredev.web.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

@Component
@Order(1)
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logRequest((HttpServletRequest) request);
        chain.doFilter(request, response);
        logResponse((HttpServletResponse) response);
    }

    private void logRequest(HttpServletRequest request) {

        String log = new StringBuilder()
                .append("\nRequest URL:")
                .append(request.getRequestURL())
                .append("\nMethod:")
                .append(request.getMethod())
                .append("\nTime:")
                .append(Instant.now())
                .toString();

        System.out.println(log);
    }

    private void logResponse(HttpServletResponse response) {
        System.out.println("Response code:" + response.getStatus());
    }

    @Override
    public void destroy() {
    }
}
