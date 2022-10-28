package controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Clan;
import repository.ClanRepository;
import util.RestUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClanRestController extends HttpServlet {
    private final ClanRepository clanRepository = new ClanRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long clanId = RestUtil.getLongPathVariable(req);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (clanId == null) {
            List<Clan> clans = clanRepository.findAll();
            String json = objectMapper.writeValueAsString(clans);
            resp.getWriter().write(json);
        } else {
            Clan clan = clanRepository.findById(clanId);
            String json = objectMapper.writeValueAsString(clan);
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        Clan clan = objectMapper.readValue(RestUtil.readJsonFromRequestBody(req), Clan.class);
        clanRepository.save(clan);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        Clan clan = objectMapper.readValue(RestUtil.readJsonFromRequestBody(req), Clan.class);
        clanRepository.update(clan);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long clanId = RestUtil.getLongPathVariable(req);
        clanRepository.delete(clanId);
    }
}
