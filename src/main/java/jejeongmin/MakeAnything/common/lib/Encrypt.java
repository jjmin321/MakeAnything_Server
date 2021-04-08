package jejeongmin.MakeAnything.common.lib;

import jejeongmin.MakeAnything.common.exception.EncryptException;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;

@Component
public class Encrypt {

    /**
     * All Methods
     * @param data - A Data want to encrypt by Encryption Method
     * @return encrypted data - A Data encrypted by Encryption Method
     * @throws EncryptException - An Exception occurred in Encryption Method
     */

    public String sha256(String data) throws EncryptException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b: md.digest()) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new EncryptException("SHA-256 암호화 중 오류가 발생함");
        }

    }

    public String sha512(String data) throws EncryptException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(data.getBytes());
            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : byteData) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new EncryptException("SHA-512 암호화 중 오류가 발생함");
        }
    }

}
