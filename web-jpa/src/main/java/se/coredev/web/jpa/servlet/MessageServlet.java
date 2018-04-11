package se.coredev.web.jpa.servlet;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import se.coredev.web.jpa.data.Message;
import se.coredev.web.jpa.repository.MessageRepository;
import se.coredev.web.jpa.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.*;

@WebServlet("/messages/*")
public class MessageServlet extends HttpServlet {

    @Autowired
    private MessageService service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = extractId(request);
        Optional<Message> message = service.getMessage(id);

        if (message.isPresent()) {
            response.getWriter().println(message.get());
        } else {
            response.setStatus(SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = IOUtils.toString(request.getInputStream(), "UTF-8");
        Message message = service.addMessage(text);

        response.setStatus(SC_CREATED);
        response.setHeader("Location", String.format("%s/%s", request.getRequestURL(), message.getId()));
    }

    private Long extractId(HttpServletRequest request) {
        String[] parts = request.getPathInfo().split("/");
        return parts.length >= 2 ? Long.parseLong(parts[1]) : -1L;
    }
}
