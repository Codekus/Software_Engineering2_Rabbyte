package de.hbrs.se.rabbyte.dtos;

import java.time.LocalDateTime;

public interface MessageDTO {

        public int getId();
        public int getSender();
        public int getReceiver();
        public String getMessageText();
        public String getTitle();
        public LocalDateTime getDate();
        public boolean getRead();

}
