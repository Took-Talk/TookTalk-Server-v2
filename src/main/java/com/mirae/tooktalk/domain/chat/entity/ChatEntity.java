package com.mirae.tooktalk.domain.chat.entity;

import com.mirae.tooktalk.domain.chat.dto.ChatDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private ChatDTO.MessageType type;
    private String message;

    private String roomId;
    private String sender;
}
