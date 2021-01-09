package org.paumard.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HelloServiceTest {

    @Test
    public void should_return_the_hello_world_message() {
        // Given
        HelloServiceImpl messageService = new HelloServiceImpl();
        String expected = "Hello World!";
        
        // When
        String message = messageService.getMessage();
        
        // Then
        Assertions.assertThat(message).isEqualTo(expected);
    }
}
