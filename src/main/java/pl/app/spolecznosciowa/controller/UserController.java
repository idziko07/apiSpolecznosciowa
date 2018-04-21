package pl.app.spolecznosciowa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.app.spolecznosciowa.model.Friends;
import pl.app.spolecznosciowa.model.User;
import pl.app.spolecznosciowa.repository.FriendsRepository;
import pl.app.spolecznosciowa.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/my")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private FriendsRepository friendsRepository;
    private String error="";
    private String succes = "";
    private String username ="";


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @GetMapping("/")
    public String showInfoUser(Principal principal, Model model){
        if(!(error.isEmpty())){
            model.addAttribute("error",error);
            error = "";
        }
        if(!(succes.isEmpty())){
            model.addAttribute("succes",succes);
            succes = "";
        }
        Optional<User> userOptional = userRepository.findAllByUsername(principal.getName());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            username = user.getUsername();
            model.addAttribute("userName",username);
        }

        return "my";
    }

    @GetMapping("/update")
    public String userUpdate(@RequestParam String username, Model model){
        Optional<User> userOptional = userRepository.findAllByUsername(username);
        model.addAttribute("userName",this.username);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "userUpdate";
        }else {
            error = "Wystapił bład";
            return "redirect:/my/";
        }
    }

    @PostMapping("/updateSave")
    public String save(User user){
        userRepository.save(user);
        succes="Zaktuliazowano twoje dane";
        return "redirect:/my/";
    }

    @GetMapping("/searchFrends")
    public String searchFrends(@RequestParam String name, @RequestParam(required = false ) String lastName, Model model){
        List<User> users;
        if(!(lastName.isEmpty())){
            users = userRepository.findByFirstNameAndLastName(name, lastName);
        }else{
            users = userRepository.findByFirstName(name);
        }
        model.addAttribute("users", users);
        model.addAttribute("userName", username);
        return "search";
    }

    @GetMapping("/invitation")
    public String invitationAdd(@RequestParam Long id){
        Optional<User> userInvitationSendOptional = userRepository.findById(id);
        Optional<User> userOptional = userRepository.findAllByUsername(username);
        if (userInvitationSendOptional.isPresent() && userOptional.isPresent()){
            User friend = userInvitationSendOptional.get();
            User user = userOptional.get();
            Friends addFriend = new Friends(false,friend, user);
            friendsRepository.save(addFriend);

            succes = "Wysłano zaproszenie";
            return "redirect:/my/";
        }else{
            error="Bład";
            return "redirect:/my/";
        }

    }
    @GetMapping("/friendSend")
    public String friendSend(@RequestParam String username,Model model){
        Optional<User> userOptional = userRepository.findAllByUsername(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Friends> friendsList = user.getFriendsList();
            model.addAttribute("friends",friendsList);
            model.addAttribute("userName",username);
            return "friendSend";
        }else{
          error="brak wysłanych zaproszeń";
          return "redirect:/my/";
        }
    }

    @GetMapping("/invitationDelete")
    public String invitationDelete(@RequestParam Long id){
        friendsRepository.deleteById(id);
        succes="Usunieto zaproszenie";
        return "redirect:/my/";
    }

    @GetMapping("/showInvitation")
    public String showInvitation(@RequestParam String username, Model model){
        Optional<User> userOptional = userRepository.findAllByUsername(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Friends> friendsList = friendsRepository.findByUserFriend_Id(user.getId());
            model.addAttribute("friends", friendsList);
            model.addAttribute("userName",this.username);
            return "showInvitation";
        }else{
            error= "Wystapił bład";
            return "redirect:/my/";
        }
    }

    @GetMapping("/invitationAccept")
    public String invitationAccept(@RequestParam Long id){
        Optional<Friends> friendsRepositoryById = friendsRepository.findById(id);
        if (friendsRepositoryById.isPresent()) {
            Friends friendsId = friendsRepositoryById.get();

            Optional<User> userOptional = userRepository.findById(friendsId.getUserFriend().getId());
            Optional<User> friendOptional = userRepository.findById(friendsId.getUser().getId());
            if (userOptional.isPresent() && friendOptional.isPresent()) {
                User user = userOptional.get();
                User friend = friendOptional.get();
                user.getFriends().add(friend);
                userRepository.save(user);
                succes = "Dodano do znajomych";
                friendsRepository.deleteById(id);
                return "redirect:/my/";
            } else {
                error = "bład";
                return "redirect:/my/";
            }
        }else {
            error="bład";
            return "redirect:/my/";

        }
    }

    @GetMapping("/listFriends")
    public String listFriends(@RequestParam String username, Model model){
        Optional<User> userOptional = userRepository.findAllByUsername(username);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getFriends().size() == 0){
                error="Brak znajomych";
                return "redirect:/my/";
            }else {
                List<User> friends = user.getFriends();
                model.addAttribute("friends",friends);
                model.addAttribute("userName",this.username);
                return "listFriends";
            }
        }else{
            error="bład";
            return "redirect:/my/";
        }


    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSucces() {
        return succes;
    }

    public void setSucces(String succes) {
        this.succes = succes;
    }
}
