package io.github.brunoyillli.organizadorreceitas;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import io.github.brunoyillli.organizadorreceitas.entity.Recipe;

public class RecipeCreationActivity extends AppCompatActivity {

    private EditText editTextName,editTextIngredients,editTextInstructions,
            editTextPreparationTime, editTextServingCount;
    private ImageView imageViewReceitaSelecionada;
    private ActivityResultLauncher<Intent> capturePhotoLauncher;
    private ActivityResultLauncher<Intent> selectPhotoLauncher;

    private Recipe recipe;
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_creation);
        recipe = new Recipe("", "", "", "", 0, null);
        photo = null;

        capturePhotoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Bundle extras = result.getData().getExtras();
                Bitmap capturedImage = (Bitmap) extras.get("data");
                photo = capturedImage;
                imageViewReceitaSelecionada.setImageBitmap(photo);
            }
        });

        selectPhotoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri selectedImageUri = result.getData().getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(inputStream);
                    photo = selectedImage;
                    imageViewReceitaSelecionada.setImageBitmap(photo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        editTextName = findViewById(R.id.editTextName);
        editTextIngredients = findViewById(R.id.editTextIngredients);
        editTextInstructions = findViewById(R.id.editTextInstructions);
        editTextPreparationTime = findViewById(R.id.editTextPreparationTime);
        editTextServingCount = findViewById(R.id.editTextServingCount);
        imageViewReceitaSelecionada = findViewById(R.id.imageViewReceitaSelecionada);
    }

    public void capturePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        capturePhotoLauncher.launch(intent);
    }

    public void selectPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        selectPhotoLauncher.launch(Intent.createChooser(intent, "@string/selecionar_foto"));
    }

    public void cadastrarReceita(View view) {
        if (editTextName.getText().toString().isEmpty() ||
                editTextIngredients.getText().toString().isEmpty()
                || editTextInstructions.getText().toString().isEmpty() ||
                editTextPreparationTime.getText().toString().isEmpty() ||
                editTextServingCount.getText().toString().isEmpty()) {
            Toast.makeText(this,
                    R.string.por_favor_preencha_todos_os_campos, Toast.LENGTH_SHORT).show();
            return;
        }

        recipe.setName(editTextName.getText().toString());
        recipe.setIngredients(editTextIngredients.getText().toString());
        recipe.setInstructions(editTextInstructions.getText().toString());
        recipe.setPreparationTime(editTextPreparationTime.getText().toString());
        recipe.setServingCount(Integer.parseInt(editTextServingCount.getText().toString()));
        recipe.setPhoto(this.photo);

        Toast.makeText(this,
                R.string.receitada_cadastrada_com_sucesso, Toast.LENGTH_LONG).show();
    }
}