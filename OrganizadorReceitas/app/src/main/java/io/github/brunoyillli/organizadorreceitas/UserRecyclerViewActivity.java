package io.github.brunoyillli.organizadorreceitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.brunoyillli.organizadorreceitas.adapter.AdapterUser;
import io.github.brunoyillli.organizadorreceitas.entity.Recipe;
import io.github.brunoyillli.organizadorreceitas.entity.User;

public class UserRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerUser;
    private List<User> listUser = new ArrayList<>();

    public static void menu(AppCompatActivity activity){
        Intent intent = new Intent(activity, UserRecyclerViewActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recycler_view);

        this.criarUsuarios();
        recyclerUser = findViewById(R.id.recyclerUser);

        AdapterUser adapterUser = new AdapterUser(listUser);

        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext());

        recyclerUser.setLayoutManager(layoutManager);
        recyclerUser.setHasFixedSize(true);
        recyclerUser.addItemDecoration(
                new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerUser.setAdapter(adapterUser);

        recyclerUser.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerUser,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                User user = listUser.get(position);
                                Toast.makeText(getApplicationContext(),
                                        getString(R.string.usuario_pressionado)
                                                + user.getName() ,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                User user = listUser.get(position);
                                Toast.makeText(getApplicationContext(),
                                        getString(R.string.usuario_pressionado_detalhes) +
                                                user.getName() + " " +
                                                user.getGender() + " " +
                                                user.getFavoriteCategory() + " " +
                                         " " + user.getFavoriteFood() ,Toast.LENGTH_SHORT).show();
                            }


                        }
                )
        );
    }

    public void criarUsuarios() {
        String[] nomesUsuarios = getResources().getStringArray(R.array.nomes_usuarios);
        String[] genero = getResources().getStringArray(R.array.sexos_usuario);
        String[] categoriasFavoritas = getResources().getStringArray(R.array.categorias_receitas);
        String[] comidasFavoritas = getResources().getStringArray(R.array.comidas_favoritas);
        for (int i = 0; i < nomesUsuarios.length; i++) {
            listUser.add(new User(
                    nomesUsuarios[i],
                    genero[i],
                    comidasFavoritas[i],
                    categoriasFavoritas[i]));
        }
    }

    public void abrirSobre(View view){
        AuditActivity.audit(this);
    }

    public void abrirNovaReceita(View view){
        RecipeCreationActivity.novaReceita(this);
    }


}