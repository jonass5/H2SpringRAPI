package example.test.RAPI.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoller {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
}
