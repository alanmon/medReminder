package com.example.medreminder;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

public class widget extends AppWidgetProvider {
	int state = 0;
	public static String ACTION_WIDGET_CONFIGURE = "ConfigureWidget";
    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
    private PendingIntent service = null; 
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		final AlarmManager m = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        final Calendar TIME = Calendar.getInstance();
        TIME.set(Calendar.MINUTE, 0);
        TIME.set(Calendar.SECOND, 0);
        TIME.set(Calendar.MILLISECOND, 0);

        final Intent i = new Intent(context, MyService.class);

        if (service == null)
        {
            service = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        }
        //TODO:: set how long it takes to update the widget
        m.setRepeating(AlarmManager.ELAPSED_REALTIME, 0, 1000 * 100, service);
		/**
		// TODO Update the Widget UI.
		final int N = appWidgetIds.length;

		// Perform this loop procedure for each App Widget that belongs to this
		// provider
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];

			// Create an Intent to launch ExampleActivity
			Intent intent = new Intent(context, C_main.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
					intent, 0);

			// Get the layout for the App Widget and attach an on-click listener
			// to the button
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.test_widget);
			views.setOnClickPendingIntent(R.id.widget_imageView, pendingIntent);

			//RemoteViews imageViews = new RemoteViews(context.getPackageName(),
				//	R.id.widget_imageView);
			//imageViews.setImate

			if(state == 0)
			{
				//views.setInt(R.id.widget_imageView, "setImageLevel", 1);
				views.setImageViewResource(R.id.widget_imageView, R.drawable.state1);
				appWidgetManager.updateAppWidget(appWidgetId, views);
				state = 1;
			}
			else
				//views.setInt(R.id.widget_imageView, "setImageLevel", 2);

			// Tell the AppWidgetManager to perform an update on the current app
			// widget
			views.setImageViewResource(R.id.widget_imageView, R.drawable.state2);
			appWidgetManager.updateAppWidget(appWidgetId, views);	
		}
		**/
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Handle deletion of the widget.
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Widget has been disabled.
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Widget has been enabled.
		super.onEnabled(context);
	}
	
	@Override
    public void onReceive(Context context, Intent intent) {

      super.onReceive(context, intent);

    }
}