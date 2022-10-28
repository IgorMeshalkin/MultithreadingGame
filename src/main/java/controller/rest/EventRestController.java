package controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Event;
import repository.EventRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventRestController extends HttpServlet {
    private final EventRepository eventRepository = new EventRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<Event> events = eventRepository.findAll();
        String json = objectMapper.writeValueAsString(events);
        resp.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        eventRepository.findAll().forEach(event -> eventRepository.delete(event.getId()));
    }
}
