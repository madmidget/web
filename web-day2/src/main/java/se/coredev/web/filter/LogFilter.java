package se.coredev.web.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter("/*") - Use this if execution order is not important
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
    }

    private void logRequest(HttpServletRequest request) {

        String log = new StringBuilder()
                .append("\nRequest URL:")
                .append(request.getRequestURL())
                .append("\nMethod:")
                .append(request.getMethod())
                .toString();

        System.out.println(log);
    }

    @Override
    public void destroy() {
    }
}
