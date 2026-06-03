package Painel;

import javax.swing.*;
import java.awt.*;

public class TelaAdmin extends JFrame {

    public TelaAdmin() {
        setTitle("Área Administrativa");
        setSize(900, 600); // Janela maior para caber os dados corporativos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout()); // Perfeito para organizar Topo + Centro

        // 1. Criando a Barra de Topo (Header)
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBackground(new Color(25, 60, 122)); // Azul escuro idêntico ao da imagem
        painelTopo.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // Padding interno

        JLabel lblTituloSistema = new JLabel("Gestão - Análise Psicossocial");
        lblTituloSistema.setFont(new Font("Arial", Font.BOLD, 14));
        lblTituloSistema.setForeground(Color.WHITE);

        JButton btnSair = new JButton("Sair");
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(e -> {
            // Fecha a tela atual e pode voltar para a tela de login se quiser
            this.dispose();
            JOptionPane.showMessageDialog(null, "Sessão encerrada.");
        });

        painelTopo.add(lblTituloSistema, BorderLayout.WEST);
        painelTopo.add(btnSair, BorderLayout.EAST);

        // 2. Criando o Sistema de Abas (JTabbedPane)
        JTabbedPane abas = new JTabbedPane();
        abas.setFont(new Font("Arial", Font.BOLD, 13));

        // Cria a aba de resultados usando o painel que fizemos acima
        PainelResultados abaResultados = new PainelResultados();

        // Criando painéis vazios temporários para as outras abas apenas para demonstração
        JPanel abaRespostasIndividuais = new JPanel();
        JPanel abaRelatorioGeral = new JPanel();

        // Adicionando as abas ao componente de abas
        abas.addTab("Resultados", abaResultados);
        abas.addTab("Respostas Individuais", abaRespostasIndividuais);
        abas.addTab("Relatório Geral", abaRelatorioGeral);

        // 3. Juntando Tudo na Janela Principal
        add(painelTopo, BorderLayout.NORTH); // Topo fixo no Norte
        add(abas, BorderLayout.CENTER);      // Abas ocupam todo o resto do espaço (Centro)
    }
}