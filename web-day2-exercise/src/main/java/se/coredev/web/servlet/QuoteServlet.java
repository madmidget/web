package se.coredev.web.servlet;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import se.coredev.web.model.Quote;
import se.coredev.web.service.QuoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.*;

@WebServlet("/quotes/*")
public final class QuoteServlet extends HttpServlet {

    @Autowired
    private QuoteService service;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = extractText(request);
        Quote quote = service.addQuote(text);

        response.setStatus(SC_CREATED);
        response.setHeader("Location", format("%s/%s", request.getRequestURL(), quote.getId()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = extractId(request);
        Quote quote = service.getQuote(id);

        if (quote == null) {
            response.setStatus(SC_NOT_FOUND);
        } else {
            writeToResponse(quote, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = extractId(request);
        String text = extractText(request);

        if (quoteExist(id)) {
            service.updateQuote(new Quote(id, text));
            response.setStatus(SC_NO_CONTENT);
        } else {
            response.setStatus(SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = extractId(request);

        if (quoteExist(id)) {
            service.deleteQuote(id);
            response.setStatus(SC_NO_CONTENT);
        } else {
            response.setStatus(SC_NOT_FOUND);
        }
    }

    /**
     * Helper method for extracting id from request path
     *
     * @param request
     * @return id or -1 if id could not be extracted
     */
    private Long extractId(HttpServletRequest request) {
        String[] parts = request.getPathInfo().split("/");
        return parts.length >= 2 ? Long.parseLong(parts[1]) : -1L;
    }

    /**
     * Writes a quote as text to response
     *
     * @param quote
     * @param response
     * @throws IOException
     */
    private void writeToResponse(Quote quote, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().println(quote);
    }

    /**
     * Extracts text from request body (input stream)
     *
     * @param request
     * @return
     * @throws IOException
     */
    private String extractText(HttpServletRequest request) throws IOException {
        return IOUtils.toString(request.getInputStream(), "UTF-8");
    }

    /**
     * Checks if quote with given id exists in service
     *
     * @param id
     * @return
     */
    private boolean quoteExist(Long id) {
        return service.getQuote(id) != null;
    }
}
