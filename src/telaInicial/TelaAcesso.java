package telaInicial;

import javax.swing.*;
import java.awt.*;

public class TelaAcesso extends JFrame {

    public TelaAcesso() {
        // 1. Configurações básicas da Janela (JFrame)
        setTitle("Sistema de Análise Psicossocial - Login");
        setSize(500, 450); // Janelas de login costumam ser menores e centralizadas
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela do usuário
        setLayout(new BorderLayout());

        // 2. Criando a Barra de Topo (Header) idêntica à TelaAdmin
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBackground(new Color(25, 60, 122)); // O mesmo azul escuro corporativo
        painelTopo.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        JLabel lblTituloSistema = new JLabel("Bem-vindo ao Portal de Acesso");
        lblTituloSistema.setFont(new Font("Arial", Font.BOLD, 16));
        lblTituloSistema.setForeground(Color.WHITE);
        lblTituloSistema.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto no topo

        painelTopo.add(lblTituloSistema, BorderLayout.CENTER);

        // 3. Criando o Sistema de Abas (JTabbedPane)
        JTabbedPane abasLogin = new JTabbedPane();
        abasLogin.setFont(new Font("Arial", Font.BOLD, 13));

        // Instanciando os painéis que você já criou
        PainelUsuario abaUsuario = new PainelUsuario();
        PainelAdmin abaAdmin = new PainelAdmin();

        // Adicionando os painéis como abas na janela de acesso
        abasLogin.addTab("Acesso Usuário", abaUsuario);
        abasLogin.addTab("Acesso Admin", abaAdmin);

        // 4. Juntando Tudo na Janela Principal
        add(painelTopo, BorderLayout.NORTH);  // Header no topo
        add(abasLogin, BorderLayout.CENTER);  // Abas de login ocupando o centro
    }
}