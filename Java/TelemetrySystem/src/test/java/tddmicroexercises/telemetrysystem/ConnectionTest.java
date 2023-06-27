package tddmicroexercises.telemetrysystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tddmicroexercises.telemetrysystem.services.OnlineStatus;
import tddmicroexercises.telemetrysystem.services.impl.ConnectionImpl;
import tddmicroexercises.telemetrysystem.services.impl.OnlineStatusImpl;

public class ConnectionTest {
    @Test
    public void connect_should_set_onlineStatus_to_false_when_telemetryServerConnectionString_is_empty() {
        // Arrange
        String connectionString = "";
        ConnectionImpl connection = new ConnectionImpl();
        OnlineStatusImpl onlineStatus = new OnlineStatusImpl();


        // Act && Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            connection.connect(connectionString);
        });
    }
    @Test
    public void disconnect_should_set_onlineStatus_to_false() {
        // Arrange
        ConnectionImpl connection = new ConnectionImpl();

        // Act
        connection.disconnect();
    }
}
