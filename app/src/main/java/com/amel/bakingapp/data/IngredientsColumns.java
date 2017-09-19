package com.amel.bakingapp.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

/**
 * Created by amelia on 19/09/2017.
 */

public interface IngredientsColumns {

    @DataType(INTEGER) @PrimaryKey @AutoIncrement String _ID = "_id";

    @DataType(TEXT) @NotNull String MEASURE = "measure";

    @DataType(TEXT) @NotNull String QUANTITY = "quantity";

    @DataType(TEXT) @NotNull String INGREDIENT = "ingredient";
}
