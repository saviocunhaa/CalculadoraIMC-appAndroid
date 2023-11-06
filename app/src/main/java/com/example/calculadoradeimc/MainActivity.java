package com.example.calculadoradeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.calculadoradeimc.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    binding.btCalcularImc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String peso = binding.editPeso.getText().toString();
            String altura = binding.editAltura.getText().toString();

            if (peso.isEmpty()){
                binding.editPeso.setError("Informe o Peso!");
            } else if (altura.isEmpty()) {
                binding.editAltura.setError("Informe a Altura!");
            }else {
                calcularImc();
                tabelaImc();
            }
        }
    });

    binding.btLimpar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            binding.editAltura.setText("");
            binding.editPeso.setText("");
            binding.txtResultado.setText("");
            binding.txtTitulo.setText("");
            binding.txtTabelaImc.setText("");
        }
    });

    }

    private void tabelaImc(){
        binding.txtTitulo.setText("TABELA IMC");
        binding.txtTabelaImc.setText("Menor que 18.5 - Abaixo do peso" + "\n" +
                "Entre 18.5 e 24.9 - Peso normal" + "\n" +
                "Entre 25.0 e 29.9 - Pré-obesidade" + "\n" +
                "Entre 30.0 e 34.9 - Obesidade Grau 1" + "\n" +
                "Entre 35.0 e 39.9 - Obesidade Grau 2" + "\n" +
                "Acima de 40 - Obesidade Grau 3");;
    }


    @SuppressLint("DefaultLocale")
    private void calcularImc(){
        float peso = Float.parseFloat(binding.editPeso.getText().toString().replace(",","."));
        float altura = Float.parseFloat(binding.editAltura.getText().toString().replace(",","."));
        float imc = peso / (altura*altura);

        //
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        if (imc < 18.5){
            binding.txtResultado.setText(String.format("Seu IMC é de: " + decimalFormat.format(imc) + " \n Peso Baixo"));
            binding.txtResultado.setTextColor(getColor(R.color.orange));
        }else if(imc <= 24.9){
            binding.txtResultado.setText(String.format("Seu IMC é de: " + decimalFormat.format(imc) + " \n Peso  Normal"));
            binding.txtResultado.setTextColor(getColor(R.color.green));
        }else if (imc <= 29.9 ){
            binding.txtResultado.setText(String.format("Seu IMC é de: " + decimalFormat.format(imc) + " \n Pré-Obesidade"));
            binding.txtResultado.setTextColor(getColor(R.color.orange));
        }else if (imc <= 34.9){
            binding.txtResultado.setText(String.format("Seu IMC é de: " + decimalFormat.format(imc) + " \n Obesidade Grau 1"));
            binding.txtResultado.setTextColor(getColor(R.color.red));
        }else if (imc <= 39.9){
            binding.txtResultado.setText(String.format("Seu IMC é de: " + decimalFormat.format(imc) + " \n Obesidade Grau 2"));
            binding.txtResultado.setTextColor(getColor(R.color.red));
        }else{
            binding.txtResultado.setText(String.format("Seu IMC é de: "+ decimalFormat.format(imc) + " \n Obesidade Grau 3"));
            binding.txtResultado.setTextColor(getColor(R.color.red));
        }
    }
}