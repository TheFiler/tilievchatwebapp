package at.str.iliev.ChatApplication.dto;

import at.str.iliev.ChatApplication.models.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    ChatUser findByUsername(String username);
}
