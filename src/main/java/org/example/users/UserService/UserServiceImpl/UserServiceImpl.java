package org.example.users.UserService.UserServiceImpl;

import org.example.Bot_State;
import org.example.users.User;
import org.example.users.UserService.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserInterface {
    private List<User> users = new ArrayList<>();


    @Override
    public User create(User user) {
        if (get(user.getChatId()) == null) {
            User newUser = new User(
                    user.getChatId(), null, null, Bot_State.START, null, null,null,null,null);
            users.add(newUser);
        }
        return user;
    }

    @Override
    public User get(long chatId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getChatId() == chatId) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User update(long chatId, User user) {
        User oldUser = get(chatId);

        for (int i = 0; i < users.size(); i++) {
            if (oldUser.getChatId() == chatId) {
                oldUser.setState(user.getState());

                users.set(i, oldUser);
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (oldUser.getChatId() == chatId) {
                oldUser.setIELTS_SCORE(user.getIELTS_SCORE());

                users.set(i, oldUser);
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (oldUser.getChatId() == chatId) {
                oldUser.setSAT_SCORE(user.getSAT_SCORE());

                users.set(i, oldUser);
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (oldUser.getChatId() == chatId) {
                oldUser.setGPA(user.getGPA());
                users.set(i, oldUser);
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (oldUser.getChatId() == chatId) {
                oldUser.setStatus_of_classes(user.getStatus_of_classes());
                users.set(i, oldUser);
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (oldUser.getChatId() == chatId) {
                oldUser.setCountry(user.getCountry());
                users.set(i, oldUser);
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (oldUser.getChatId() == chatId) {
                oldUser.setMajor(user.getMajor());
                users.set(i, oldUser);
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (oldUser.getChatId() == chatId) {
                oldUser.setScholarship(user.getScholarship());
                users.set(i, oldUser);
            }
        }


        return oldUser;
    }
}
