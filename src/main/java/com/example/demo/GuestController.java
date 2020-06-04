package com.example.demo;

import com.example.demo.Role;
import com.example.demo.RoleRepository;
import com.example.demo.User;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/guests")
public class GuestController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/register")
    public String showRegisterationPage(Model model) {
        model.addAttribute("user", new User());
        return "guestregister";
    }

    @PostMapping("/guestProcess")
    public String processRegisterationPage(@Valid @ModelAttribute("newUser") User newUser,
                                           BindingResult result, Model model) {
        model.addAttribute("newUser", newUser);

        if(result.hasErrors()) {
            newUser.clearPassword();
            return "guestregister";
        }
        else {
            model.addAttribute("message", "User Account Created");

            newUser.setEnabled(true);
            Role role = new Role(newUser.getUsername(), "ROLE_USER");
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);

            roleRepository.save(role);
            userRepository.save((newUser));

        }
        return "redirect:/";
    }

}
