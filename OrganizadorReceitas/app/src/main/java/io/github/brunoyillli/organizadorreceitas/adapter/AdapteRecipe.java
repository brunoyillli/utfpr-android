package io.github.brunoyillli.organizadorreceitas.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.brunoyillli.organizadorreceitas.R;
import io.github.brunoyillli.organizadorreceitas.entity.MealTypeEnum;
import io.github.brunoyillli.organizadorreceitas.entity.Recipe;

public class AdapteRecipe extends RecyclerView.Adapter<AdapteRecipe.RecipeViewHolder> {
    private List<Recipe> listRecipe;

    public AdapteRecipe(List<Recipe> lista) {
        this.listRecipe = lista;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recipe_lista, parent, false);
        return new AdapteRecipe.RecipeViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = listRecipe.get(position);
        holder.nome.setText(recipe.getName());
        holder.ingredientes.setText(recipe.getIngredients());
        holder.modoPreparo.setText(recipe.getInstructions());
        holder.numeroPorcoes.setText(String.valueOf(recipe.getServingCount()));
        holder.tempoPreparo.setText(recipe.getPreparationTime());
        Context context = holder.tipoRefeicao.getContext();
        List<String> mealTypes = new ArrayList<>();
        for (MealTypeEnum type : recipe.getMealType()) {
            mealTypes.add(context.getString(type.getDisplayNameResId()));
        }
        String allMealTypes = TextUtils.join(", ", mealTypes);
        holder.tipoRefeicao.setText(allMealTypes);
        holder.tipoRefeicao.setText(TextUtils.join(", ", mealTypes));
        holder.categoria.setText(recipe.getCategoria());
    }

    @Override
    public int getItemCount() {
        return listRecipe.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView ingredientes;
        TextView modoPreparo;
        TextView numeroPorcoes;
        TextView tempoPreparo;
        TextView tipoRefeicao;
        TextView categoria;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNomeRecipe);
            ingredientes = itemView.findViewById(R.id.textViewIngredientes);
            modoPreparo = itemView.findViewById(R.id.textViewModoPreparo);
            numeroPorcoes = itemView.findViewById(R.id.textViewNumeroPorcoes);
            tempoPreparo = itemView.findViewById(R.id.textViewTempoPreparo);
            tipoRefeicao = itemView.findViewById(R.id.textViewTipoRefeicao);
            categoria = itemView.findViewById(R.id.textViewCategoria);
        }
    }
}
