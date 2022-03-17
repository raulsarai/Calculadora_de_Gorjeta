package br.com.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    EditText valorConta;
    SeekBar seekBarPorcentagem;
    TextView textViewGorjeta, textViewTotal, textPorcentagem;

    private double porcentagem = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorConta = findViewById(R.id.valorConta);
        seekBarPorcentagem = findViewById(R.id.seekBarPorcentagem);
        textViewGorjeta = findViewById(R.id.textGorjeta);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textViewTotal = findViewById(R.id.textTotal);

        seekBarPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i;
                textPorcentagem.setText(Math.round(porcentagem) + " %");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular() {
        String valorRecuperado = valorConta.getText().toString();
        if (valorRecuperado == null || valorRecuperado.equals("")) {
            Toast.makeText(getApplicationContext(), "Digite um valor primeiro", Toast.LENGTH_LONG).show();
        } else {
            double valorDigitado = Double.parseDouble(valorRecuperado);
            double valorGorjeta = (valorDigitado * porcentagem) / 100;
            double valorTotal = valorDigitado + valorGorjeta;
            textViewGorjeta.setText("R$" + valorGorjeta);
            textViewTotal.setText("R$" + valorTotal);
        }
    }
}