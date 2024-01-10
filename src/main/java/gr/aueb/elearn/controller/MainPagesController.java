package gr.aueb.elearn.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPagesController {

    @GetMapping("/index")
    public String indexPage() {
        return "/index";
    }


    @GetMapping("/login")
    public String showLoginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
            boolean isLoggedInWithSession = details != null && details.getSessionId() != null;

            model.addAttribute("loginError", isLoggedInWithSession);
            return "/login";
        }

        return "redirect:/index";
    }

}