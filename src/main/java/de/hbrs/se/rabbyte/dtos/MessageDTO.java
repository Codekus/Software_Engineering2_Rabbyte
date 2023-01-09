package de.hbrs.se.rabbyte.dtos;

import java.time.LocalDate;
import java.util.Date;

public interface MessageDTO {

        public int getId();
        public int getSender();
        public int getReceiver();
        public String getMessageText();
        public String getTitle();
        public LocalDate getDate();
        public boolean getRead();

}
