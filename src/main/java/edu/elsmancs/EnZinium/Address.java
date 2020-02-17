package edu.elsmancs.EnZinium;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {

    /**
     * Metodos
     * toString()
     * GenerateKeyPair
     * <p>
     * Atributos
     * PK
     * SK
     * balance
     * symbol
     */

    private PublicKey PK = null;
    private PrivateKey SK = null;
    private int balance = 0;
    private final String symbol = "EZI";

    public Address() {
    }

    public void generateKeyPair() {
        KeyPair pair = GenSig.generateKeyPair();
        this.PK = pair.getPublic();
        this.SK = pair.getPrivate();
    }

    @Override
    public String toString() {
        return "PK " + getPK().hashCode() + "\n"
                + "Balance " + getBalance() + " " + this.symbol + "\n";
    }

    public PublicKey getPK() {
        return PK;
    }

    public int getBalance() {
        return balance;
    }
}
