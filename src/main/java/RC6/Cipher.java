package RC6;

public interface Cipher {
    int getSizeBlockTextInBits();

    default byte[] encrypt(byte[] text) {
        return text;
    }

    default byte[] decrypt(byte[] text) {
        return text;
    }
}
