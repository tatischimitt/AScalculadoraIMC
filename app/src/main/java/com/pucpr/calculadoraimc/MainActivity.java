package com.pucpr.calculadoraimc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText pesoEditText;
    private EditText alturaEditText;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pesoEditText = findViewById(R.id.pesoEditText);
        alturaEditText = findViewById(R.id.alturaEditText);
        calcularButton = findViewById(R.id.calcularButton);

        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });
    }

    private void calcularIMC() {
        String pesoStr = pesoEditText.getText().toString();
        String alturaStr = alturaEditText.getText().toString();

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            Toast.makeText(this, "Por favor, insira todos os dados", Toast.LENGTH_SHORT).show();
            return;
        }

        float peso = Float.parseFloat(pesoStr);
        float altura = Float.parseFloat(alturaStr);

        float imc = calcularIMC(peso, altura);
        String classificacao = getClassificacaoIMC(imc);

        exibirResultado(imc, classificacao);
    }

    private float calcularIMC(float peso, float altura) {
        return peso / (altura * altura);
    }

    private String getClassificacaoIMC(float imc) {
        if (imc < 18.5) {
            return "Magreza";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        }else if (imc < 30) {
            return "Obesidade grau I";
        }else if (imc < 35) {
            return "Obesidade grau II";
        } else {
            return "Obesidade grau III";
        }
    }

    private void exibirResultado(float imc, String classificacao) {
        TextView resultadoTextView = findViewById(R.id.resultadoTextView);
        resultadoTextView.setText("Seu IMC é: " + imc + "\nClassificação: " + classificacao);
    }
}
