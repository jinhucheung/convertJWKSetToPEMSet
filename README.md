# Convert JWK to PEM

A simple utility written in Java + Camel to fetch a JWK Set from an IDP URL and then produce the Public Key. And RSA256 encrypt by public key.

Usage:

```
$ java -jar jwkset-encrypt-1.0.0-SNAPSHOT.jar <URL> <Text>...
```

Both http and https address are supported. For https, this utility assumes that the cert as been imported into the JDK cacerts.

For Jmeter, add [jwkset-encrypt-1.0.0-SNAPSHOT.jar](https://github.com/jinhucheung/convertJWKSetToPEMSet/releases/download/v0.1.0/jwkset-encrypt-1.0.0-SNAPSHOT.jar) to `$JMETER_HOME/lib/ext`.

Then import this jar into `Test Plan`. And use it in `JSR223 PreProcessor`, example:

```
import com.redhat.utility.JWKSetEncrypt;

log.info(JWKSetEncrypt.ping());

String[] logins = {vars.get("username"), vars.get("password")};
String[] encryptedTexts = JWKSetEncrypt.encrypt(vars.get("jkws_url"), logins);

vars.put("encrypted_username", encryptedTexts[0]);
vars.put("encrypted_password", encryptedTexts[1]);
```