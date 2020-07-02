package pos.core.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.SpringApplication;

public class GeneratePasswordCryptedUtil {

    public static void main(String[] args) {
        SpringApplication.run(pos.PosApplication.class, args);

        System.out.println(hash("123"));
    }


    public static String hash(String value) {
        try {
            return DigestUtils.sha1Hex(value);

        } catch (Exception e) {
            throw new RuntimeException(e);
             }

         }


}