package ca.project.application.configurations;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@Controller
public class SPARedirectionConfigurationController implements ErrorController {
	
	private static final String errorPath = "/error";

    @RequestMapping(value = errorPath)
    @ResponseStatus(HttpStatus.OK)
    public String error() {
        return "forward:/index.html";
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
