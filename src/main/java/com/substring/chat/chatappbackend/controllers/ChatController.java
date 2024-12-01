package com.substring.chat.chatappbackend.controllers;

import com.substring.chat.chatappbackend.entities.Message;
import com.substring.chat.chatappbackend.entities.Room;
import com.substring.chat.chatappbackend.payload.MessageRequest;
import com.substring.chat.chatappbackend.repositories.RoomRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @MessageMapping("/sendMessage/{roomId}") // This is the endpoint where the client sends a message
    @SendTo("/topic/room/{roomId}") // This is the endpoint where the server sends the message to
    public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest messageRequest) {
        // Save the message to the room
        Room room = roomRepository.findByRoomId(roomId);

        // Create a new message
        Message message = new Message();
        message.setSender(messageRequest.getSender());
        message.setContent(messageRequest.getContent());
        message.setTimestamp(LocalDateTime.now());

        // Add the message to the room
        if (room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
        }
        else{
            throw new RuntimeException("Room not found");
        }

        return message;
    }
}
