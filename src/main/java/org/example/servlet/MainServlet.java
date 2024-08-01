
package org.example.servlet;


import org.example.controller.PostController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private final String GET = "GET";
    private final String POST = "POST";
    private final String DELETE = "DELETE";
    private final String apiPostReq = "/api/posts";
    private final String idParam = "/\\d+";


    private PostController controller;

    @Override
    public void init() {
        final var context = new AnnotationConfigApplicationContext("org.example");
        controller = context.getBean(PostController.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var path = req.getRequestURI();
        final var method = req.getMethod();
        if (method.equals(GET) && path.equals(apiPostReq)) {
            controller.all(resp);
            return;
        }
        if (method.equals(GET) && path.matches(apiPostReq + idParam)) {
            final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
            controller.getById(id, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var path = req.getRequestURI();
        final var method = req.getMethod();
        if (method.equals(POST) && path.equals(apiPostReq)) {
            controller.save(req.getReader(), resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        final var path = req.getRequestURI();
        final var method = req.getMethod();
        if (method.equals(DELETE) && path.matches(apiPostReq + idParam)) {
            final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
            System.out.println(id);
            controller.removeById(id, resp);

        }
    }
}

