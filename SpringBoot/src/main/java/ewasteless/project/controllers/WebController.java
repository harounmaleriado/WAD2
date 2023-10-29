package ewasteless.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/hello")
    public String testPage() {
        return "hello"; // Note: This would return the hello.html page if using templates
    }

}



