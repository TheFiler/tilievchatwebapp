package at.str.iliev.ChatApplication.services;

import at.str.iliev.ChatApplication.dto.ChatMessageRepository;
import at.str.iliev.ChatApplication.models.ChatMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final int historyLimit;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, @Value("${chat.history.limit}") int historyLimit) {
        this.chatMessageRepository = chatMessageRepository;
        this.historyLimit = historyLimit;
    }

    public ChatMessage saveMessage(String sender, String content) {
        ChatMessage message = new ChatMessage(sender, content, LocalDateTime.now());
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getRecentMessages() {
        return chatMessageRepository.findTop10ByOrderByTimestampDesc();
    }
}