package vn.midatri.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppUtils {

    public static ResponseEntity<?> mapError(BindingResult bindingResult){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            errors.put(fieldError.getField() , fieldError.getDefaultMessage());
        }
        System.out.println(errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
