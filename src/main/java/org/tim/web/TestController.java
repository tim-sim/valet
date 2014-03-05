package org.tim.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TNasibullin
 */
@Controller
public class TestController {
    @RequestMapping(value = "/home.html")
    public void home(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print("Hello world!");
    }
}
