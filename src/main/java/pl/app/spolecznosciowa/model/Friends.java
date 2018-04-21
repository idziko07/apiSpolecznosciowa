package pl.app.spolecznosciowa.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // private Long friend;
    private boolean accept;
    @ManyToOne
    private User userFriend;
    @ManyToOne
    private User user;

    public Friends() {
    }

    public Friends(boolean accept, User userFriend, User user) {
        this.accept = accept;
        this.userFriend = userFriend;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(User userFriend) {
        this.userFriend = userFriend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getFriend() {
//        return friend;
//    }
//
//    public void setFriend(Long friend) {
//        this.friend = friend;
//    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
