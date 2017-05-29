package com.ju5tEgoist;

import java.io.IOException;

/**
 * Created by yulia on 25.05.17.
 */
public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser();
        try {
         //  parser.getAllParameters(args[0]);
            parser.getAllParameters("hel");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
