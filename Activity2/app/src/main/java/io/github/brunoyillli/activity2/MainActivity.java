package io.github.brunoyillli.activity2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome;
    private CheckBox checkBoxPossuiCarro;
    private RadioGroup radioGroupTipo;

    public static final int PEDIR_NOTA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        checkBoxPossuiCarro = findViewById(R.id.checkBoxPossuiCarro);
        radioGroupTipo = findViewById(R.id.radioGroupTipo);
    }

    public void chamaTela2(boolean pedirNota){
        Intent intent = new Intent(this,MostrarDadosActivity.class);
        String nome = editTextNome.getText().toString();
        if(!nome.equals("")){
            intent.putExtra(MostrarDadosActivity.NOME, nome);
        }
        intent.putExtra(MostrarDadosActivity.POSSUI_CARRO, checkBoxPossuiCarro.isChecked());
        int id = radioGroupTipo.getCheckedRadioButtonId();
        if(id != -1){
            intent.putExtra(MostrarDadosActivity.TIPO,id);
        }

        if(pedirNota){
            intent.putExtra(MostrarDadosActivity.MODO, PEDIR_NOTA);

            startActivityForResult(intent, PEDIR_NOTA);
        }else{
            startActivity(intent);
        }

    }

    public void enviarPedindoNota(View view){
        chamaTela2(true);
    }

    public void enviarSemPedirNota(View view){
        chamaTela2(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PEDIR_NOTA && resultCode == Activity.RESULT_OK ){
            Bundle budle = data.getExtras();
            if(budle != null){
                int nota = budle.getInt(MostrarDadosActivity.NOTA);
                Toast.makeText(this, getString(R.string.nota) + nota, Toast.LENGTH_LONG).show();
            }
        }
            super.onActivityResult(requestCode, resultCode, data);
    }
}