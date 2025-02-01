package hr.ferit.karlovlasic.eldare


import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat


class NotificationReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context, intent: Intent) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat
            .Builder(context, "mainChannel")
            .setSmallIcon(R.drawable.baseline_local_hospital_24)
            .setContentTitle("Uzmite Lijek")
            .setContentText("Uzmite Vaš sljedeći lijek")
            .build()

        manager.notify(1, notification)
    }
}