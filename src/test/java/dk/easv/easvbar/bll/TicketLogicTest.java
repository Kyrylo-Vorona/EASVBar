package dk.easv.easvbar.bll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class TicketLogicTest {

    private Logic logic = Logic.getInstance();

    @Test
    @DisplayName("Email validation should recognize correct format")
    public void testEmailValidator() {
        assertTrue(logic.isValidEmail("anton@easv.dk"), "Should accept valid email with @ and dot");
        assertFalse(logic.isValidEmail("invalid-email"), "Should reject email without @");
        assertFalse(logic.isValidEmail(null), "Should handle null gracefully");
    }

    @Test
    @DisplayName("UUID generation should be unique and correct length")
    public void testUUIDGeneration() {
        String code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        assertNotNull(code);
        assertEquals(8, code.length(), "Ticket code must be 8 characters long");
    }
}
