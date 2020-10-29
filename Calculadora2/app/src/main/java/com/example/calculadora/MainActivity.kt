package com.example.calculadora

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        numero0.setOnClickListener{AcrescentarExpressao("0", true)}
        numero1.setOnClickListener{AcrescentarExpressao("1", true)}
        numero2.setOnClickListener{AcrescentarExpressao("2", true)}
        numero3.setOnClickListener{AcrescentarExpressao("3", true)}
        numero4.setOnClickListener{AcrescentarExpressao("4", true)}
        numero5.setOnClickListener{AcrescentarExpressao("5", true)}
        numero6.setOnClickListener{AcrescentarExpressao("6", true)}
        numero7.setOnClickListener{AcrescentarExpressao("7", true)}
        numero8.setOnClickListener{AcrescentarExpressao("8", true)}
        numero9.setOnClickListener{AcrescentarExpressao("9", true)}


        //Operadores
        adicao.setOnClickListener{
            if(expression.text.last().toString() != "+")
                AcrescentarExpressao("+", false)
        }

        subtracao.setOnClickListener{
            if(expression.text.last().toString() != "-")
            AcrescentarExpressao("-", false)
        }

        multiplicacao.setOnClickListener{
            if(expression.text.last().toString() != "*")
                AcrescentarExpressao("*", false)}

        divisao.setOnClickListener{
            AcrescentarExpressao("/", false)}

        raizquadrada.setOnClickListener{

           val numero = expression.text.toString()

            result.text = sqrt(numero.toDouble()).toString()
        }


        limpar.setOnClickListener{
            expression.text = ""
            result.text = ""
        }

        corrigir.setOnClickListener{
            val string = expression.text.toString()

            if(string.isNotBlank()){
                    expression.text = string.substring(0, string.length - 1)
            }
            result.text = ""
        }

        ponto.setOnClickListener{
            if(expression.text.last().toString() != "."){
                AcrescentarExpressao(".", true)
            }
        }

        enter.setOnClickListener{
            try {
                val expressao = ExpressionBuilder(expression.text.toString()).build()

                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if (resultado == longResult.toDouble())

                    result.text = longResult.toString()
                else
                    result.text = resultado.toString()

            }catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show();

                Log.d("EXCEPTION", "Message: ${e.message}")
            }
        }
    }

    fun AcrescentarExpressao(string: String, limpar_dados: Boolean){
        if(result.text.isNotEmpty()){
            expression.text = ""
        }
        if (limpar_dados) {
            result.text = ""
            expression.append(string)
        } else {
            expression.append(result.text)
            expression.append(string)
            result.text = ""
        }
    }


}




