package io.github.brunoyillli.organizadorreceitas.persistencia;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.github.brunoyillli.organizadorreceitas.entity.Recipe;

@Dao
public interface RecipeDao {

    @Insert
    long insert(Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Update
    int update(Recipe recipe);

    @Query("SELECT * FROM recipe where id = :id")
    Recipe findRecipeById(int id);

    @Query("SELECT * FROM recipe ORDER BY name ASC")
    List<Recipe> findAll();
}
