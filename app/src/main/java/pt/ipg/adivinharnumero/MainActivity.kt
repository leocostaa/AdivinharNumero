package pt.ipg.adivinharnumero

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivity : AppCompatActivity() {
    private val random = Random()
    private var numeroAdivinhar: Int = 0
    private var jogo: Int = 0
    private var tentativas: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if ( savedInstanceState == null){
            novoJogo()
        }else{
            numeroAdivinhar = savedInstanceState.getInt(NUMERO_ADIVINHAR)
            jogo = savedInstanceState.getInt(JOGO)
            tentativas = savedInstanceState.getInt(TENTATIVAS)

            atualizaTentativas()
            atualizaJogo()
            findViewById<TextView>(R.id.textViewAjuda).text = ""
        }
        novoJogo()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NUMERO_ADIVINHAR,numeroAdivinhar)
        outState.putInt(JOGO,jogo)
        outState.putInt(TENTATIVAS,tentativas)
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
            textViewAjuda.text = getString(R.string.acertou)
            perguntaNovamente()
        }else if (numeroAdivinhar > numero){
            textViewAjuda.text =getString(R.string.numero_maior)
        }else{
            textViewAjuda.text =getString(R.string.numero_menor)
        }
        tentativas++
        atualizaTentativas()
    }
    private fun perguntaNovamente(){
        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.setTitle(R.string.acertou)
        alertDialogBuilder.setMessage(getString(R.string.jogar_novamente))

        alertDialogBuilder.setPositiveButton(
                android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which -> novoJogo() }
        )
        alertDialogBuilder.setNegativeButton(
                android.R.string.cancel,
                DialogInterface.OnClickListener { dialog, which -> finish() }
        )
        alertDialogBuilder.show()
    }
    companion object{
        const val NUMERO_ADIVINHAR = "NUM_ADIVINHAR"
        const val JOGO = "JOGO"
        const val TENTATIVAS = "TENTATIVAS"
    }

}