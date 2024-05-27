package com.example.chat;

import com.example.chat.RC6.Cipher;
import com.example.chat.RC6.impl.RC6;
import com.example.chat.client.Client;
import com.example.chat.server.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Cipher cipher = new RC6();
//        Server server = new Server();
//        Client kate = new Client(server, cipher, 666);
//        Client klim = new Client(server, cipher, 667);
//
//        kate.start(19);
//        klim.start(19);
//
//        Thread.sleep(5000);
//
//        kate.sendMessage("hello klim", 19, 667);
//        klim.sendMessage("hello kate", 19, 666);
//
//        // Второй блок закомментированного кода
//        Cipher cipher2 = new RC6();
//        Server server2 = new Server();
//        Client alice = new Client(server2, cipher2, 66);
//        Client bob = new Client(server2, cipher2, 67);
//
//        alice.start(5);
//        bob.start(5);

        // Запуск Spring Boot приложения
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