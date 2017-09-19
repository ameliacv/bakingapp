package com.amel.bakingapp.data;

/**
 * Created by amelia on 19/09/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import net.simonvt.schematic.BuildConfig;
import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.NotifyBulkInsert;
import net.simonvt.schematic.annotation.NotifyDelete;
import net.simonvt.schematic.annotation.NotifyInsert;
import net.simonvt.schematic.annotation.NotifyUpdate;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(
        authority = IngredientsProvider.AUTHORITY,
        database = IngredientsDatabase.class,
        packageName = "net.simonvt.schematic.sample.provider"
)

public class IngredientsProvider {

    private IngredientsProvider() {
    }

    public static final String AUTHORITY = "com.amel.bakingapp.data.IngredientsProvider";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String INGREDIENTS = "ingredients";
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = IngredientsDatabase.INGREDIENTS) public static class Ingredients {

        @ContentUri(
                path = Path.INGREDIENTS,
                type = "vnd.android.cursor.dir/ingredients")
        public static final Uri CONTENT_URI = buildUri(Path.INGREDIENTS);

        @InexactContentUri(
                name = "NINGREDIENTS_ID",
                path = Path.INGREDIENTS + "/#",
                type = "vnd.android.cursor.item/ingredients",
                whereColumn = IngredientsColumns._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.INGREDIENTS, String.valueOf(id));
        }

        @NotifyInsert(paths = Path.INGREDIENTS) public static Uri[] onInsert(ContentValues values) {
            final long listId = values.getAsLong(IngredientsColumns._ID);
            return new Uri[] {
                    withId(listId)
            };
        }

        @NotifyBulkInsert(paths = Path.INGREDIENTS)
        public static Uri[] onBulkInsert(Context context, Uri uri, ContentValues[] values, long[] ids) {
            return new Uri[] {
                    uri,
            };
        }

        @NotifyUpdate(paths = Path.INGREDIENTS + "/#") public static Uri[] onUpdate(Context context,
                                                                              Uri uri, String where, String[] whereArgs) {
            final long noteId = Long.valueOf(uri.getPathSegments().get(1));
            Cursor c = context.getContentResolver().query(uri, new String[] {
                    IngredientsColumns._ID,
            }, null, null, null);
            c.moveToFirst();
            final long listId = c.getLong(c.getColumnIndex(IngredientsColumns._ID));
            c.close();

            return new Uri[] {
                    withId(noteId),
            };
        }

        @NotifyDelete(paths = Path.INGREDIENTS + "/#") public static Uri[] onDelete(Context context,
                                                                              Uri uri) {
            final long noteId = Long.valueOf(uri.getPathSegments().get(1));
            Cursor c = context.getContentResolver().query(uri, null, null, null, null);
            c.moveToFirst();
            final long listId = c.getLong(c.getColumnIndex(IngredientsColumns._ID));
            c.close();

            return new Uri[] {
                    withId(noteId),
            };
        }
    }

}
