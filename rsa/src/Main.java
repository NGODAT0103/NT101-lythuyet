import backend.CustomRsa;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException {

        CustomRsa customRsa = new CustomRsa();
        customRsa.importPublicKey("-----BEGIN CERTIFICATE-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuTWIbjjepUYcmPazM1+DBscmvKJO+CV5Y3QuJIyLnSCU/sdCQJuK5beeZkeoJwIVkRpRe+YFAc19quRjvutSH9jQfsWSG4ifYz7t25AW+qsJ79BUb0Yu/0Qp9inrtj5VtY4cBqxJmCA2Cw39kWnHmrIl6AI4/FH8kgjo4rPFCyvqhfAg8CO6CZ9hZThyysQhGRNxVK9XSxKwSwsnHBWiLMfEMioplpPOjY3S7qFrAuIr9VKBWbhs1HCKtIB1lBuy1gD8+GyTswR9ELRlfjZe7OE5Q7tsTm/Ah9I4GXIy7AIJf0+ZOGrlSyQmQpHn532OV2Z3BW0YeTi/Tkxn/FJ5OwIDAQAB\n" +
                "-----END CERTIFICATE-----");
        int stop = 0 ;
        customRsa.importPrivateKey("-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5NYhuON6lRhyY9rMzX4MGxya8ok74JXljdC4kjIudIJT+x0JAm4rlt55mR6gnAhWRGlF75gUBzX2q5GO+61If2NB+xZIbiJ9jPu3bkBb6qwnv0FRvRi7/RCn2Keu2PlW1jhwGrEmYIDYLDf2RaceasiXoAjj8UfySCOjis8ULK+qF8CDwI7oJn2FlOHLKxCEZE3FUr1dLErBLCyccFaIsx8QyKimWk86NjdLuoWsC4iv1UoFZuGzUcIq0gHWUG7LWAPz4bJOzBH0QtGV+Nl7s4TlDu2xOb8CH0jgZcjLsAgl/T5k4auVLJCZCkefnfY5XZncFbRh5OL9OTGf8Unk7AgMBAAECggEAMjePHd+ikqpvb5QYY5oGk0xlNulFQygaATrLlhJbeoeOjoQldRIXd5CxTKcqMyVwBo9dv5vPCL2YUMErQGVat5LYeT8FjhydptG1uX4YlO/SOmlAs4kSjhkDzaBxLlE5eQgmljKZo7s8HAQ3l/lX6xxZhc4weE2cj1YGGw3GI/BWmEN8lzjY+q4JMTYwkRYMwd+Kxv5BDTz30YxFQstwcMf2NhbbMcjp4DNfAuhX8huo8PIiRoxkOnsUcoAo9oPdANv/UxZb8w/EfG+VrhKMPH1suGSdFBirny+PA3kXb7iunHmFrY8GmzPbN5cJ9lWDtcgtr0LlNjqifoWMBivcTQKBgQDmqEJcxY7utnWDTVDAX2IEDV8eSvsBCwanuQwr10IXjJuzNRLSvSOnpWPTIW01X5kgiAO/SjnfN7huVFQvNX2+BKWEIPO2+mKaGjWQywrhAk8uoBPwOgB6MAX9LWTMR96mSvPILClO07yG3dGb2JmTanh1j48CTrLUNrl1VYparQKBgQDNjvHnp3z/qCYD/m2yal2r1sM7ryLfKhkdK/HfyYCE+3YuiTtAR3tv9FdkRvMuBHMpdMxNV4o2H0EBIaVzQiUIiZGUu1rS3GiGjFElQLK3Slu9WZPMX2HJdeIykNPaym33XGDqFA/LuYg4DhV7s/aDhpB/28lgmLjkq/DfKcxIhwKBgQDKWZZOeOpZtSwGko9jFMqSL4DgvHrTgW7HOWnaFC/hJPN7FfZ7dn6PFmMq18pM5p8ZkPlomZ+HmgRslgJ8n/q99qmV7qqgPay+8HtJmTbkCviYOMsFFn1QtEysvWQOaoaDcIHJfCSJCOv9jR/UDwM79k3TJKwbJBtyXMf6jZ6gyQKBgCa9PMu6ne0UhG2WQpgwQY0EAXE7FWhXZ7o+sOvK7uSg+2nTxQ3dKt8zflDQqj3O6uEyONqOl+aRR0/uRl8NZStRudT85iX9Ednhkd1ibwvqAJYTzq3if+xWwhVjg/mk0sqeZ0lWFJABXDGsPdI9XUT2VD80baQ9xAKcgN2d4/j7AoGAfmCS4i+h1mkLrM2TFIk4DMOl0ITaDG1rBfujoTWrXxcsLwxW8RoJaWAm/eEEyBnZjSQnAk74/HUXkuPTkQG80LZtv28Mj/46DKUBQB8E4qOn+wMBSlykqs3WR5BATIto9354Sn8qN0osussChfLaZXMQMcsJG+PD93PnHs/6mt0=\n" +
                "-----END RSA PRIVATE KEY-----\n");
        String plaintext ="Hello world";

        byte[] ciphertextBytes = customRsa.encryptBytes(plaintext.getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(ciphertextBytes,StandardCharsets.UTF_8));
        System.out.println(new String(customRsa.decryptBytes(ciphertextBytes),StandardCharsets.UTF_8));



    }
}