package org.tim.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TNasibullin
 */
@Controller
public class TestController {
    @RequestMapping(value = "/test")
    public String test(Model model) throws IOException {
        model.addAttribute("message", "It works!");
        return "test";
    }
}
