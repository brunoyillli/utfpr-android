package io.github.brunoyillli.menuopcoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layoutPrincipal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemSalvar){
            mostrarMensagem(getString(R.string.salvar) + getString(R.string.foi_clicado));
        }else if(item.getItemId() == R.id.menuItemExcluir){
            mostrarMensagem(getString(R.string.excluir) + getString(R.string.foi_clicado));
        }else if(item.getItemId() == R.id.menuItemAzul){
            item.setChecked(true);
            layout.setBackgroundColor(Color.BLUE);
        }else if(item.getItemId() == R.id.menuItemVermeho){
            item.setChecked(true);
            layout.setBackgroundColor(Color.RED);
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarMensagem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    public void usarDados(MenuItem item){
        item.setChecked(!item.isChecked());
    }
}