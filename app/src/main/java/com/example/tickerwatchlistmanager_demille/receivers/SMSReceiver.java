package com.example.tickerwatchlistmanager_demille.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.tickerwatchlistmanager_demille.MainActivity;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        //what 2 do when sms happens
        final Bundle bundle = intent.getExtras();
        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            if(bundle != null){
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format").toString();
                String message = "";
                for (Object o : pdusObj) {
                    SmsMessage currentMessage =
                            SmsMessage.createFromPdu((byte[]) o, format);
                    String sender =
                            currentMessage.getDisplayOriginatingAddress();
                    message =
                            currentMessage.getDisplayMessageBody();
                    String printMessage = "Sender: " + sender + " Message: " + message;
                }
                Intent launchIntent = new Intent(context, MainActivity.class);
                launchIntent.setAction(Intent.ACTION_SEND);
                launchIntent.putExtra("sms", message);
                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(launchIntent);

            }
        }
    }
}