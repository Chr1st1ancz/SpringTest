package cz.spsmb.service;

import cz.spsmb.entity.User;

public interface UserService {
    void save(User user);
    boolean isAuth(String name, String password);

}
