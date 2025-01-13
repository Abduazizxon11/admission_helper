package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonService {
    public InlineKeyboardMarkup createInlineKeyboard() {
        String[] buttonNames = new String[]{"Calculator"};
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        List<InlineKeyboardButton> currentRow = new ArrayList<>();
        for (int i = 0; i < buttonNames.length; i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonNames[i]);
            button.setCallbackData( buttonNames[i]);

            currentRow.add(button);


            if (currentRow.size() == 2 || i == buttonNames.length - 1) {
                keyboardRows.add(new ArrayList<>(currentRow));
                currentRow.clear();
            }
        }

        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    } public InlineKeyboardMarkup createInlineKeyboard2(String[] buttonNames) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        List<InlineKeyboardButton> currentRow = new ArrayList<>();
        for (int i = 0; i < buttonNames.length; i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonNames[i]);
            button.setCallbackData( buttonNames[i]);

            currentRow.add(button);


            if (currentRow.size() == 2 || i == buttonNames.length - 1) {
                keyboardRows.add(new ArrayList<>(currentRow));
                currentRow.clear();
            }
        }

        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    } public InlineKeyboardMarkup EditInlineKeyboard(String[] buttonNames) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        List<InlineKeyboardButton> currentRow = new ArrayList<>();
        for (int i = 0; i < buttonNames.length; i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonNames[i]);
            button.setCallbackData( buttonNames[i]);

            currentRow.add(button);


            if (currentRow.size() == 2 || i == buttonNames.length - 1) {
                keyboardRows.add(new ArrayList<>(currentRow));
                currentRow.clear();
            }
        }

        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    }
}
