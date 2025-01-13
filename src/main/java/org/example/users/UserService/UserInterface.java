package org.example.users.UserService;

import org.example.users.User;

import java.util.List;

public interface UserInterface {
    User create(User user);
    User get(long chatId);
    List<User> getAll();
    User update(long chatId, User user);
}
