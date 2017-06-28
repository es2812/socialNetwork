/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author dizzy
 */
public class Hash {
    public Hash(){}
    public String doHash(String txtplano) throws NoSuchAlgorithmException{
     MessageDigest digest = MessageDigest.getInstance("SHA-256");
     byte[] hash = digest.digest(txtplano.getBytes(StandardCharsets.UTF_8));
     return new String(hash, StandardCharsets.UTF_8);
    }
}
