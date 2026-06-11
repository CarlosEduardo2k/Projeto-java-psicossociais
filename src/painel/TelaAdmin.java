package painel;

import telaInicial.TelaAcesso;

import javax.swing.*;
import java.awt.*;

public class TelaAdmin extends JFrame {

    public TelaAdmin() {
        // Configurações básicas da janela principal
        setTitle("Área Administrativa - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // ==================================================
        // 1. BARRA DE TOPO (HEADER)
        // ==================================================
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBackground(new Color(25, 70, 130)); // Azul padrão do seu questionário
        painelTopo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding interno

        JLabel lblTituloSistema = new JLabel("Gestão - Análise Psicossocial (NR-01)");
        lblTituloSistema.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloSistema.setForeground(Color.WHITE);

        // Botão Sair com estilo Flat moderno
        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSair.setBackground(new Color(204, 51, 51)); // Vermelho discreto para saída
        btnSair.setForeground(Color.WHITE);
        btnSair.setFocusPainted(false);
        btnSair.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSair.addActionListener(e -> {
            this.dispose();
            TelaAcesso telaInicial = new TelaAcesso();
            telaInicial.setVisible(true);
        });

        painelTopo.add(lblTituloSistema, BorderLayout.WEST);
        painelTopo.add(btnSair, BorderLayout.EAST);

        // ==================================================
        // 2. SISTEMA DE ABAS (TABBED PANE)
        // ==================================================
        JTabbedPane abas = new JTabbedPane();
        abas.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // Inicialização dos painéis reais (refeita de forma limpa)
        //PainelResultados abaResultados = new PainelResultados();
        PainelRespostasIndividuais abaRespostasIndividuais = new PainelRespostasIndividuais();
        PainelCadastroAdmin abaCadastroAdmin = new PainelCadastroAdmin();

        // Adicionando as abas ativas ao componente
        //abas.addTab("Resultados Gerais", abaResultados);
        abas.addTab("Respostas Individuais", abaRespostasIndividuais); // Ativada!
        abas.addTab("Cadastrar Administrador", abaCadastroAdmin);

        // ==================================================
        // 3. MONTAGEM FINAL DA TELA
        // ==================================================
        add(painelTopo, BorderLayout.NORTH); // Topo fixo no Norte
        add(abas, BorderLayout.CENTER);      // Abas ocupam todo o resto do espaço

        // Garante que a tela abra centralizada se não estiver maximizada
        setLocationRelativeTo(null);
    }
}