package io.github.brunoyillli.organizadorreceitas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import io.github.brunoyillli.organizadorreceitas.R;
import io.github.brunoyillli.organizadorreceitas.entity.User;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserViewHolder> {

    private List<User> listUser;

    public AdapterUser(List<User> lista) {
        this.listUser = lista;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaper_user_lista, parent, false);
        return new UserViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = listUser.get(position);
        holder.nome.setText(user.getName());
        holder.genero.setText(user.getGender());
        holder.categoriaFavorita.setText(user.getFavoriteCategory());
        holder.comidaFavorita.setText(user.getFavoriteFood());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView genero;
        TextView categoriaFavorita;

        TextView comidaFavorita;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNome);
            genero = itemView.findViewById(R.id.textViewGenero);
            categoriaFavorita = itemView.findViewById(R.id.textViewCategoriaFavorita);
            comidaFavorita = itemView.findViewById(R.id.textViewComidaFavorita);
        }
    }
}
