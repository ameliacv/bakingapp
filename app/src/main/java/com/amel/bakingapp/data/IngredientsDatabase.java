package com.amel.bakingapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.ExecOnCreate;
import net.simonvt.schematic.annotation.IfNotExists;
import net.simonvt.schematic.annotation.OnConfigure;
import net.simonvt.schematic.annotation.OnCreate;
import net.simonvt.schematic.annotation.OnUpgrade;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by amelia on 19/09/2017.
 */
@Database(version = IngredientsDatabase.VERSION,
        packageName = "com.amel.bakingapp.data")

public class IngredientsDatabase {

    public static final int VERSION = 1;


    public IngredientsDatabase() {

    }

    @Table(IngredientsColumns.class)
    public static final String INGREDIENTS = "ingredients";

    @OnCreate
    public static void onCreate(Context context, SQLiteDatabase db) {
    }

    @OnUpgrade
    public static void onUpgrade(Context context, SQLiteDatabase db, int oldVersion,
                                 int newVersion) {
    }

    @OnConfigure
    public static void onConfigure(SQLiteDatabase db) {
    }

    @ExecOnCreate
    public static final String EXEC_ON_CREATE = "SELECT * FROM " + INGREDIENTS;
}

