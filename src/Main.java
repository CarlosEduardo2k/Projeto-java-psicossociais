import Painel.TelaAcesso;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Inicialização segura do Swing na Thread correta
        SwingUtilities.invokeLater(() -> {
            TelaAcesso tela = new TelaAcesso();
            tela.setVisible(true); // Exibe a janela
        });
    }
}