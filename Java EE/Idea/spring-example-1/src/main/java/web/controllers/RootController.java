package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController {

    @RequestMapping(method = RequestMethod.GET)
    public String doSomething() {
        return "hello-world-view";
    }

    @RequestMapping(value = "/hello")
    public String doHello(){
        return "hello-view";
    }
}
