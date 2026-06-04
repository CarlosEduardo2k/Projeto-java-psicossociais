import Painel.TelaAcesso;
import javax.swing.SwingUtilities;
import Painel.TelaQuestionario;

public class Main {
    public static void main(String[] args) {
        // Inicialização segura do Swing na Thread correta
        SwingUtilities.invokeLater(() -> {
            TelaQuestionario tela = new TelaQuestionario();
            tela.setVisible(true); // Exibe a janela
        });
    }
}