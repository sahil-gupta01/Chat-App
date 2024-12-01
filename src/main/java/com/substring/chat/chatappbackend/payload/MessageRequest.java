package com.substring.chat.chatappbackend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private String sender;
    private String content;
    private String roomId;
}
