package id.jmlcode.sample.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jamal on 3/13/2018.
 */

public class ResultGetToken {
     @SerializedName("access_token")private String accessToken;
     @SerializedName("token_type")private String tokenType;
     @SerializedName("expires_in")private Integer expiresIn;
     @SerializedName("scope")private String scope;

    public ResultGetToken() {
        super();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
