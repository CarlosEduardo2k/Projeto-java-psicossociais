package principal;

import telaInicial.TelaAcesso;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Inicialização segura do Swing na Thread correta
        SwingUtilities.invokeLater(() -> {

            TelaAcesso telaInicial = new TelaAcesso();
            telaInicial.setVisible(true);
        });
    }
}