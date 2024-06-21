package at.str.iliev.ChatApplication.dto;

import at.str.iliev.ChatApplication.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findTop10ByOrderByTimestampDesc();
}