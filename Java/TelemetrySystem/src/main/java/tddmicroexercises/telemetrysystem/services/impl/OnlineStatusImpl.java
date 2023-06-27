package tddmicroexercises.telemetrysystem.services.impl;

import tddmicroexercises.telemetrysystem.services.OnlineStatus;

public class OnlineStatusImpl implements OnlineStatus {
    private static boolean onlineStatus;
    public boolean getOnlineStatus() {
        return onlineStatus;
    }
}
