package com.example.jogodavelhamquinasimples

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.jogodavelhamquinasimples.databinding.ActivityMainBinding

import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    // Vetor bidimensional que representará o tabuleiro no jogo
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "F")
    )


    // Qual jogador está jogando
    var jogadorAtual = "X"


    // Método onCreate que é chamado quando a Activity é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflando o layout usando view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Habilitando o modo edge-to-edge
        enableEdgeToEdge()
        // Definindo o conteúdo da view como o layout inflado
        setContentView(binding.root)
    }


    // Função que será chamada quando um botão for clicado
    fun buttonClick(view: View) {
        // Converte a view recebida para um botão
        val buttonSelecionado = view as Button
        // O texto do botão recebe o jogador atual
        buttonSelecionado.text = jogadorAtual
        // Define um background pro botão
        buttonSelecionado.setBackgroundColor(Color.RED)
        // Desativa o botão para que não possa ser clicado novamente
        buttonSelecionado.isEnabled = false


        // De acordo com o botão clicado, a posição da matriz recebe o jogador
        when (buttonSelecionado.id) {
            binding.buttonZero.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][2] = jogadorAtual
        }


        // Verifica se o jogo terminou
        if (!verificaJogoEncerrado(tabuleiro)) {
            // Realiza o movimento do computador se o jogo não terminou
            movimentoComputador()
        }
    }


    // Função que realiza o movimento do computador
    private fun movimentoComputador() {
        var rX: Int
        var rY: Int
        var i = 0


        // Tenta encontrar uma posição vazia no tabuleiro
        while (i < 9) {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)
            // Verifica se a posição escolhida já foi preenchida
            if (tabuleiro[rX][rY] != "X" && tabuleiro[rX][rY] != "O") {
                // Marca a posição encontrada como "O"
                tabuleiro[rX][rY] = "O"
                val posicao = rX * 3 + rY


                // Define o background e desativa o botão correspondente
                when (posicao) {
                    0 -> {
                        binding.buttonZero.setBackgroundColor(Color.BLACK)
                        binding.buttonZero.isEnabled = false
                    }
                    1 -> {
                        binding.buttonUm.setBackgroundColor(Color.BLACK)
                        binding.buttonUm.isEnabled = false
                    }
                    2 -> {

                        binding.buttonDois.setBackgroundColor(Color.BLACK)
                        binding.buttonDois.isEnabled = false
                    }
                    3 -> {

                        binding.buttonTres.setBackgroundColor(Color.BLACK)
                        binding.buttonTres.isEnabled = false
                    }
                    4 -> {
                        binding.buttonQuatro.setBackgroundColor(Color.BLACK)
                        binding.buttonQuatro.isEnabled = false
                    }
                    5 -> {
                        binding.buttonCinco.setBackgroundColor(Color.BLACK)
                        binding.buttonCinco.isEnabled = false
                    }
                    6 -> {
                        binding.buttonSeis.setBackgroundColor(Color.BLACK)
                        binding.buttonSeis.isEnabled = false
                    }
                    7 -> {
                        binding.buttonSete.setBackgroundColor(Color.BLACK)
                        binding.buttonSete.isEnabled = false
                    }
                    8 -> {
                        binding.buttonOito.setBackgroundColor(Color.BLACK)
                        binding.buttonOito.isEnabled = false
                    }

                }
                break
            }
            i++
        }


        // Verifica se o jogo terminou
        verificaJogoEncerrado(tabuleiro)
    }


    // Função que verifica se o jogo terminou
    private fun verificaJogoEncerrado(tabuleiro: Array<Array<String>>): Boolean {
        val vencedor = verificaVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
            // Exibe uma mensagem com o vencedor e reinicia a activity
            Toast.makeText(this, "Vencedor: $vencedor", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return true
        }
        return false
    }


    // Função que verifica se há vencedor ou empate
    private fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {
        // Verifica linhas e colunas
        for (i in 0 until 3) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }


        // Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }


        // Verifica se há empate
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if (valor == "X" || valor == "O") {
                    empate++
                }
            }
        }


        // Se todos os espaços estão preenchidos, houve um empate
        if (empate == 9) {
            return "Empate"
        }
        return null
    }
}




