package com.redhat.utility;

import java.util.Arrays;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JWKSetEncrypt {
    public static String[] encrypt(String jwksetUrl, String[] rawTexts) throws Exception {
        String publicKeyJson = ObtainPublicKey.fetch(jwksetUrl);
        JsonObject publicKeyObject = JsonParser.parseString(publicKeyJson).getAsJsonObject();
        String publicKey = publicKeyObject.get("publicKeys").getAsJsonArray().get(0).getAsJsonObject().get("publicKey").getAsString();

        String[] encryptedTexts = new String[rawTexts.length];

        for(int i = 0; i < rawTexts.length; i++) {
            String encryptedText = RSA.encryptFromPublicKeyString(rawTexts[i], publicKey);
            encryptedTexts[i] = encryptedText;
        }

        return encryptedTexts;
    }

    public static String ping() {
        return "Ping...";
    }

    public static void main(String[] args) throws Exception {
        String[] encryptedTexts = JWKSetEncrypt.encrypt(args[0], Arrays.copyOfRange(args, 1, args.length));

        for (String encryptedText : encryptedTexts) {
            System.out.println(encryptedText);
        }
    }
}