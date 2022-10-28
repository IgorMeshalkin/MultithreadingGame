package controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Clan;
import model.User;
import repository.ClanRepository;
import repository.UserRepository;
import util.RestUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserRestController extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();
    private final ClanRepository clanRepository = new ClanRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long userId = RestUtil.getLongPathVariable(req);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (userId == null) {
            List<User> users = userRepository.findAll();
            String json = objectMapper.writeValueAsString(users);
            resp.getWriter().write(json);
        } else {
            User user = userRepository.findById(userId);
            String json = objectMapper.writeValueAsString(user);
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long clanId = RestUtil.getLongPathVariable(req);
        Clan clan = clanRepository.findById(clanId);

        req.setCharacterEncoding("UTF-8");

        User user = objectMapper.readValue(RestUtil.readJsonFromRequestBody(req), User.class);
        user.setClan(clan);
        userRepository.save(user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long clanId = RestUtil.getLongPathVariable(req);

        req.setCharacterEncoding("UTF-8");

        User user = objectMapper.readValue(RestUtil.readJsonFromRequestBody(req), User.class);
        if (clanId != null) {
            Clan clan = clanRepository.findById(clanId);
            user.setClan(clan);
        }
        userRepository.update(user);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long userId = RestUtil.getLongPathVariable(req);
        userRepository.delete(userId);
    }
}

