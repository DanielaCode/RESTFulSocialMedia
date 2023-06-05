package com.danCode.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDaoService {
    private static List<User> users =new ArrayList<>();
    private static int usersCount = 0;
    static {
        users.add(new User(++usersCount,"Daniela",LocalDate.now().minusYears(27)));
        users.add(new User(++usersCount,"Valeria",LocalDate.now().minusYears(21)));
        users.add(new User(++usersCount,"Sofia",LocalDate.now().minusYears(15)));
    }

    public List<User>findALL(){
        return users;
    }

    public User findById(int id) {
        return users.stream()
                    .filter(user -> user.getId()==id)
                    .findFirst()
                    //.get()this one is ok but when Id does not exist it return an incorrect status code 500
                    .orElse(null);//this is better approach but returns 200 it is not good see the resource
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
