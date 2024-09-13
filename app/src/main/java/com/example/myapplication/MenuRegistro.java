package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MenuRegistro extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button voltarButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        emailEditText = findViewById(R.id.editTextEmail); // Campo de email
        passwordEditText = findViewById(R.id.editTextSenha); // Campo de senha
        registerButton = findViewById(R.id.buttonRegister); // Botão "Registrar"
        voltarButton = findViewById(R.id.butback); // Botão "Voltar"

        // Inicializa o banco de dados
        databaseHelper = new DatabaseHelper(this);

        // Configurar o botão de registrar
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegister();
            }
        });

        // Configurar o botão de voltar para retornar à tela de login
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuRegistro.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void performRegister() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Verifica se os campos estão preenchidos
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MenuRegistro.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
        } else {
            // Insere o novo usuário no banco de dados
            boolean isInserted = databaseHelper.insertUser(email, password);
            if (isInserted) {
                Toast.makeText(MenuRegistro.this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenuRegistro.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MenuRegistro.this, "Falha no registro. Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
