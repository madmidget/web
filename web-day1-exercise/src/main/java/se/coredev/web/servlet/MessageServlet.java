package se.coredev.web.servlet;

import org.apache.commons.io.IOUtils;
import se.coredev.web.data.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.servlet.http.HttpServletResponse.*;


// http://127.0.0.1:8080/messages       - POST      201/400     C
// http://127.0.0.1:8080/messages/{id}  - GET       200/404     R
// http://127.0.0.1:8080/messages/{id}  - PUT       204/404     U
// http://127.0.0.1:8080/messages/{id}  - DELETE    204/404     D

@WebServlet(urlPatterns = "/messages/*")
public class MessageServlet extends HttpServlet {

    private static Map<Integer, Message> messages = new ConcurrentHashMap<>();
    private static AtomicInteger messageIds = new AtomicInteger(1000);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = IOUtils.toString(request.getInputStream(), "UTF-8");

        if(content.trim().isEmpty()) {
            response.setStatus(SC_BAD_REQUEST);
        } else {
            Integer id = messageIds.incrementAndGet();
            messages.put(id, new Message(id, content));
            response.setStatus(SC_CREATED);
            response.setHeader("Location", String.format("%s/%s", request.getRequestURL(), id));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = getMessage(request);

        if(message == null) {
            response.setStatus(SC_NOT_FOUND);
        } else {
            response.setContentType("text/plain");
            response.getWriter().println(message);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = getMessage(request);

        if(message == null) {
            response.setStatus(SC_NOT_FOUND);
        } else {
            messages.remove(message.getId());
            response.setStatus(SC_NO_CONTENT);
        }
    }

    private Message getMessage(HttpServletRequest request) {
        String id = request.getPathInfo().split("/")[1];
        return messages.get(Integer.parseInt(id));
    }
}

