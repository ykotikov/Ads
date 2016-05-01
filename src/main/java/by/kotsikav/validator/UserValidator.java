package by.kotsikav.validator;

import by.kotsikav.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yura5 on 07.04.2016.
 */
public class UserValidator implements Validator {
    @Override
    public List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        User user = (User) obj;
        if(user != null){
            String login = user.getLogin();
            String password = user.getPassword();
            String email = user.getEmail();

            
        }
        return errors;
    }
}
