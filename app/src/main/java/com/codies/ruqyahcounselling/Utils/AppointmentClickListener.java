package com.codies.ruqyahcounselling.Utils;

public interface AppointmentClickListener {
    void onAccept(String appointmentId,String userId);

    void onDelete(String appointmentId,String userId);
}
