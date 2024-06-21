package at.str.iliev.ChatApplication.controllers;

import at.str.iliev.ChatApplication.models.ChatMessage;
import at.str.iliev.ChatApplication.models.ChatUser;
import at.str.iliev.ChatApplication.services.ChatMessageService;
import at.str.iliev.ChatApplication.services.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatUserService chatUserService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message) throws Exception {
        chatUserService.saveOrUpdateUser(message.getSender());
        ChatMessage savedMessage = chatMessageService.saveMessage(message.getSender(), HtmlUtils.htmlEscape(message.getContent()));
        return new ChatMessage(savedMessage.getSender(), savedMessage.getContent(), savedMessage.getTimestamp());
    }

    @MessageMapping("/history")
    @SendTo("/topic/history")
    public List<ChatMessage> getHistory() {
        return chatMessageService.getRecentMessages();
    }

    @MessageMapping("/users")
    @SendTo("/topic/users")
    public List<ChatUser> getUsers() {
        return chatUserService.getAllUsers();
    }
}