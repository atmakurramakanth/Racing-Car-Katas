package tddmicroexercises.telemetrysystem;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tddmicroexercises.telemetrysystem.services.impl.ClientImpl;
import tddmicroexercises.telemetrysystem.services.impl.ConnectionImpl;
import tddmicroexercises.telemetrysystem.services.impl.OnlineStatusImpl;
import tddmicroexercises.telemetrysystem.services.impl.TelemetryDiagnosticControls;

import static org.mockito.Mockito.*;

public class TelemetryDiagnosticControlsTest {
    private TelemetryClient telemetryClient;
    private ConnectionImpl connection;
    private ClientImpl client;
    private OnlineStatusImpl onlineStatus;
    private TelemetryDiagnosticControls telemetryDiagnosticControls;

    @BeforeEach
    public void setUp() {
        telemetryClient = mock(TelemetryClient.class);
        connection = mock(ConnectionImpl.class);
        client = mock(ClientImpl.class);
        onlineStatus = mock(OnlineStatusImpl.class);
        telemetryDiagnosticControls = new TelemetryDiagnosticControls(client, connection, onlineStatus);
    }

    @Test
    public void checkTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response() throws Exception {
        // Arrange
        String diagnosticInfo = "test";
        when(onlineStatus.getOnlineStatus()).thenReturn(true);
        when(client.receive()).thenReturn(diagnosticInfo);

        // Act
        telemetryDiagnosticControls.checkTransmission();

        // Assert
        Assertions.assertEquals(diagnosticInfo, telemetryDiagnosticControls.getDiagnosticInfo());
        verify(client, times(1)).send("AT#UD");
        verify(connection, times(1)).disconnect();
    }


    @Test
    public void checkTransmission_should_throw_exception_when_failed_to_connect() throws Exception {
        // Arrange
        when(onlineStatus.getOnlineStatus()).thenReturn(true);
        doThrow(new RuntimeException("Connection failed")).when(connection).disconnect();

        // Act & Assert
        Assertions.assertThrows(Exception.class, telemetryDiagnosticControls::checkTransmission);
        verify(client, never()).send(anyString());
    }




}
