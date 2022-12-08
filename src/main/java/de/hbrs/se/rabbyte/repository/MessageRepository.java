package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.MessageDTO;
import de.hbrs.se.rabbyte.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageRepository extends JpaRepository<Message, Integer> {

    MessageDTO findMessageById(int id);

    List<MessageDTO> findMessagesByRecipient(int recipient);
}
