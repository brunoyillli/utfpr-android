package io.github.brunoyillli.organizadorreceitas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.brunoyillli.organizadorreceitas.adapter.AdapteRecipe;
import io.github.brunoyillli.organizadorreceitas.entity.Recipe;

public class RecipeRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerRecipe;
    private List<Recipe> listRecipe = new ArrayList<>();

    private AdapteRecipe adapteRecipe;

    public static void menu(AppCompatActivity activity) {
        Intent intent = new Intent(activity, RecipeRecyclerViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_recycler_view);

        recyclerRecipe = findViewById(R.id.recyclerRecipe);

        adapteRecipe = new AdapteRecipe(listRecipe);

        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext());

        recyclerRecipe.setLayoutManager(layoutManager);
        recyclerRecipe.setHasFixedSize(true);
        recyclerRecipe.addItemDecoration(
                new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerRecipe.setAdapter(adapteRecipe);

        recyclerRecipe.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerRecipe,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Recipe recipe = listRecipe.get(position);
                                Toast.makeText(getApplicationContext(),
                                        getString(R.string.receita_pressionada)
                                                + recipe.getName(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Recipe recipe = listRecipe.get(position);
                                Toast.makeText(getApplicationContext(),
                                        getString(R.string.receita_pressionada_detalhes) +
                                                recipe.getName() + " " +
                                                recipe.getCategoria() + " " +
                                                recipe.getInstructions() + " " +
                                                " " + recipe.getPreparationTime(), Toast.LENGTH_SHORT).show();
                            }


                        }
                )
        );
    }

    public void abrirSobre(View view) {
        AuditActivity.audit(this);
    }

    public void abrirNovaReceita(View view) {
        RecipeCreationActivity.novaReceita(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            Recipe recipe = (Recipe) data.getSerializableExtra(RecipeCreationActivity.RECIPE);
            listRecipe.add(recipe);
            adapteRecipe.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}