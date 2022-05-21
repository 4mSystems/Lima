package app.te.lima_zola.core

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService :
  FirebaseMessagingService() {
  override fun onMessageReceived(remoteMessage: RemoteMessage) {
    sendNotification(remoteMessage.data)
  }

  override fun onNewToken(token: String) {
    Log.e("onNewToken", "onNewToken: " + token)
  }

  private fun sendNotification(messageBody: MutableMap<String, String>) {
//    val bundle = Bundle()
//    bundle.putBoolean(Constants.NOTIFICATION, true)
//    val pendingIntent = NavDeepLinkBuilder(this)
//      .setComponentName(HomeActivity::class.java)
//      .setGraph(R.navigation.nav_home)
//      .setDestination(R.id.notificationsFragment)
//      .setArguments(bundle)
//      .createPendingIntent()
//
//    val channelId = "channelId"
//    val defaultSoundUri: Uri = Uri.parse(
//      ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/" + R.raw.notify_default
//    )
//    val r: Ringtone = RingtoneManager.getRingtone(applicationContext, defaultSoundUri)
//    r.play()
//    val notificationBuilder = NotificationCompat.Builder(this, channelId)
//      .setSmallIcon(R.mipmap.ic_launcher_release)
//      .setPriority(NotificationCompat.PRIORITY_HIGH)
//      .setContentTitle(messageBody["title"])
//      .setContentText(messageBody["body"])
//      .setAutoCancel(true)
//      .setSound(defaultSoundUri)
//      .setNumber(0)
//      .setContentIntent(pendingIntent)
//
//    val notificationManager =
//      getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//
//    // Since android Oreo notification channel is needed.
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//      val channel = NotificationChannel(
//        channelId,
//        "Channel human readable title",
//        NotificationManager.IMPORTANCE_DEFAULT
//      )
//      notificationManager.createNotificationChannel(channel)
//    }
//
//    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())

  }
}