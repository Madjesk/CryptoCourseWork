package com.example.chat.DiffHelman;

import java.math.BigInteger;
import java.util.Random;

public class DiffHellman {
    public static BigInteger[] generatePages() {
        BigInteger[] resultPair = new BigInteger[2];
        BigInteger p = new BigInteger(300, new Random());
        resultPair[0] = p;
        BigInteger[] MostPopularPervoobr = {BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(5),
                BigInteger.valueOf(7), BigInteger.valueOf(11), BigInteger.valueOf(13),
                BigInteger.valueOf(17)};

        Random random = new Random();
        BigInteger g = MostPopularPervoobr[random.nextInt(MostPopularPervoobr.length)];
        resultPair[1] = g;
        return resultPair;
    }
}
