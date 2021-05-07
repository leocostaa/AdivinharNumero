package pt.ipg.adivinharnumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private val random = Random()
    private var numeroAdivinhar: Int = 0
    private var jogo: Int = 0
    private var tentativas: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        novoJogo()
    }

    private fun novoJogo(){
        numeroAdivinhar = random.nextInt(10) + 1
        jogo++
        tentativas = 0
        atualizaTentativas()
        atualizaJogo()
    }

    private fun atualizaJogo() {
        findViewById<TextView>(R.id.textViewJogo).text = getString(R.string.jogo) + jogo

    }
    private fun atualizaTentativas() {
        findViewById<TextView>(R.id.textViewTentativas).text = getString(R.string.tentativa) + tentativas
    }

    fun adivinha(view: View) {
        val editTextNumero = findViewById<EditText>(R.id.EditTextNumero)
        val numero = editTextNumero.text.toString().toIntOrNull()
        when (numero) {
            in 1..10 -> verificaAcertou(numero!!)//let para verificar que nao é nulo e sim inteiro
            null -> editTextNumero.error = getString(R.string.numero_invalido)
            else -> editTextNumero.error = getString(R.string.numero_1e10)

        }
    }

    private fun verificaAcertou(numero: Int) {
        val textViewAjuda = findViewById<TextView>(R.id.textViewAjuda)

        if(numero == numeroAdivinhar){
            textViewAjuda.text = "Parabéns, acertou!!"
        }else if (numeroAdivinhar > numero){
            textViewAjuda.text ="O número que estou a pensar é maior"
        }else{
            textViewAjuda.text ="O número que estou a pensar é menor"
        }
        tentativas++
        atualizaTentativas()
    }
    private fun perguntaNovamente(){
        //colocar alert dialog se quer jogar novamente
    }

}