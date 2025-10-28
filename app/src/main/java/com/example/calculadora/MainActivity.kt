package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    //Primer paso
    //Definir las variables de cada operacion de nuestra calculadora con (VAL)

    val Suma =  "+"
    val Resta =  "-"
    val Multiplicacion =  "*"
    val Division =  "/"
    val Porcentaje =  "%"




    //Creamos una variable que nos permite que operacion es la que quiere ejecutar el usuario


    var operacionactual =""


    // Toda las operaciones son entre dos numero por eso creamos con la variable Var
    // Para las operaciones de primer numero y segundo numero


    var primerNumero:Double = Double.NaN
    var segundoNumero:Double = Double.NaN

    //Ejecutamos los dos elementos en este caso con nombre de tvTemp resultado temporal
    //TvResulto es la variable que nos da la respuesta en Textview

    lateinit var tvTemp: TextView
    lateinit var tvResult: TextView
    lateinit var formatodecimal: DecimalFormat



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Como anteriormente dimos con el formato decimal con la variable
        // Hacemos que formato decimal tenga un patron de decimales

        formatodecimal = DecimalFormat ("#.#########")
        tvTemp = findViewById (R.id.tvTemp)
        tvResult = findViewById (R.id.tvResult)




    }


         // Creamos una funcion para que pueda cambiar de operador
    fun cambiarOperador(b: View){
        if (tvTemp.text.isNotEmpty() && primerNumero.toString()!="NaN"){


         // Creamos condicion para que nos verifique la suma y la multiplicacion
         // Teniendo en cuenta las operaciones logaritmicas


        calcular()
        val boton: Button = b as Button
        if(boton.text.toString().trim()=="÷"){
           operacionactual = "/"
        }else if(boton.text.toString().trim()=="X"){
            operacionactual = "*"

            }else{
            operacionactual = boton.text.toString().trim()
        }
             if(tvTemp.text.toString().isEmpty()){

           }
        tvResult.text = formatodecimal.format(primerNumero) + operacionactual
        tvTemp.text = ""
    }
    }

         //Hacesmo una funcion que me haga la operacion comenzando en cambiar el primer numero con el segundo
    fun calcular(){
       try {
           if (primerNumero.toString() != "NaN"){
               if (tvTemp.text.toString().isEmpty()){
                   tvTemp.text = tvResult.text.toString()
               }
               segundoNumero = tvTemp.text.toString().toDouble()
               tvTemp.text = ""

               // Como ya tenemos los operadores entre le primer numero hacemos las operaciones

               when(operacionactual){
                   "+"-> primerNumero = (primerNumero + segundoNumero)
                   "-"-> primerNumero = (primerNumero - segundoNumero)
                   "*"-> primerNumero = (primerNumero * segundoNumero)
                   "/"-> primerNumero = (primerNumero / segundoNumero)
                   "%"-> primerNumero = (primerNumero % segundoNumero)
               }

           }else{
               primerNumero = tvTemp.text.toString().toDouble()
           }
       }catch (e: Exception){

       }

             // Como ya tenemos los operadores segumos con los botones
             // Generamos las condiciones concatenando en este caso el tvTemp con el boton

    }
    fun seleccionarNumero(b: View){
        val boton: Button = b as Button
        tvTemp.text = tvTemp.text.toString() + boton.text.toString()

    }

             // Generamos el boton para borra y igual para todas las operacione, creando dos funciones
    fun igual(b: View) {
        calcular()
        tvResult.text = formatodecimal.format(primerNumero)
        //primerNumero = Double.NaN
        operacionactual = " "

    }
           //Generamos funcion de borrar para la calculadora usan un charSequence

    fun borrar(b: View){
        calcular()
        val boton: Button = b as Button
        if(boton.text.toString().trim()=="C") {
            if (tvTemp.text.toString().isNotEmpty()) {
                var datosactuales: CharSequence = tvTemp.text.toString() as CharSequence
                tvTemp.text = datosactuales.subSequence(0, datosactuales.length - 1)

            } else {
                primerNumero = Double.NaN
                segundoNumero = Double.NaN
                tvTemp.text = ""
                tvResult.text = ""

            }



        }else if(boton.text.toString().trim()=="CA"){
            primerNumero = Double.NaN
            segundoNumero = Double.NaN
            tvTemp.text = ""
            tvResult.text = ""

        }else{
            operacionactual = boton.text.toString().trim()
        }

        }

        }

