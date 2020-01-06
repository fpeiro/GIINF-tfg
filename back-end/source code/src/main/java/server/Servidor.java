/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Felipe
 */
@EnableCaching
@SpringBootApplication
@ComponentScan({"server"})
public class Servidor {

    public static void main(String[] args) throws Exception {
        SpringApplication servidor = new SpringApplication(Servidor.class);
        ApplicationContext context = servidor.run(args);
//        Cliente cliente = new Cliente(context);
//        cliente.run();
    }
}
