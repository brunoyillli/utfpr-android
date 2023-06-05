package io.github.brunoyillli.organizadorreceitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AuditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit);
    }
    public static void audit(AppCompatActivity activity){
        Intent intent = new Intent(activity, AuditActivity.class);
        activity.startActivity(intent);
    }

    public void voltarMenu(View view){
        UserRecyclerViewActivity.menu(this);
    }
}