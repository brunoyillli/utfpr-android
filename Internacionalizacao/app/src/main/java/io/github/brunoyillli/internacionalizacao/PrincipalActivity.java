  package io.github.brunoyillli.internacionalizacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

  public class PrincipalActivity extends AppCompatActivity {

    private EditText editTextNome, editTextSobrenome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        editTextNome = findViewById(R.id.editTextNome);
        editTextSobrenome = findViewById(R.id.editTextSobrenome);

        setTitle(getString(R.string.novo_cadastro));
        Toast.makeText(this,
                getString(R.string.frase_boas_vindas),
                Toast.LENGTH_SHORT).show();
    }

    public void enviar(View view){
        String nome = editTextNome.getText().toString();
        String sobrenome = editTextSobrenome.getText().toString();
        Toast.makeText(this,
                getString(R.string.nome_completo) + nome + sobrenome,
                Toast.LENGTH_SHORT).show();
    }

    public void limpar(View view){
        editTextNome.setText(null);
        editTextSobrenome.setText(null);

        editTextNome.requestFocus();
    }
}