package com.amel.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.amel.bakingapp.R;

/**
 * Created by amelia on 19/09/2017.
 */

public class BakingWidgetIntentService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetFactory(this.getApplicationContext(), intent);
    }

    class BakingWidgetFactory implements RemoteViewsFactory {

        private Context mContext;
        private int mAppWidgetId;
        private Cursor cursor;


        public BakingWidgetFactory(Context context, Intent intent) {

            mContext = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }


        private void initCursor() {

            if (cursor != null) {
                cursor.close();
            }

        }

        @Override
        public void onCreate() {
            initCursor();

        }

        @Override
        public void onDataSetChanged() {
            initCursor();
        }

        @Override
        public void onDestroy() {
            cursor.close();
        }

        @Override
        public int getCount() {
            return cursor.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_ingredients_item);

            if (cursor.getCount() != 0) {
                cursor.moveToPosition(position);

                rv.setTextViewText(R.id.widget_recipe_name, cursor.getString(3));

                String measure = cursor.getString(1) + " " +
                        cursor.getString(2);

                rv.setTextViewText(R.id.widget_recipe_name, measure);
            }


            return rv;

        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }


    }

}
