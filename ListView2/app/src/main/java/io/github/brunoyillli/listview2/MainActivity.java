package io.github.brunoyillli.listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPessoas = findViewById(R.id.listViewPessoas);
        popularList();
    }

    private void popularList() {
        String[] nomes = getResources().getStringArray(R.array.nomes);
        int[] idades = getResources().getIntArray(R.array.idades);

        ArrayList<Pessoa> pessoas = new ArrayList<>();
        for(int count = 0; count < nomes.length;  count++){
            pessoas.add(new Pessoa(nomes[count], idades[count]));
        }

        ArrayAdapter<Pessoa> adapter
                = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);

        listViewPessoas.setAdapter(adapter);
    }
}