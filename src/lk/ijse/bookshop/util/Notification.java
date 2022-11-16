package lk.ijse.bookshop.util;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notification {
    public static void notifie(String title, String message, NotificationType notificationType){
        TrayNotification trayNotification=new TrayNotification();
        AnimationType animationType=AnimationType.POPUP;
        trayNotification.setAnimationType(animationType);
        trayNotification.setTitle(title);
        trayNotification.setMessage(message);
        trayNotification.setNotificationType(notificationType);
        trayNotification.showAndDismiss(Duration.millis(2000));
    }
}
