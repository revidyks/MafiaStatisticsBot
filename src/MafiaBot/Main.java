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
                sendMsg(message, "", "ASSISTANCE TO DROWNING PERSONS IS IN THE HANDS OF THOSE PERSONS THEMSELVES");
            else if (message.getText().equals("/start"))
                sendMsg(message, "who","OK! Who are you?");
            else if (message.getText().equals("/player"))
                sendMsg(message, "","Name?");
            else if (message.getText().equals("/master"))
                sendMsg(message, "master","Lets begin");
            else if (message.getText().equals("/game"))
                sendMsg(message, "game","New game started");
            else if (message.getText().equals("/foul"))
                sendMsg(message, "game","Which player have foul?");
            else if (message.getText().equals("/night"))
                sendMsg(message, "game","City sleeps");
            else if (message.getText().equals("/day"))
                sendMsg(message, "game","City woke up");
            else if (message.getText().equals("/end"))
                sendMsg(message, "start","Wanna start new game?");
            //else
            //    sendMsg(message, "I don't understand you");
        }
    }

    private List<KeyboardRow> GetKeyboard(String KBType) {

        List<KeyboardRow> keyboard = new ArrayList<>();

        if (KBType == "start") {

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add("/start");
            keyboardFirstRow.add("/back");
            keyboard.add(keyboardFirstRow);

        }

        else if (KBType == "who") {

            KeyboardRow keyboardMenuRow = new KeyboardRow();
            keyboardMenuRow.add("/player");
            keyboardMenuRow.add("/master");
            keyboardMenuRow.add("/back");
            keyboard.add(keyboardMenuRow);
        }

        else if (KBType == "master") {

            KeyboardRow keyboardMenuRow = new KeyboardRow();
            keyboardMenuRow.add("/player");
            keyboardMenuRow.add("/game");
            keyboardMenuRow.add("/back");
            keyboard.add(keyboardMenuRow);

        }

        else if (KBType == "game") {

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add("1");
            keyboardFirstRow.add("2");
            keyboardFirstRow.add("3");
            keyboardFirstRow.add("4");
            keyboardFirstRow.add("5");
            keyboardFirstRow.add("6");

            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add("7");
            keyboardSecondRow.add("8");
            keyboardSecondRow.add("9");
            keyboardSecondRow.add("10");
            keyboardSecondRow.add("11");
            keyboardSecondRow.add("12");

            //KeyboardRow keyboardThirdRow = new KeyboardRow();

            KeyboardRow keyboardMenuRow = new KeyboardRow();
            keyboardMenuRow.add("/foul");
            keyboardMenuRow.add("/night");
            keyboardMenuRow.add("/day");
            keyboardMenuRow.add("/end");
            keyboardMenuRow.add("/back");

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            //keyboard.add(keyboardThirdRow);
            keyboard.add(keyboardMenuRow);

         }

        return keyboard;

    }

    private ReplyKeyboardMarkup GetReplyKeyboardMarkup(String KBType) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(GetKeyboard(KBType));

        return replyKeyboardMarkup;

    }

    private void sendMsg(Message message, String KBType, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setReplyMarkup(GetReplyKeyboardMarkup(KBType));
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

