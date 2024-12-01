package com.substring.chat.chatappbackend.repositories;

import com.substring.chat.chatappbackend.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
    // Find a room by its id
    Room findByRoomId(String roomId);

}
