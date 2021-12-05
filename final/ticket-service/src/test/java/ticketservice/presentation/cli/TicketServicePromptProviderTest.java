package ticketservice.presentation.cli;

import com.epam.training.ticketservice.presentation.cli.TicketServicePromptProvider;
import org.jline.utils.AttributedString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TicketServicePromptProviderTest {

    @InjectMocks
    private TicketServicePromptProvider ticketServicePromptProvider;

    @Test
    public void testTicketServicePromptProviderTestShouldReturnCorrectPrompt() {
        // Given
        AttributedString excepted = new AttributedString("Ticket service>");

        // Then
        Assertions.assertEquals(excepted, ticketServicePromptProvider.getPrompt());
    }
}

