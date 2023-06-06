package io.github.brunoyillli.menucontextoflutuante;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNome;
    private EditText editTextNome;
    private ImageButton imageButton1, imageButton2;

    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;

    private int posicaoAlteracao = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNome = findViewById(R.id.listViewNomes);
        editTextNome = findViewById(R.id.editTextNome);
        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);

        imageButton2.setVisibility(View.INVISIBLE);

        popularLista();

        registerForContextMenu(listViewNome);
    }

    private void popularLista() {
        lista = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listViewNome.setAdapter(adapter);
    }

    public void adicionar(View view){
        String frase = editTextNome.getText().toString();
        if(frase.isEmpty()){
            return;
        }

        editTextNome.setText(null);
        if(posicaoAlteracao == -1){
            lista.add(frase);
        }else{
            lista.remove(posicaoAlteracao);
            lista.add(posicaoAlteracao, frase);
            imageButton1.setImageResource(android.R.drawable.ic_input_add);
            imageButton2.setVisibility(View.INVISIBLE);
            posicaoAlteracao = - 1;
            listViewNome.setEnabled(true);
        }
        Collections.sort(lista);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.principal_menu_contexto,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info;

        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.menuItemAlterar){
            alterar(info.position);
        }else if(item.getItemId() == R.id.menuItemExcluir){
            excluir(info.position);
        }
        return super.onContextItemSelected(item);
    }

    private void alterar(int position) {
        String frase = lista.get(position);
        editTextNome.setText(frase);
        editTextNome.setSelection(editTextNome.getText().length());

        imageButton1.setImageResource(android.R.drawable.ic_menu_save);
        imageButton2.setVisibility(View.VISIBLE);

        listViewNome.setEnabled(false);

        posicaoAlteracao = position;
    }

    public void cancelar(View view){
        editTextNome.setText(null);
        imageButton1.setImageResource(android.R.drawable.ic_input_add);
        imageButton2.setVisibility(View.INVISIBLE);
        listViewNome.setEnabled(true);
        posicaoAlteracao = -1;
    }

    private void excluir(int position) {
        lista.remove(position);
        adapter.notifyDataSetChanged();
    }
}