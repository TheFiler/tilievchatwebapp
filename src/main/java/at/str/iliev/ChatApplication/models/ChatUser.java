package at.str.iliev.ChatApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private LocalDateTime lastPosted;

    public ChatUser() {}

    public ChatUser(String username, LocalDateTime lastPosted) {
        this.username = username;
        this.lastPosted = lastPosted;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLastPosted() {
        return lastPosted;
    }

    public void setLastPosted(LocalDateTime lastPosted) {
        this.lastPosted = lastPosted;
    }
}