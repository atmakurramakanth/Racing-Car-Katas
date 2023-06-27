package tddmicroexercises.telemetrysystem;

import tddmicroexercises.telemetrysystem.services.impl.ClientImpl;
import tddmicroexercises.telemetrysystem.services.impl.ConnectionImpl;
import tddmicroexercises.telemetrysystem.services.impl.OnlineStatusImpl;

public class TelemetryDiagnosticControls
{
    private final String DiagnosticChannelConnectionString = "*111#";

    private final TelemetryClient telemetryClient;
    private final OnlineStatusImpl onlineStatus;
    private final ConnectionImpl connection;
    private  final ClientImpl client;
    private String diagnosticInfo = "";

        public TelemetryDiagnosticControls(OnlineStatusImpl onlineStatus, ConnectionImpl connection, ClientImpl client)
        {
            this.onlineStatus = onlineStatus;
            this.connection = connection;
            this.client = client;
            telemetryClient = new TelemetryClient();
        }

        public String getDiagnosticInfo(){
            return diagnosticInfo;
        }

        public void setDiagnosticInfo(String diagnosticInfo){
            this.diagnosticInfo = diagnosticInfo;
        }

        public void checkTransmission() throws Exception
        {
            diagnosticInfo = "";

            connection.disconnect();

            int retryLeft = 3;
            while (onlineStatus.getOnlineStatus() == false && retryLeft > 0)
            {
                connection.connect(DiagnosticChannelConnectionString);
                retryLeft -= 1;
            }

            if(onlineStatus.getOnlineStatus() == false)
            {
                throw new Exception("Unable to connect.");
            }

            client.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
            diagnosticInfo = client.receive();
    }
}

