package de.hbrs.se.rabbyte;

import de.hbrs.se.rabbyte.service.ChatMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@EnableJpaRepositories
@SpringBootApplication
public class RabbyteApplication  {


	public static void main(String[] args) {
		SpringApplication.run(RabbyteApplication.class, args);
	}

	@Bean
	UnicastProcessor<ChatMessage> publisher(){
		return UnicastProcessor.create();
	}
	@Bean
	Flux<ChatMessage> messages(UnicastProcessor<ChatMessage> publisher) {
		return publisher.replay(30).autoConnect();
	}
}