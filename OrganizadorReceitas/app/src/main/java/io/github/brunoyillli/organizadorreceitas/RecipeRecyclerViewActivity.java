package io.github.brunoyillli.organizadorreceitas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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

    private int posicaoSelecionada = -1;

    private ActionMode actionMode;

    private static final String ARQUIVO_TEMAS =
            "io.github.brunoyillli.sharedpreferences.PREFERENCIAS_TEMAS";

    private static final String TEMAS = "TEMAS";

    private boolean isDarkTheme = false;

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.recipe_menu_contexto, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId() == R.id.menuItemEditar){
                alterarReceita();
                mode.finish();
                return true;
            } else if(item.getItemId() == R.id.menuItemExcluir){
                removerReceita();
                mode.finish();
                return true;
            }else{
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            recyclerRecipe.setEnabled(true);
        }
    };

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
                                if (actionMode == null){
                                    posicaoSelecionada = position;
                                    recyclerRecipe.setEnabled(false);
                                    actionMode = startActionMode(mActionModeCallBack);
                                }
                            }


                        }
                )
        );

    }

    public void abrirSobre() {
        AuditActivity.audit(this);
    }

    public void abrirNovaReceita() {
        RecipeCreationActivity.novaReceita(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            Recipe recipe = (Recipe) data.getSerializableExtra(RecipeCreationActivity.RECIPE);
            if (requestCode == RecipeCreationActivity.ALTERAR){
                listRecipe.remove(posicaoSelecionada);
                listRecipe.add(posicaoSelecionada, recipe);
                posicaoSelecionada = -1;
            }else{
                listRecipe.add(recipe);
            }
            adapteRecipe.notifyDataSetChanged();
        }
        adapteRecipe.notifyDataSetChanged();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemAdicionar){
            abrirNovaReceita();
        }else if(item.getItemId() == R.id.menuItemSobre){
            abrirSobre();
        }else if(item.getItemId() == R.id.menuItemTemaClaro){
            salvarPreferenciaTema(false);
        }else if(item.getItemId() == R.id.menuItemTemaEscuro){
            salvarPreferenciaTema(true);
        }
        return super.onOptionsItemSelected(item);
    }

    private void aplicarTema(boolean isNightModeEnabled) {
        if (isNightModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void salvarPreferenciaTema(boolean isNightModeEnabled) {
        SharedPreferences shared = getSharedPreferences(ARQUIVO_TEMAS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(TEMAS, isNightModeEnabled);
        editor.commit();
        isDarkTheme = isNightModeEnabled;

        aplicarTema(isDarkTheme);
    }

    private void lerPreferenciaTema() {
        SharedPreferences shared = getSharedPreferences(ARQUIVO_TEMAS, Context.MODE_PRIVATE);
        isDarkTheme = shared.getBoolean(TEMAS, isDarkTheme);
        aplicarTema(isDarkTheme);
    }

    private void removerReceita() {
        listRecipe.remove(posicaoSelecionada);
        adapteRecipe.notifyDataSetChanged();
    }

    private void alterarReceita() {
        Recipe recipe = listRecipe.get(posicaoSelecionada);
        RecipeCreationActivity.alterarRecipe(this, recipe);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item;
        if(isDarkTheme){
            item =menu.findItem(R.id.menuItemTemaEscuro);
        }else{
            item = menu.findItem(R.id.menuItemTemaClaro);
        }
        item.setChecked(true);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        lerPreferenciaTema();
    }
}