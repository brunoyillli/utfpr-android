package io.github.brunoyillli.organizadorreceitas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.github.brunoyillli.organizadorreceitas.entity.MealTypeEnum;
import io.github.brunoyillli.organizadorreceitas.entity.Recipe;

public class RecipeCreationActivity extends AppCompatActivity {

    private EditText editTextName, editTextIngredients, editTextInstructions,
            editTextPreparationTime, editTextServingCount;
    private ImageView imageViewReceitaSelecionada;

    private RadioGroup radioGroupReceitaIsActive;
    private ActivityResultLauncher<Intent> capturePhotoLauncher;
    private ActivityResultLauncher<Intent> selectPhotoLauncher;

    private Recipe recipe;
    private Bitmap photo;

    private Spinner spinnerCategorias;

    private String categoriaSelecionada;
    private CheckBox checkBoxCafeManha, checkBoxAlmoco, checkBoxCafeTarde, checkBoxJanta;

    private RadioButton radioButtonAtivada, radioButtonDesativada;
    private static final int RADIO_BUTTON_ATIVADA = R.id.radioButtonAtivada;
    private static final int RADIO_BUTTON_DESATIVADA = R.id.radioButtonDesativada;

    public static final String MODO = "MODO";
    public static final String RECIPE = "RECIPE";

    public static final int NOVO = 1;
    public static final int ALTERAR = 2;

    private int modo;

    public static void novaReceita(AppCompatActivity activity) {
        Intent intent = new Intent(activity, RecipeCreationActivity.class);
        intent.putExtra(MODO, NOVO);
        activity.startActivityForResult(intent, NOVO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_creation);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recipe = new Recipe("", "", "", "",
                0, null, new ArrayList<>(), true, "");
        photo = null;

        editTextName = findViewById(R.id.editTextName);
        editTextIngredients = findViewById(R.id.editTextIngredients);
        editTextInstructions = findViewById(R.id.editTextInstructions);
        editTextPreparationTime = findViewById(R.id.editTextPreparationTime);
        editTextServingCount = findViewById(R.id.editTextServingCount);
        imageViewReceitaSelecionada = findViewById(R.id.imageViewReceitaSelecionada);
        radioGroupReceitaIsActive = findViewById(R.id.radioGroupReceitaIsActive);
        radioButtonAtivada = findViewById(R.id.radioButtonAtivada);
        radioButtonDesativada = findViewById(R.id.radioButtonDesativada);
        checkBoxCafeManha = findViewById(R.id.checkBoxCafeManha);
        checkBoxAlmoco = findViewById(R.id.checkBoxAlmoco);
        checkBoxCafeTarde = findViewById(R.id.checkBoxCafeTarde);
        checkBoxJanta = findViewById(R.id.checkBoxJanta);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);

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

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSelecionada = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            modo = bundle.getInt(MODO, NOVO);

            if (modo == NOVO) {
                setTitle(getString(R.string.nova_receita));
            } else {
                Recipe recipe = (Recipe) bundle.getSerializable(RecipeCreationActivity.RECIPE, Recipe.class);
                popularCamposEdicao(recipe);
            }
        }

    }

    private void popularCamposEdicao(Recipe recipe) {

        editTextName.setText(recipe.getName());
        editTextIngredients.setText(recipe.getIngredients());
        editTextInstructions.setText(recipe.getInstructions());
        editTextPreparationTime.setText(recipe.getPreparationTime());
        editTextServingCount.setText(String.valueOf(recipe.getServingCount()));
        if (recipe.isActive()) {
            radioButtonAtivada.setChecked(true);
        } else {
            radioButtonDesativada.setChecked(true);
        }
        for (MealTypeEnum meal : recipe.getMealType()) {
            if(meal.equals(MealTypeEnum.CAFE_DA_MANHA)){
                checkBoxCafeManha.setChecked(true);
            } else if (meal.equals(MealTypeEnum.ALMOCO)) {
                checkBoxAlmoco.setChecked(true);
            } else if (meal.equals(MealTypeEnum.CAFE_DA_TARDE)) {
                checkBoxCafeTarde.setChecked(true);
            } else if (meal.equals(MealTypeEnum.JANTA)) {
                checkBoxJanta.setChecked(true);
            }
        }

        String categoria = recipe.getCategoria();
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinnerCategorias.getAdapter();
        int position = adapter.getPosition(categoria);

        if (position != -1) {
            spinnerCategorias.setSelection(position);
        }

        setTitle(getString(R.string.alterar_receita));
    }

    public void capturePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        capturePhotoLauncher.launch(intent);
    }

    public void selectPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        selectPhotoLauncher.launch(Intent.createChooser(intent,
                getString(R.string.selecionar_foto)));
    }

    public void cadastrarReceita() {
        int checkedRadioButtonId = radioGroupReceitaIsActive.getCheckedRadioButtonId();
        List<MealTypeEnum> listTipoRefeicao = new ArrayList<>();
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

        if (checkedRadioButtonId == RADIO_BUTTON_ATIVADA) {
            recipe.setActive(true);
        } else if (checkedRadioButtonId == RADIO_BUTTON_DESATIVADA) {
            recipe.setActive(false);
        }

        if (checkBoxCafeManha.isChecked()) {
            listTipoRefeicao.add(MealTypeEnum.CAFE_DA_MANHA);
        }
        if (checkBoxAlmoco.isChecked()) {
            listTipoRefeicao.add(MealTypeEnum.ALMOCO);
        }
        if (checkBoxCafeTarde.isChecked()) {
            listTipoRefeicao.add(MealTypeEnum.CAFE_DA_TARDE);
        }
        if (checkBoxJanta.isChecked()) {
            listTipoRefeicao.add(MealTypeEnum.JANTA);
        }
        recipe.setMealType(listTipoRefeicao);

        recipe.setCategoria(categoriaSelecionada);

        Toast.makeText(this,
                R.string.receitada_cadastrada_com_sucesso, Toast.LENGTH_LONG).show();

        gerarIntentRecipe(recipe);
    }

    private void gerarIntentRecipe(Recipe recipe) {
        Intent intent = new Intent();
        intent.putExtra(RECIPE, recipe);

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    public void limpar() {
        editTextName.setText("");
        editTextIngredients.setText("");
        editTextInstructions.setText("");
        editTextServingCount.setText("");
        editTextPreparationTime.setText("");
        radioGroupReceitaIsActive.clearCheck();
        photo = null;
        imageViewReceitaSelecionada.setImageResource(R.drawable.ic_receita_inserir);
        editTextName.requestFocus();
        checkBoxCafeManha.setChecked(false);
        checkBoxAlmoco.setChecked(false);
        checkBoxCafeTarde.setChecked(false);
        checkBoxJanta.setChecked(false);
        spinnerCategorias.setSelection(0);
        Toast.makeText(this,
                "Campos limpos para novo cadastro de receita!",
                Toast.LENGTH_LONG).show();
    }

    public void voltarMenu() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public static void alterarRecipe(AppCompatActivity activity, Recipe recipe) {
        Intent intent = new Intent(activity, RecipeCreationActivity.class);
        intent.putExtra(MODO, ALTERAR);
        intent.putExtra(RECIPE, recipe);

        activity.startActivityForResult(intent, ALTERAR);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemSalvarReceita){
            cadastrarReceita();
        }else if(item.getItemId() == R.id.menuItemLimparReceita){
            limpar();
        }else if(item.getItemId() == android.R.id.home){
            voltarMenu();
        }
        return super.onOptionsItemSelected(item);
    }
}