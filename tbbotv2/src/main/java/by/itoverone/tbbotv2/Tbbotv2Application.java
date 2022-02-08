package by.itoverone.tbbotv2;

import by.itoverone.tbbotv2.botData.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class Tbbotv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Tbbotv2Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired TelegramBotsApi telegramBotsApi, @Autowired Bot bot) {
		return args -> {
			telegramBotsApi.registerBot(bot);
		};
	}

	@Bean
	public TelegramBotsApi telegramBotsApi(Bot bot) throws TelegramApiException {
		return new TelegramBotsApi(DefaultBotSession.class);
	}

}
