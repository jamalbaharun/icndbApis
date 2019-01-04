package id.jmlcode.sample.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jamal on 3/13/2018.
 */

public class ResultSignature {
    @SerializedName("AccessToken")private String accessToken;
    @SerializedName("HTTPMethod")private String hTTPMethod;
    @SerializedName("APISecret")private String aPISecret;
    @SerializedName("Timestamp")private String timestamp;
    @SerializedName("URI")private String uRI;
    @SerializedName("RequestPayload")private String requestPayload;
    @SerializedName("HashedRequestPayload")private String hashedRequestPayload;
    @SerializedName("SortedURI")private String sortedURI;
    @SerializedName("CalculatedHMAC")private String calculatedHMAC;

    public ResultSignature() {
        super();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String gethTTPMethod() {
        return hTTPMethod;
    }

    public void sethTTPMethod(String hTTPMethod) {
        this.hTTPMethod = hTTPMethod;
    }

    public String getaPISecret() {
        return aPISecret;
    }

    public void setaPISecret(String aPISecret) {
        this.aPISecret = aPISecret;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getuRI() {
        return uRI;
    }

    public void setuRI(String uRI) {
        this.uRI = uRI;
    }

    public String getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }

    public String getHashedRequestPayload() {
        return hashedRequestPayload;
    }

    public void setHashedRequestPayload(String hashedRequestPayload) {
        this.hashedRequestPayload = hashedRequestPayload;
    }

    public String getSortedURI() {
        return sortedURI;
    }

    public void setSortedURI(String sortedURI) {
        this.sortedURI = sortedURI;
    }

    public String getCalculatedHMAC() {
        return calculatedHMAC;
    }

    public void setCalculatedHMAC(String calculatedHMAC) {
        this.calculatedHMAC = calculatedHMAC;
    }
}
