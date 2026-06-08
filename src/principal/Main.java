package principal;

import painel.TelaQuestionario;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Inicialização segura do Swing na Thread correta
        SwingUtilities.invokeLater(() -> {

            TelaQuestionario tela = new TelaQuestionario();
            tela.setVisible(true);
        });
    }
}