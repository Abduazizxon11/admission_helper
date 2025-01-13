package org.example;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.example.prompts.prompts;
import org.example.users.User;
import org.example.users.UserService.UserServiceImpl.UserServiceImpl;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    public MyBot(String s) {
        super(s);
    }

    static Gson gson = new Gson();

    static HttpClient client = HttpClient.newHttpClient();
    static UserServiceImpl userService = new UserServiceImpl();
    static ButtonService buttonService = new ButtonService();


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            HttpClient client = HttpClient.newHttpClient();
            Gson gson = new Gson();
            long chatId = update.getMessage().getChatId();
            System.out.println("Kirildi");
            User user = userService.get(chatId);

            if (update.getMessage().getText().equals("/start")) {
                System.out.println(chatId);
                System.out.println("Kirildi");
                if (user == null) {
                    user = userService.create(new User(chatId, null, null, Bot_State.START, null, null, null, null, null));
                }
                user.setState(Bot_State.START);
                userService.update(chatId, user);
                send(chatId, "Xush kelibsiz!.Quyidagilardan birini tanlang", buttonService.createInlineKeyboard());
            }

            switch (user.getState()) {
                case SECOND -> {
                    Double score = Double.valueOf(text);
                    if (score >= 0 && score <= 9) {
                        send(chatId, "Qabul qilindi.Endi SAT scoringizni kiriting");
                        user.setState(Bot_State.THIRD);
                        user.setIELTS_SCORE(score);
                        userService.update(chatId, user);
                    } else {
                        send(chatId, "Ichvomang");

                    }

                }
                case THIRD -> {
                    Integer score = Integer.valueOf(text);
                    if (score >= 400 && score <= 1600) {

                        user.setState(Bot_State.FOUR);
                        user.setSAT_SCORE(score);
                        userService.update(chatId, user);
                        send(chatId, "Qabul qilindi.GPA ingizni kiriting");
                    } else {
                        send(chatId, "Ichvomang");
                    }

                }
                case FOUR -> {
                    Double GPA = Double.valueOf(text);
                    if (GPA >= 0 && GPA <= 4) {
                        user.setGPA(GPA);
                        user.setState(Bot_State.FIVE);
                        userService.update(chatId, user);
                        String[] buttonNames = new String[]{"Weighted", "Unweighted"};

                        send(chatId, "Qabul qilindi.Sinfingizni turini kiriting", buttonService.createInlineKeyboard2(buttonNames));
                    }
                }

            }

        } else if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            User user = userService.get(chatId);
            String data = update.getCallbackQuery().getData();
            if (data.equals("Calculator")) {
                editMessageCaptionAndInlineKeyboard(update, "IELTS scoringizni kiriting.Masalan 7.0 toki 6.5");
                user.setState(Bot_State.SECOND);
                userService.update(chatId, user);
            }
            switch (user.getState()) {
                case FIVE -> {
                    if (data.equals("Weighted")) {
                        user.setStatus_of_classes("Weighted");
                    }
                    if (data.equals("Unweighted")) {
                        user.setStatus_of_classes("Unweighted");
                    }
                    user.setState(Bot_State.SIX);
                    userService.update(chatId, user);
                    String[] buttons = {"US", "UK"};
                    editMessageCaptionAndInlineKeyboard(update, "Qabul qilindi. Xohlagan davlatingizni tanlang", buttons);


                }
                case SIX -> {

                    String[] buttonNames = {"Arts & Humanities", "Business & Economics", "Computer Science & Information Technology", "Engineering", "Health & Medicine", "Social Sciences", "Sciences", "Education", "Law & Legal Studies", "Communications & Media", "Agriculture & Environmental Studies", "Architecture & Design", "Hospitality & Culinary Arts", "Military & Defense", "Sports & Recreation", "Interdisciplinary Majors"};
                    user.setCountry(data);
                    user.setState(Bot_State.SEVEN);
                    userService.update(chatId,user);
                    editMessageCaptionAndInlineKeyboard(update, "Qabul qilindi. Sohangizni kiriting", buttonNames);
                }
                case SEVEN -> {
                    user.setMajor(data);
                    user.setState(Bot_State.EIGHT);
                    userService.update(chatId,user);
                    String[] buttons = {"Unnecessary","Need Based","Merit Based","Full Ride"};
                    editMessageCaptionAndInlineKeyboard(update,"Qabul qilindi.Scholarship turini tanlang.",buttons);

                }
                case EIGHT -> {
                    user.setScholarship(data);
                    user.setState(Bot_State.NINE);
                    userService.update(chatId,user);
                    editMessageCaptionAndInlineKeyboard(update,"Qabul qilindi...Biroz kutub turing");
                    Resp print = print(chatId);
                    String output = print.getOutput();
                    send(chatId,output);
                }
            }

        }


    }

    private Resp print(long chatId) {
        User user = userService.get(chatId);
        Resp resp = null;
        String jsonRequestBody = "";
        String output = "";
        System.out.println("Gone");

        jsonRequestBody = "{\"contents\":[{\"parts\":[{\"text\":\"" + "IELTS:" + user.getIELTS_SCORE() + " SAT:" + user.getSAT_SCORE() + prompts.score_rules + "GPA" + user.getGPA() + "Weighted/Unweighted classes" + user.getStatus_of_classes() +"Major"+user.getMajor()+"Country of University"+user.getCountry()+" Scholarship type"+ user.getScholarship()+"\"}]}]}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro:generateContent?key=AIzaSyA1lyz2u1IaADLJ2RMs9UCIAbZbDKcJDFI"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();
        HttpResponse<String> response = null;
        System.out.println("here");

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            ResponseData responseData = gson.fromJson(body, ResponseData.class);
            for (ResponseData.Candidate candidate : responseData.getCandidates()) {
                ResponseData.Content content = candidate.getContent();
                List<ResponseData.Part> parts = content.getParts();
                for (ResponseData.Part part : parts) {
                    output += part.getText() + " ";
                }

            }


            resp = new Resp(output);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return resp;
    }

    @SneakyThrows
    private Message send(long chatId, String text) {
        SendMessage sendmessage = new SendMessage();
        sendmessage.setChatId(chatId);
        sendmessage.setText(text);
        return execute(sendmessage);
    }

    @SneakyThrows
    private Message send(long chatId, String text, ReplyKeyboardMarkup markup) {
        SendMessage sendmessage = new SendMessage();
        sendmessage.setChatId(chatId);
        sendmessage.setText(text);
        sendmessage.setReplyMarkup(markup);
        return execute(sendmessage);
    }

    @SneakyThrows
    private Message send(long chatId, String text, InlineKeyboardMarkup markup) {
        SendMessage sendmessage = new SendMessage();
        sendmessage.setChatId(chatId);
        sendmessage.setText(text);
        sendmessage.setReplyMarkup(markup);
        return execute(sendmessage);
    }

    @SneakyThrows
    private Message send(long chatId, String text, String parseMode) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setParseMode(parseMode);
        return execute(message);
    }

    @SneakyThrows
    private Message send(long chatId, String text, InlineKeyboardMarkup markup, String parseMode) {
        SendMessage sendmessage = new SendMessage();
        sendmessage.setChatId(chatId);
        sendmessage.setText(text);
        sendmessage.setReplyMarkup(markup);
        sendmessage.setParseMode(parseMode);
        return execute(sendmessage);
    }

    public void editMessageCaptionAndInlineKeyboard(Update update, String newCaption, String[] newButtonNames) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        int messageId = update.getCallbackQuery().getMessage().getMessageId();

        InlineKeyboardMarkup newInlineKeyboard = buttonService.EditInlineKeyboard(newButtonNames);

        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(chatId);
        editMessage.setMessageId(messageId);
        editMessage.setText(newCaption);  // New caption
        editMessage.setReplyMarkup(newInlineKeyboard);  // New inline keyboard

        try {
            execute(editMessage);  // Edits both the caption and the inline keyboard
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void editMessageCaptionAndInlineKeyboard(Update update, String newCaption) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        int messageId = update.getCallbackQuery().getMessage().getMessageId();


        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(chatId);
        editMessage.setMessageId(messageId);
        editMessage.setText(newCaption);  // New caption

        try {
            execute(editMessage);  // Edits both the caption and the inline keyboard
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "t.me/admission_helper_bot";
    }
}
