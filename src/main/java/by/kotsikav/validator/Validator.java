package by.kotsikav.validator;

import java.util.List;

/**
 * Created by yura5 on 03.04.2016.
 */
public interface Validator {
    List<String> validate(Object obj);
}
