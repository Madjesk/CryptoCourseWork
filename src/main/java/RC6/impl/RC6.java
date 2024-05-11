package RC6.impl;

import RC6.Cipher;

public class RC6 implements Cipher {
    @Override
    public int getSizeBlockTextInBits() {
        return 128;
    }
}
//
//import RC6.SymmetricalEnctryptionAlgorithm;
//
//public class RC6Bits32 implements SymmetricalEnctryptionAlgorithm {
//    private static final int OPEN_TEXT_BLOCK_LENGTH = 128;
//    private static final int ROUND_NUMBER = 20;
//
//    public enum CipherKeyLength {
//        BIT_128(128), BIT_192(192), BIT_256(256);
//        public final int bitsNumber;
//
//        CipherKeyLength(int bitsNumber) {
//            this.bitsNumber = bitsNumber;
//        }
//    }
//
//
//
////    private byte[] cipherKey;
////    private final int wordLength;
////    private final RoundKeysGenerator32BitsRC6 roundKeysGenerator;
////
////    private RC6Bits32(RoundKeysGenerator32BitsRC6 roundKeysGenerator) {
////        this.roundKeysGenerator = roundKeysGenerator;
////        this.wordLength = roundKeysGenerator.getWordLength();
////    }
//
//    @Override
//    public byte[] encode(byte[] inputBlock) {
//
//        int[] translatedInputArray = translateInputByteArrayIntArray(inputBlock);
//        int a = translatedInputArray[0], b = translatedInputArray[1];
//        int c = translatedInputArray[2], d = translatedInputArray[3];
//
////        int[] roundKeys = roundKeysGenerator.generate(cipherKey);
//        b = b + roundKeys[0];
//        d = d + roundKeys[1];
//        for (int i = 1; i <= ROUND_NUMBER; i++) {
//            int t = leftCycleShift(b * (2 * b + 1), (int) log2(wordLength));
//            int u = leftCycleShift(d * (2 * d + 1), (int) log2(wordLength));
//            a = leftCycleShift(a ^ t, u) + roundKeys[2 * i];
//            c = leftCycleShift(c ^ u, t) + roundKeys[2 * i + 1];
//
//            int tmpA = a;
//            a = b;
//            b = c;
//            c = d;
//            d = tmpA;
//        }
//        a = a + roundKeys[2 * ROUND_NUMBER + 2];
//        c = c + roundKeys[2 * ROUND_NUMBER + 3];
//
//        translatedInputArray[0] = a;
//        translatedInputArray[1] = b;
//        translatedInputArray[2] = c;
//        translatedInputArray[3] = d;
//
//        return translateIntArrayToByteArray(translatedInputArray);
//    }
//
//
//    int[] translateInputByteArrayIntArray(byte[] array) {
//        int[] translatedArray = new int[OPEN_TEXT_BLOCK_LENGTH / wordLength];
//        int index = 0;
//        for(int i = 0; i < translatedArray.length; i++) {
//            translatedArray[i] = (array[index++] & 0xFF)
//                    | ((array[index++] & 0xFF) << Byte.SIZE)
//                    | ((array[index++] & 0xFF) << Byte.SIZE * 2)
//                    | ((array[index++] & 0xFF) << Byte.SIZE * 3);
//        }
//        return translatedArray;
//    }
//
//    private int leftCycleShift(int digit, int shift) {
//        return (digit << shift) | (digit >>> (wordLength - shift));
//    }
//
//    byte[] translateIntArrayToByteArray(int[] src) {
//        byte[] dest = new byte[src.length * Integer.SIZE / Byte.SIZE];
//        for (int i = 0; i < dest.length; i++) {
//            int wordLengthInByte = wordLength / Byte.SIZE;
//            dest[i] = (byte) ((src[i / wordLengthInByte] >>> (i % wordLengthInByte) * Byte.SIZE) & 0xFF);
//        }
//        return dest;
//    }
//
//    private double log2(double digit) {
//        return Math.log(digit) / Math.log(2);
//    }
//
//    @Override
//    public byte[] decode(byte[] inputBlock) {
//        checkArgs(inputBlock);
//
//        int[] translatedInputArray = translateInputByteArrayIntArray(inputBlock);
//        int a = translatedInputArray[0], b = translatedInputArray[1];
//        int c = translatedInputArray[2], d = translatedInputArray[3];
//
////        int[] roundKeys = roundKeysGenerator.generate(cipherKey);
//        c = c - roundKeys[2 * ROUND_NUMBER + 3];
//        a = a - roundKeys[2 * ROUND_NUMBER + 2];
//        for (int i = ROUND_NUMBER; i >= 1; i--) {
//            int tmpD = d;
//            d = c;
//            c = b;
//            b = a;
//            a = tmpD;
//
//            int u = leftCycleShift(d * (2 * d + 1), (int) log2(wordLength));
//            int t = leftCycleShift(b * (2 * b + 1), (int) log2(wordLength));
//
//            c = rightCycleShift(c - roundKeys[2 * i + 1], t) ^ u;
//            a = rightCycleShift(a - roundKeys[2 * i], u) ^ t;
//        }
//        d = d - roundKeys[1];
//        b = b - roundKeys[0];
//
//        translatedInputArray[0] = a;
//        translatedInputArray[1] = b;
//        translatedInputArray[2] = c;
//        translatedInputArray[3] = d;
//
//        return translateIntArrayToByteArray(translatedInputArray);
//    }
//
//    private int rightCycleShift(int digit, int shift) {
//        return (digit >>> shift) | (digit << (wordLength - shift));
//    }
//
//    @Override
//    public void setKey(byte[] cipherKey) {
//        this.cipherKey = cipherKey;
//    }
//
//    @Override
//    public int getOpenTextBlockSizeInBytes() {
//        return OPEN_TEXT_BLOCK_LENGTH / Byte.SIZE;
//    }
//}

