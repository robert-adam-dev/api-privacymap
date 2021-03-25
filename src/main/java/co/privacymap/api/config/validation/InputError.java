package co.privacymap.api.config.validation;

import lombok.Getter;

@Getter
public class InputError {
    private String field;
    private String message;

    public InputError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
