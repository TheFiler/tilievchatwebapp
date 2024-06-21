package at.str.iliev.ChatApplication.services;

import at.str.iliev.ChatApplication.dto.ChatUserRepository;
import at.str.iliev.ChatApplication.models.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatUserService {

    @Autowired
    private ChatUserRepository chatUserRepository;

    public ChatUser saveOrUpdateUser(String username) {
        ChatUser user = chatUserRepository.findByUsername(username);
        if (user == null) {
            user = new ChatUser(username, LocalDateTime.now());
        } else {
            user.setLastPosted(LocalDateTime.now());
        }
        return chatUserRepository.save(user);
    }

    public List<ChatUser> getAllUsers() {
        return chatUserRepository.findAll();
    }
}