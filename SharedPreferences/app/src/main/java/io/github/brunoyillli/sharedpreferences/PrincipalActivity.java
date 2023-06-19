package io.github.brunoyillli.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PrincipalActivity extends AppCompatActivity {

    private static final String ARQUIVO =
            "io.github.brunoyillli.sharedpreferences.PREFERENCIAS_CORES";
    private static final String COR = "COR";
    private ConstraintLayout layout;

    private int opcao = Color.BLUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layoutPrincipal);
        lerPreferenciaCor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        item.setChecked(true);

        if (item.getItemId() == R.id.menuItemAmarelo) {
            salvarPreferenciaCor(Color.YELLOW);
            return true;
        } else if (item.getItemId() == R.id.menuItemAzul) {
            salvarPreferenciaCor(Color.BLUE);
            return true;
        } else if (item.getItemId() == R.id.menuItemVermelho) {
            salvarPreferenciaCor(Color.RED);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void lerPreferenciaCor() {
        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);
        opcao = shared.getInt(COR, opcao);
        mudarCorFundo();
    }

    private void mudarCorFundo() {
        layout.setBackgroundColor(opcao);
    }

    private void salvarPreferenciaCor(int novoValor) {
        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt(COR, novoValor);
        editor.commit();
        opcao = novoValor;

        mudarCorFundo();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item;

        if(opcao == Color.YELLOW){
            item = menu.findItem(R.id.menuItemAmarelo);
        }else if(opcao == Color.BLUE){
            item = menu.findItem(R.id.menuItemAzul);
        }else if(opcao == Color.RED){
            item = menu.findItem(R.id.menuItemVermelho);
        }else{
            return false;
        }
        item.setChecked(true);
        return true;
    }
}