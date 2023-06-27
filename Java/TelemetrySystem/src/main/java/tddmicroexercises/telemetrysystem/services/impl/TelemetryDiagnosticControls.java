package tddmicroexercises.telemetrysystem.services.impl;

import tddmicroexercises.telemetrysystem.services.DiagnosticsControls;

public class TelemetryDiagnosticControls implements DiagnosticsControls {
    private final String DiagnosticChannelConnectionString = "*111#";
    private String diagnosticInfo = "";
    private final ClientImpl client;
    private final ConnectionImpl connection;
    private OnlineStatusImpl onlineStatus;

    public TelemetryDiagnosticControls(ClientImpl client, ConnectionImpl connection,OnlineStatusImpl onlineStatus) {
        this.client = client;
        this.connection = connection;
        this.onlineStatus = onlineStatus;
    }

    public String getDiagnosticInfo() {
        return this.diagnosticInfo;
    }

    public void setDiagnosticInfo(String diagnosticInfo) {
        this.diagnosticInfo = diagnosticInfo;
    }

    public void checkTransmission() throws Exception {
        this.diagnosticInfo = "";
        this.connection.disconnect();

        for(int retryLeft = 3; !this.onlineStatus.getOnlineStatus() && retryLeft > 0; --retryLeft) {
            this.connection.connect("*111#");
        }

        if (!this.onlineStatus.getOnlineStatus()) {
            throw new Exception("Unable to connect.");
        } else {
            this.client.send("AT#UD");
            this.diagnosticInfo = this.client.receive();
        }
    }
}
