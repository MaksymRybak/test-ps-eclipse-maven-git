package org.paumard;

import org.paumard.service.MessageService;

public class HelloWorld {

    public static void main(String[] args) {

        MessageService messageService = new MessageService();
        System.out.println(messageService.getMessage());
    }
}
