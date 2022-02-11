package imperial.heriberto.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lbR: TextView = findViewById(R.id.tvResultado)
        var lbE: TextView = findViewById(R.id.tvEstado)

        var txtE: EditText = findViewById(R.id.etEstatura)
        var txP: EditText = findViewById(R.id.etPeso)
        var btC: Button = findViewById(R.id.btnCalcular)

        btC.setOnClickListener(){
            if(!txtE.text.isBlank() || !txP.text.isBlank()){

                val imcNum = calculadoraIMC(txtE.toString().toDouble(),txP.text.toString().toDouble())
                lbR.setText(imcNum.toString())

                val estado = this.obtenEstado(imcNum)
                lbE.setText(estado)

                when(estado){
                    "Bajo peso" -> lbE.setBackgroundResource(R.color.colorBrown)
                    "Saludable" -> lbE.setBackgroundResource(R.color.colorGreen)
                    "Sobrepeso" -> lbE.setBackgroundResource(R.color.colorGreenish)
                    "Obesidad de grado 1" -> lbE.setBackgroundResource(R.color.colorYellow)
                    "Obesidad de grado 2" -> lbE.setBackgroundResource(R.color.colorOrange)
                    "Obesidad de grado 3" -> lbE.setBackgroundResource(R.color.colorRed)
                }
            }
        }
    }
    fun calculadoraIMC(alt: Double, peso:Double):Double{
        val imc: Double = (peso/(Math.pow(alt,2.0)))
        return imc
    }

    fun obtenEstado(imc:Double):String{
        when{
            imc <18.5 -> return "Bajo peso"
            imc >= 18.5 && imc <= 24.9 -> return "Saludable"
            imc >= 24.9 && imc <= 29.9 -> return "Sobrepeso"
            imc >= 29.9 && imc <= 34.9 -> return "Obesidad grado 1"
            imc >= 34.9 && imc <= 40 -> return "Obesidad grado 2"
            imc >= 40  -> return "Obesidad grado 3"
        }

        return "Error"
    }
}