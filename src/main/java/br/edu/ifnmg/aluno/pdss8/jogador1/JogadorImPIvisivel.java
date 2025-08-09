/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.ifnmg.aluno.pdss8.jogador1;
import io.github.guisso.jankenpon.AbstractPlayer;
import io.github.guisso.jankenpon.Move;
import static io.github.guisso.jankenpon.Move.PAPER;
import static io.github.guisso.jankenpon.Move.ROCK;
import static io.github.guisso.jankenpon.Move.SCISSORS;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;


/**
 *
 * @author PABLO DANIEL
 */
public class JogadorImPIvisivel extends AbstractPlayer {

    private static final String PI_DIGITS = "3141592653589793238462643383279502884197169399375105820974944592";

    private int piIndex = 0;
    private final List<Move> historicoOponente = new ArrayList<>();
    private Move ultimaMinha = null;
    private boolean perdiUltima = false;
    private Random random = new Random();

    @Override
    public String getDeveloperName() {
        return "Pablo Daniel Silva Santos - Pi Estratégico";
    }

    @Override
    public Move makeMyMove(Move opponentPreviousMove) {
        if (opponentPreviousMove != null) {
            historicoOponente.add(opponentPreviousMove);
        }

        int digitoPi = Character.getNumericValue(PI_DIGITS.charAt(piIndex));
        piIndex = (piIndex + 1) % PI_DIGITS.length();

        Move jogadaEscolhida;

        if (digitoPi < 3) {
            // Contra-ataque simples do último movimento
            if (opponentPreviousMove != null) {
                jogadaEscolhida = counterMove(opponentPreviousMove);
            } else {
                jogadaEscolhida = randomMove();
            }
        } else if (digitoPi <= 6) {
            // Contra a jogada mais frequente geral
            Move maisFrequente = maisFrequenteOponente();
            jogadaEscolhida = counterMove(maisFrequente);
        } else {
            // Aleatório
            jogadaEscolhida = randomMove();
        }

        // Se perdeu a última, evita repetir jogada
        if (perdiUltima && jogadaEscolhida == ultimaMinha) {
            List<Move> opcoes = new ArrayList<>(Arrays.asList(Move.values()));
            opcoes.remove(ultimaMinha);
            jogadaEscolhida = opcoes.get(random.nextInt(opcoes.size()));
        }

        ultimaMinha = jogadaEscolhida;
        perdiUltima = false; 

        return jogadaEscolhida;
    }

    // Método para atualizar se perdeu ou não 
    public void registrarResultado(boolean venceu, boolean empatou) {
        perdiUltima = (!venceu && !empatou);
    }

    private Move maisFrequenteOponente() {
        if (historicoOponente.isEmpty()) {
            return randomMove();
        }
        Map<Move, Integer> freq = new HashMap<>();
        for (Move m : Move.values()) freq.put(m, 0);
        for (Move m : historicoOponente) freq.put(m, freq.get(m) + 1);

        Move maisFrequente = null;
        int maxFreq = -1;
        for (Map.Entry<Move, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                maisFrequente = entry.getKey();
            }
        }
        return maisFrequente;
    }

    private Move counterMove(Move move) {
        switch (move) {
            case ROCK: return Move.PAPER;
            case PAPER: return Move.SCISSORS;
            case SCISSORS: return Move.ROCK;
            default: return Move.ROCK;
        }
    }

    private Move randomMove() {
        return Move.values()[random.nextInt(Move.values().length)];
    }
}