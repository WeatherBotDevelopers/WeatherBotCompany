package org.example;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import com.petersamokhin.bots.sdk.clients.Group;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;

public class MessageReplyer extends TelegramWeatherBot {

    public void sendTelegramMessage(Update update) {
        Message messageTelegram = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        if (messageTelegram.hasText()) {
            try {
                sendMessage.enableMarkdown(true);
                sendMessage.setChatId(messageTelegram.getChatId().toString());
                sendMessage.setReplyToMessageId(messageTelegram.getMessageId());
                String messageToSend = Weather.getWeather(new ModelJSON(), messageTelegram.getText());
                sendMessage.setText(messageToSend);
                sendMessage(sendMessage);
            } catch (IOException | TelegramApiException e) {
                try {
                    sendMessage.setText("Такого города не существует!");
                    sendMessage(sendMessage);
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendVkMessage(Group groupVk) {
        groupVk.onSimpleTextMessage(message -> {
            try {
                String text = message.getText();
                String messageToSend = Weather.getWeather(new ModelJSON(), text);
                new com.petersamokhin.bots.sdk.objects.Message().from(groupVk).to(message.authorId()).text(messageToSend).send();
            } catch (IOException e) {
                new com.petersamokhin.bots.sdk.objects.Message().from(groupVk).to(message.authorId()).text("Такого города не существует!").send();
            }
        });
    }
}
