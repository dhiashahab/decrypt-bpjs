package decryptbpjs;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesKeySpec {
    private SecretKeySpec key;
    private IvParameterSpec iv;
    
    public SecretKeySpec getKey() {
        return key;
    }
    
    public void setKey(SecretKeySpec key) {
        this.key = key;
    }
    
    public IvParameterSpec getIv() {
        return iv;
    }
    
    public void setIv(IvParameterSpec iv) {
        this.iv = iv;
    }
}
