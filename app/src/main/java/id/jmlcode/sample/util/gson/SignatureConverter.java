package id.jmlcode.sample.util.gson;

import id.jmlcode.sample.model.bean.ResultSignature;

/**
 * Created by Jamal on 3/13/2018.
 */

public class SignatureConverter {
    public static ResultSignature signatureConverter(String s){
        ResultSignature resultSignature = new ResultSignature();
        String [] splitHead = s.split(",\\r?\\n");
        for(int i = 0 ; i < splitHead.length ; i++){
            String [] splitChild = splitHead[i].split(": ");
            if(splitChild[0].equals("AccessToken")){
                resultSignature.setAccessToken(splitChild[1]);
            }else if(splitChild[0].equals("HTTPMethod")){
                resultSignature.sethTTPMethod(splitChild[1]);
            }else if(splitChild[0].equals("APISecret")){
                resultSignature.setaPISecret(splitChild[1]);
            }else if(splitChild[0].equals("Timestamp")){
                resultSignature.setTimestamp(splitChild[1]);
            }else if(splitChild[0].equals("URI")){
                resultSignature.setuRI(splitChild[1]);
            //}else if(splitChild[0].equals("RequestPayload")){
            //    resultSignature.setRequestPayload(splitChild[1]);
            }else if(splitChild[0].equals("HashedRequestPayload")){
                resultSignature.setHashedRequestPayload(splitChild[1]);
            }else if(splitChild[0].equals("SortedURI")){
                resultSignature.setSortedURI(splitChild[1]);
            }else if(splitChild[0].equals("CalculatedHMAC")){
                resultSignature.setCalculatedHMAC(splitChild[1]);
            }
        }

        return resultSignature;
    }
}
