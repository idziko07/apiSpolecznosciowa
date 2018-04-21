package pl.app.spolecznosciowa.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.app.spolecznosciowa.model.User;
import pl.app.spolecznosciowa.model.UserRole;
import pl.app.spolecznosciowa.repository.UserRepository;
import pl.app.spolecznosciowa.repository.UserRoleRepository;

import java.util.List;


@Controller
public class MainController {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private String username="";

    public MainController(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/mylogin")
    public String my(){
        return "mylogin";
    }
    @PostMapping("/register")
    public String register(User user){
        user.setEnabled(true);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userRepository.save(user);
        UserRole userRole = new UserRole(user.getUsername(),"ROLE_USER");
        userRoleRepository.save(userRole);
        return "dodano";
    }

    @GetMapping("/delete")
    public String listDelete(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        model.addAttribute("userName",username);
        return "delete";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id){
        userRepository.deleteById(id);
        return "redirect:/my/";
    }
}
