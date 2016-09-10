package com.jose.calculadora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String texto = "";
    private String operacao = "";
    private String valor1 = "";
    private String valor2 = "";
    private Double resultado = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setEventoBotaoNumero(R.id.button, "1");
        setEventoBotaoNumero(R.id.button2, "2");
        setEventoBotaoNumero(R.id.button3, "3");
        setEventoBotaoNumero(R.id.button4, "4");
        setEventoBotaoNumero(R.id.button5, "5");
        setEventoBotaoNumero(R.id.button6, "6");
        setEventoBotaoNumero(R.id.button7, "7");
        setEventoBotaoNumero(R.id.button8, "8");
        setEventoBotaoNumero(R.id.button9, "9");
        setEventoBotaoNumero(R.id.button0, "0");

        setEventoBotaoOperacao(R.id.buttonMais, "adicao");
        setEventoBotaoOperacao(R.id.buttonMenos, "subtracao");
        setEventoBotaoOperacao(R.id.buttonVezes, "multiplicacao");
        setEventoBotaoOperacao(R.id.buttonDividir, "divisao");
        setEventoBotaoOperacao(R.id.buttonResultado, "resultado");
    }

    private void setEventoBotaoNumero(int id, final String valor) {
        Button button = ((Button) findViewById(id));
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (resultado != null) {
                    resultado = null;
                }

                texto += valor;

                EditText editText = ((EditText) findViewById(R.id.editText));
                editText.setText(texto);
            }
        });
    }

    private void setEventoBotaoOperacao(int id, final String oper) {
        Button button = ((Button) findViewById(id));
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText editText = ((EditText) findViewById(R.id.editText));
                editText.setText("");

                if (resultado != null && valor1.isEmpty()) {
                    valor1 = resultado.toString();
                }

                if (valor1.isEmpty()) {
                    valor1 = texto;
                    texto = "";
                } else {
                    valor2 = texto;
                    texto = "";
                }

                switch (oper) {
                    case "resultado":
                        imprimeResultado();
                        break;
                    default:
                        operacao = oper;
                }
            }
        });
    }

    private void imprimeResultado() {
        if (valor1.isEmpty() && valor2.isEmpty()) {
            resultado = 0.0;
        } else if (valor2.isEmpty()) {
            resultado = Double.parseDouble(valor1);
        }

        switch (operacao) {
            case "adicao":
                resultado = Double.parseDouble(valor1) + Double.parseDouble(valor2);
                break;
            case "subtracao":
                resultado = Double.parseDouble(valor1) - Double.parseDouble(valor2);
                break;
            case "multiplicacao":
                resultado = Double.parseDouble(valor1) * Double.parseDouble(valor2);
                break;
            case "divisao":
                resultado = Double.parseDouble(valor1) / Double.parseDouble(valor2);
                break;
        }
        valor1 = "";
        valor2 = "";

        EditText editText = ((EditText) findViewById(R.id.editText));
        editText.setText(resultado.toString());
    }
}
