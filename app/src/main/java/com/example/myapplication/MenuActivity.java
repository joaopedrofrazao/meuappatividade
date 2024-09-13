package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    private LinearLayout mainLayout, sorteioContainer, imcContainer, abastecimentoContainer, outroJogoContainer;
    private TextView sorteioResult, phraseText, imcResult, resultadoAbastecimento, precoPorLitroTextView;
    private Button sorteioButton, imcButton, abastecimentoButton;
    private EditText editWeight, editHeight, editLitros;
    private RadioGroup radioGroupCombustivel;
    private ImageView image1, image2, image3, image4, image5;
    private String[] frasesNerds = {
            "Que a Força esteja com você ~ Luke Skywalker (Star Wars)",
            "Viva longa e próspera ~ Spock (Star Trek)",
            "A resistência é inútil ~ Borg (Star Trek)",
            "É uma armadilha! ~ Almirante Ackbar (Star Wars)",
            "Eu sou o Batman ~ Batman (Batman)",
            "Ao infinito e além ~ Buzz Lightyear (Toy Story)",
            "Não é um erro, é uma funcionalidade ~ Programador (Programação)",
            "O inverno está chegando ~ Ned Stark (Game of Thrones)",
            "42 ~ Douglas Adams (O Guia do Mochileiro das Galáxias)",
            "Eu juro solenemente não fazer nada de bom ~ Draco Malfoy (Harry Potter)",
            "Faça ou não faça, não há tentativa ~ Yoda (Star Wars)",
            "Qual é o sentido de ser adulto se você não pode ser infantil às vezes? ~ Doctor Who (Doctor Who)",
            "O bolo é mentira ~ Glados (Portal)",
            "Elementar, meu caro Watson ~ Sherlock Holmes (Sherlock Holmes)",
            "É perigoso ir sozinho! Leve isso. ~ Link (The Legend of Zelda)",
            "Grande Scott! ~ Doc Brown (De Volta para o Futuro)",
            "Eu acho sua falta de fé perturbadora ~ Darth Vader (Star Wars)",
            "A resistência é a voltagem dividida pela corrente ~ Física (Física)",
            "Não é você, são seus dados ~ Big Data (Big Data)",
            "Não estou discutindo, estou apenas explicando porque estou certo ~ Internet (Internet)",
            "Escolha a pílula azul ou a pílula vermelha ~ Morpheus (Matrix)"
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Referências aos elementos do layout
        mainLayout = findViewById(R.id.main_layout);
        sorteioContainer = findViewById(R.id.sorteio_container);
        imcContainer = findViewById(R.id.imc_container);
        abastecimentoContainer = findViewById(R.id.abastecimento_container);
        outroJogoContainer = findViewById(R.id.outro_jogo_container);

        sorteioResult = findViewById(R.id.sorteio_result);
        sorteioButton = findViewById(R.id.sorteio_button);
        phraseText = findViewById(R.id.phrase_text);
        imcResult = findViewById(R.id.imc_result);
        imcButton = findViewById(R.id.imc_button);
        editWeight = findViewById(R.id.edit_weight);
        editHeight = findViewById(R.id.edit_height);
        editLitros = findViewById(R.id.edit_litros);
        resultadoAbastecimento = findViewById(R.id.abastecimento_resultado);
        abastecimentoButton = findViewById(R.id.abastecimento_calcular);
        radioGroupCombustivel = findViewById(R.id.radio_group_combustivel);
        precoPorLitroTextView = findViewById(R.id.preco_por_litro);

        // Botões do menu (substituindo os ícones)
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);

        // Evento de clique para o primeiro botão (Sorteio Numérico)
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSorteio();
                changeBackgroundColor(Color.parseColor("#87CEEB")); // Cor de fundo para Sorteio
            }
        });

        // Evento de clique para o segundo botão (Gerador de Frases Nerds)
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOutroJogo();
                phraseText.setText(getRandomFrase());
                changeBackgroundColor(Color.parseColor("#C4E538")); // Cor de fundo para Frases Nerds
            }
        });

        // Evento de clique para o terceiro botão (IMC)
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImc();
                changeBackgroundColor(Color.parseColor("#FFAB91")); // Cor de fundo para IMC
            }
        });

        // Evento de clique para o quarto botão (Programa de Abastecimento)
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAbastecimento();
                atualizarPrecoPorLitro(radioGroupCombustivel.getCheckedRadioButtonId());
                changeBackgroundColor(Color.parseColor("#80CBC4")); // Cor de fundo para Abastecimento
            }
        });

        // Evento de clique para o quinto botão (ImageView com ID image5)
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exibir mensagem de copyright
                Toast.makeText(MenuActivity.this, "© João Pedro Araújo Frazão\nContato: araujofrazaojoaopedro@gmail.com", Toast.LENGTH_LONG).show();
                // Voltar para o fundo original
                changeBackgroundDrawable(R.drawable.backgroundmenu);
            }
        });

        // Evento de clique para o botão de sorteio
        sorteioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorteioResult.setText(String.valueOf(getRandomNumber()));
            }
        });

        // Evento de clique para o botão de cálculo de IMC
        imcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateIMC();
            }
        });

        // Evento de clique para o botão de abastecimento
        abastecimentoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAbastecimento();
            }
        });

        // Evento para atualizar o preço por litro quando o tipo de combustível for alterado
        radioGroupCombustivel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                atualizarPrecoPorLitro(checkedId);
            }
        });
    }

    private void showSorteio() {
        sorteioContainer.setVisibility(View.VISIBLE);
        imcContainer.setVisibility(View.GONE);
        abastecimentoContainer.setVisibility(View.GONE);
        outroJogoContainer.setVisibility(View.GONE);
    }

    private void showOutroJogo() {
        sorteioContainer.setVisibility(View.GONE);
        imcContainer.setVisibility(View.GONE);
        abastecimentoContainer.setVisibility(View.GONE);
        outroJogoContainer.setVisibility(View.VISIBLE);
    }

    private void showImc() {
        sorteioContainer.setVisibility(View.GONE);
        abastecimentoContainer.setVisibility(View.GONE);
        outroJogoContainer.setVisibility(View.GONE);
        imcContainer.setVisibility(View.VISIBLE);
    }

    private void showAbastecimento() {
        sorteioContainer.setVisibility(View.GONE);
        imcContainer.setVisibility(View.GONE);
        outroJogoContainer.setVisibility(View.GONE);
        abastecimentoContainer.setVisibility(View.VISIBLE);
    }

    private String getRandomFrase() {
        Random random = new Random();
        int index = random.nextInt(frasesNerds.length);
        return frasesNerds[index];
    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void calculateIMC() {
        try {
            double weight = Double.parseDouble(editWeight.getText().toString());
            double height = Double.parseDouble(editHeight.getText().toString());

            if (height <= 0) {
                imcResult.setText("Altura inválida");
                return;
            }

            double imc = weight / (height * height);
            imcResult.setText(String.format("IMC: %.2f", imc));
        } catch (NumberFormatException e) {
            imcResult.setText("Por favor, insira valores válidos");
        }
    }

    private void calculateAbastecimento() {
        try {
            double litros = Double.parseDouble(editLitros.getText().toString());
            if (litros <= 0) {
                resultadoAbastecimento.setText("Quantidade inválida");
                return;
            }

            // Preços reais de combustíveis para o exemplo (ajuste conforme sua região)
            double precoGasolinaComum = 5.80; // em reais por litro
            double precoGasolinaAditivada = 6.20; // em reais por litro
            double precoAlcool = 4.50; // em reais por litro

            // Map para associar IDs dos RadioButton aos preços
            Map<Integer, Double> precosPorCombustivel = new HashMap<>();
            precosPorCombustivel.put(R.id.radio_gasolina_comum, precoGasolinaComum);
            precosPorCombustivel.put(R.id.radio_gasolina_aditivada, precoGasolinaAditivada);
            precosPorCombustivel.put(R.id.radio_alcool, precoAlcool);

            // Verifica o ID do combustível selecionado
            int combustivelSelecionadoId = radioGroupCombustivel.getCheckedRadioButtonId();
            Double precoPorLitro = precosPorCombustivel.get(combustivelSelecionadoId);

            if (precoPorLitro == null) {
                resultadoAbastecimento.setText("Selecione um tipo de combustível");
                return;
            }

            // Preço total
            double precoTotal = litros * precoPorLitro;

            // Resultado
            resultadoAbastecimento.setText(String.format(
                    "Total a pagar: R$ %.2f",
                    precoTotal
            ));
        } catch (NumberFormatException e) {
            resultadoAbastecimento.setText("Por favor, insira a quantidade de litros");
        }
    }

    private void atualizarPrecoPorLitro(int combustivelSelecionadoId) {
        double precoGasolinaComum = 5.80; // em reais por litro
        double precoGasolinaAditivada = 6.20; // em reais por litro
        double precoAlcool = 4.50; // em reais por litro

        // Map para associate IDs dos RadioButton aos preços
        Map<Integer, Double> precosPorCombustivel = new HashMap<>();
        precosPorCombustivel.put(R.id.radio_gasolina_comum, precoGasolinaComum);
        precosPorCombustivel.put(R.id.radio_gasolina_aditivada, precoGasolinaAditivada);
        precosPorCombustivel.put(R.id.radio_alcool, precoAlcool);

        // Verifica o ID do combustível selecionado
        Double precoPorLitro = precosPorCombustivel.get(combustivelSelecionadoId);

        String precoTexto = "Preço por litro: R$ ";
        if (precoPorLitro != null) {
            precoTexto += String.format("%.2f", precoPorLitro);
        } else {
            precoTexto += "0.00";
        }

        // Cria um SpannableString
        SpannableString spannableString = new SpannableString(precoTexto);

        // Define a cor amarela para a parte do preço
        int startIndex = precoTexto.indexOf("R$ ") + 2; // Start após "R$ "
        int endIndex = precoTexto.length(); // Fim do texto

        // Aplica a cor amarela no preço
        spannableString.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Define o texto do TextView
        precoPorLitroTextView.setText(spannableString);
    }

    private void changeBackgroundColor(int color) {
        mainLayout.setBackgroundColor(color);
    }

    private void changeBackgroundDrawable(int drawableId) {
        mainLayout.setBackgroundResource(drawableId);
    }
}
