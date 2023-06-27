package tddmicroexercises.telemetrysystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tddmicroexercises.telemetrysystem.services.impl.ClientImpl;

public class ClientTest {

    @Test
    public void send_should_set_diagnosticMessageResult_when_message_is_AT_UD() {
        // Arrange
        String message = "AT#UD";
        ClientImpl client = new ClientImpl();

        // Act
        client.send(message);

        // Assert
        String expectedDiagnosticMessage = "LAST TX rate................ 100 MBPS\r\nHIGHEST TX rate............. 100 MBPS\r\nLAST RX rate................ 100 MBPS\r\nHIGHEST RX rate............. 100 MBPS\r\nBIT RATE.................... 100000000\r\nWORD LEN.................... 16\r\nWORD/FRAME.................. 511\r\nBITS/FRAME.................. 8192\r\nMODULATION TYPE............. PCM/FM\r\nTX Digital Los.............. 0.75\r\nRX Digital Los.............. 0.10\r\nBEP Test.................... -5\r\nLocal Rtrn Count............ 00\r\nRemote Rtrn Count........... 00";
    }

    @Test
    public void receive_should_return_non_empty_string() {
        // Arrange
        ClientImpl client = new ClientImpl();

        // Act
        String receivedMessage = client.receive();

        // Assert
        Assertions.assertNotNull(receivedMessage);
        Assertions.assertNotEquals("", receivedMessage);
    }

}
