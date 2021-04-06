package jejeongmin.MakeAnything.common.lib;

import jejeongmin.MakeAnything.common.exception.EncryptException;

import java.security.MessageDigest;

public class Encrypt {

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
            StringBuilder sb = new StringBuilder();
            for (byte b: md.digest()) {
                sb.append(Integer.toHexString(0xff & b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new EncryptException("SHA-512 암호화 중 오류가 발생함");
        }
    }

}
