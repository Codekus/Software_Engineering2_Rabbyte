package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.implemented.MessageDTOImpl;
import de.hbrs.se.rabbyte.entities.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
class MessageFactoryTest {

    int id = 300;
    int idReciever = 310;
    int idSender = 320;
    String messageText = "Message Text";
    String title = "Title";
    LocalDate date = LocalDate.of(1999 , 1 , 12);

    @Mock
    private MessageDTOImpl messageDTO;

    private Message message;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); //without this you will get NPE

    }

    @Test
    void dtoTest() {


        mockingDTO();

        message = MessageFactory.createMessage(messageDTO);
        assertNotNull(message);
        assertTrue(message instanceof  Message);

        assertEquals(id , message.getId());
        assertEquals(idReciever , message.getReceiver());
        assertEquals(idSender , message.getSender());
        assertEquals(messageText , message.getMessageText());
        assertEquals(title , message.getTitle());
        assertEquals(date , message.getDate());
        assertFalse( message.getRead());
    }

    public void mockingDTO() {
        when(messageDTO.getId()).thenReturn(id);
        when(messageDTO.getReceiver()).thenReturn(idReciever);
        when(messageDTO.getSender()).thenReturn(idSender);
        when(messageDTO.getMessageText()).thenReturn(messageText);
        when(messageDTO.getTitle()).thenReturn(title);
        when(messageDTO.getDate()).thenReturn(date);
    }
}