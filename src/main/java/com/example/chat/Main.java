package com.example.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}


//
//import RC6.impl.RC6;
//import client.Client;
//import server.Server;
//
//import RC6.Cipher;

//public class com.example.chat.Main {
//    public static void main(String[] args) throws InterruptedException {
//        Cipher cipher = new RC6();
//        Server server = new Server();
//        Client kate = new Client(server, cipher, 18);
//        Client klim = new Client(server, cipher, 19);
//
//        kate.start(17);
//        klim.start(17);
//
//        Thread.sleep(5000);
//
//        kate.sendMessage("hello klim", 17, 19);
//        klim.sendMessage("hello kate", 17, 18);

//        --------------
//
//        Cipher cipher = new RC6();
//        Server server = new Server();
//        Client alice = new Client(server, cipher, 66);
//        Client bob = new Client(server, cipher, 67);
//
//        alice.start(5);
//        bob.start(5);
//    }
//}