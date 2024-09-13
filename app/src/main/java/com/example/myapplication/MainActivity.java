package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private Button skipButton; // Botão "Pular"
    private EditText emailEditText;
    private EditText passwordEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telalogin);

        loginButton = findViewById(R.id.buttonlogin); // Botão de login
        registerButton = findViewById(R.id.buttonRegisterjoin); // Botão "Registrar-se"
        skipButton = findViewById(R.id.buttonskip); // Botão "Pular"
        emailEditText = findViewById(R.id.editTextEmail); // Campo de email
        passwordEditText = findViewById(R.id.editTextSenha); // Campo de senha

        // Inicializa o banco de dados
        databaseHelper = new DatabaseHelper(this);

        // Configurar o botão de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        // Configurar o botão de registrar-se para abrir a tela de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuRegistro.class);
                startActivity(intent); // Inicia a nova Activity para registrar
            }
        });

        // Configurar o botão de "Pular" para abrir o MenuActivity
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent); // Inicia o MenuActivity sem login
            }
        });

        // O botão de registrar inicialmente será invisível
        registerButton.setVisibility(View.INVISIBLE);
    }

    private void performLogin() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Verifica o login com o banco de dados
        if (databaseHelper.checkUser(email, password)) {
            // Login bem-sucedido, iniciar MenuActivity
            Toast.makeText(MainActivity.this, "Login Bem Sucedido. Divirta-se =D.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            // Login falhou, mostrar o botão "Registrar-se"
            registerButton.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Login falhou. Por favor, registre-se.", Toast.LENGTH_SHORT).show();
        }
    }
}
