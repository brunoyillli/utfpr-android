package io.github.brunoyillli.organizadorreceitas.persistencia;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import io.github.brunoyillli.organizadorreceitas.entity.MealTypeConverter;
import io.github.brunoyillli.organizadorreceitas.entity.Recipe;

@Database(entities = {Recipe.class}, version = 1,exportSchema = false)
@TypeConverters(MealTypeConverter.class)
public abstract class OrganizadorReceitasDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();

    private static  OrganizadorReceitasDatabase instance;

    public static OrganizadorReceitasDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (OrganizadorReceitasDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context, OrganizadorReceitasDatabase.class,
                            "organizadorReceitas.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

}
