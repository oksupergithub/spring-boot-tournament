package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.models.Game;

@SpringBootTest
class GameTest {

    @Test
    @DisplayName("Lombok 테스트 marche ?")
    void testEntity() {
        Game g = new Game();
        g.setName("test");

        assertEquals("test", g.getName());
    }
}
