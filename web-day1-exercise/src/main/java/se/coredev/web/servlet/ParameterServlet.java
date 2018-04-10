package se.coredev.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.*;
import static javax.servlet.http.HttpServletResponse.*;

@WebServlet("/parameters")
public class ParameterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder result = new StringBuilder();
        Map<String, String[]> parameters = request.getParameterMap();

        if(parameters.isEmpty()) {
            response.setStatus(SC_NOT_FOUND);
        } else {

            for(Entry<String, String[]> parameter : parameters.entrySet()) {
                result.append("Parameter name:").append(parameter.getKey()).append("\n");
                String values = Arrays.stream(parameter.getValue()).collect(Collectors.joining(", "));
                result.append("Parameter value:").append(values).append("\n");
            }

            response.getWriter().println(result);
            response.setContentType("text/plain");
        }
    }
}
