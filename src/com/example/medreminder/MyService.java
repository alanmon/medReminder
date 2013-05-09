package com.example.medreminder;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class MyService extends Service
{
	int state = 0;
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        buildUpdate();

        return super.onStartCommand(intent, flags, startId);
    }

    private void buildUpdate()
    {
    	RemoteViews views = new RemoteViews(getPackageName(),
				R.layout.widget);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        ComponentName thisWidget = new ComponentName(this, widget.class);
 
		//RemoteViews imageViews = new RemoteViews(context.getPackageName(),
			//	R.id.widget_imageView);
		//imageViews.setImate
        //TODO: change how to show the image
		if(state == 0)
		{
			//views.setInt(R.id.widget_imageView, "setImageLevel", 1);
			views.setImageViewResource(R.id.widget_imageView, R.drawable.saturn1);
			appWidgetManager.updateAppWidget(thisWidget, views);
			state = 1;
		}
		else if (state == 1) {
			views.setImageViewResource(R.id.widget_imageView, R.drawable.saturn2);
			appWidgetManager.updateAppWidget(thisWidget, views);
			state = 2;
		}
		else if (state == 2) {
			views.setImageViewResource(R.id.widget_imageView, R.drawable.saturn3);
			appWidgetManager.updateAppWidget(thisWidget, views);
			state = 3;
		}
		else if (state == 3) {
			views.setImageViewResource(R.id.widget_imageView, R.drawable.saturn4);
			appWidgetManager.updateAppWidget(thisWidget, views);
			state = 4;
		}
		else
		{
			//views.setInt(R.id.widget_imageView, "setImageLevel", 2);

		// Tell the AppWidgetManager to perform an update on the current app
		// widget
		views.setImageViewResource(R.id.widget_imageView, R.drawable.saturn5);
		appWidgetManager.updateAppWidget(thisWidget, views);
		state = 0;
		}
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
