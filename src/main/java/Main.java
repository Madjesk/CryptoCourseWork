package RC6;

import RC6.impl.RC6;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8};
        byte[] text = {1, 2, 3, 4, 5, 6, 7, 8};
        Cipher cipher = new RC6();

        System.out.println(Arrays.toString(cipher.encrypt(text, key)));
        System.out.println(Arrays.toString(cipher.decrypt(text, key)));

        System.out.println("Hello world!");
    }
}