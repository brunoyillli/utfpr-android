package io.github.brunoyillli.activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abreSiteUTFPR(View view){
        abreSite("http://www.utfpr.edu.br");
    }

    private void abreSite(String endereco){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(endereco));

        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivity(intent);
        }else{
            Toast.makeText(this,
                    R.string.nenhum_aplicativo_instalado_pode_atender,
                    Toast.LENGTH_LONG).show();
        }

    }
}