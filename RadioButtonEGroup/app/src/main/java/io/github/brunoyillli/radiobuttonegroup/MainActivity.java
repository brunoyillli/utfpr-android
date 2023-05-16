package io.github.brunoyillli.radiobuttonegroup;

import static io.github.brunoyillli.radiobuttonegroup.R.id.radioButtonJava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupLinguagens;
    private static final int RADIO_BUTTON_JAVA = R.id.radioButtonJava;
    private static final int RADIO_BUTTON_PASCAL = R.id.radioButtonPascal;
    private static final int RADIO_BUTTON_COBOL = R.id.radioButtonCobol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroupLinguagens = findViewById(R.id.radioGroupLinguagens);
    }

    public void mostrarSelecionados(View view) {
        String mensagem = "";
        int checkedRadioButtonId = radioGroupLinguagens.getCheckedRadioButtonId();

        if (checkedRadioButtonId == RADIO_BUTTON_JAVA) {
            mensagem = getString(R.string.java) + getString(R.string.foi_selecionado);
        } else if (checkedRadioButtonId == RADIO_BUTTON_PASCAL) {
            mensagem = getString(R.string.pascal) + getString(R.string.foi_selecionado);
        } else if (checkedRadioButtonId == RADIO_BUTTON_COBOL) {
            mensagem = getString(R.string.cobol) + getString(R.string.foi_selecionado);
        } else {
            mensagem = getString(R.string.ninguem_foi_selecionado);
        }

        Toast.makeText(
                this,
                mensagem,
                Toast.LENGTH_SHORT).show();
    }

    public void desmarcar(View view){
        radioGroupLinguagens.clearCheck();
    }
}