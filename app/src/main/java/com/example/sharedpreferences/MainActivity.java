package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    //Variaveis
    private Button buttonSalvarNome;
    private TextInputEditText editNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvarNome = findViewById(R.id.id_botaoSalvar);
        editNome = findViewById(R.id.id_digiteSeuNome);
        textResultado = findViewById(R.id.id_txtResultado);

        //Passando ação ao botão Salvar
        buttonSalvarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0); //Parametro - Mode = 0 modo privado (Só nosso app consegue ler)
                SharedPreferences.Editor editor = preferences.edit(); //Editar nosso arquivo de preferencias

                //Validar nome
                if(editNome.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_LONG).show();
                } else {
                    String nome = editNome.getText().toString();
                    editor.putString("nome",nome); //"Utilizando a palavra nome como um identificador"
                    editor.commit(); //Salvando nome
                    textResultado.setText("Olá " + nome);
                }
            }
        });

        //Recuperando nome
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

        if(preferences.contains("nome")) {
            String nome = preferences.getString("nome", "não encontramos esse usuário");
            textResultado.setText("Olá " + nome);
        } else {
            textResultado.setText("não encontramos esse usuário");
        }
    }
}
