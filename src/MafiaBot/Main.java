package MafiaBot;

/**
 * Created by skydiver on 11.12.16.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public class Main extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Main());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "MafiaStatisticsBot";
    }

    @Override
    public String getBotToken() {
        return "271130106:AAHEX0WNOOCU2gW_zy_tvLVIMkYA6bUQQJU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help"))
                sendMsg(message, "Hello, I'm Mafia Bot");
            else if (message.getText().equals("/start"))
                sendMsg(message, "Lets begin");
            else
                sendMsg(message, "I don't understand you");
        }
    }

    private List<KeyboardRow> GetKeyboard(String KBType) {

        List<KeyboardRow> keyboard = new ArrayList<>();

        if (KBType == "main") {

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add("1");
            keyboardFirstRow.add("2");
            keyboardFirstRow.add("3");
            keyboardFirstRow.add("4");

            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add("5");
            keyboardSecondRow.add("6");
            keyboardSecondRow.add("7");
            keyboardSecondRow.add("8");

            KeyboardRow keyboardThirdRow = new KeyboardRow();
            keyboardThirdRow.add("9");
            keyboardThirdRow.add("10");
            keyboardThirdRow.add("11");
            keyboardThirdRow.add("12");

            KeyboardRow keyboardfourthRow = new KeyboardRow();
            keyboardfourthRow.add("Back");
            keyboardfourthRow.add("Fouls");

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            keyboard.add(keyboardThirdRow);
            keyboard.add(keyboardfourthRow);

        }

        return keyboard;

    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //replyKeyboardMarkup.setOneTimeKeyboad(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        replyKeyboardMarkup.setKeyboard(GetKeyboard("main"));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

