package com.redhat.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JWKSetEncrypt {
    public static List<String> encrypt(String jwksetUrl, String...rawTexts) throws Exception {
        String publicKeyJson = ObtainPublicKey.fetch(jwksetUrl);
        JsonObject publicKeyObject = JsonParser.parseString(publicKeyJson).getAsJsonObject();
        String publicKey = publicKeyObject.get("publicKeys").getAsJsonArray().get(0).getAsJsonObject().get("publicKey").getAsString();

        List<String> encryptedTexts = new ArrayList<String>();

        for (String rawText : rawTexts) {
            String encryptedText = RSA.encryptFromPublicKeyString(rawText, publicKey);
            encryptedTexts.add(encryptedText);
        }

        return encryptedTexts;
    }


    public static void main(String[] args) throws Exception {
        List<String> encryptedTexts = JWKSetEncrypt.encrypt(args[0], Arrays.copyOfRange(args, 1, args.length));

        for (String encryptedText : encryptedTexts) {
            System.out.println(encryptedText);
        }
    }
}