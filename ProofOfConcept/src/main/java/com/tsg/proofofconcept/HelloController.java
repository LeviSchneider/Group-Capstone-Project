package com.tsg.proofofconcept;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
        
    public HelloController() {
    }
    
    @RequestMapping(value="/tinymce", method=RequestMethod.GET)
    public String showEditor(Map<String, Object> model) {
        
        return "tinymce";
    }
    
    @RequestMapping(value="/render", method=RequestMethod.POST)
    public String renderHtmlOutput(HttpServletRequest req, Model model) {
        String htmlOutput = req.getParameter("htmlOutput");
        model.addAttribute("htmlOutput", htmlOutput);
        
        return "render";
    }
    
}
