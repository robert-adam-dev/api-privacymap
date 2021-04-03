package co.privacymap.api.model.dto;

import lombok.Getter;

@Getter
public class TokenOutput {

    private String token;
    private String authenticationType;

    public TokenOutput(String token, String authenticationType) {
        this.token = token;
        this.authenticationType = authenticationType;
    }
}
