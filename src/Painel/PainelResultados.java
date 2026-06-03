package Painel;

import javax.swing.*;
import java.awt.*;

public class PainelResultados extends JPanel {

    public PainelResultados() {
        // Divide o painel em 2 colunas idênticas (Esquerda e Direita)
        setLayout(new GridLayout(1, 2, 20, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem interna da tela

        // --- LADO ESQUERDO: Lista de Funcionários ---
        JPanel ladoEsquerdo = new JPanel();
        ladoEsquerdo.setLayout(new BoxLayout(ladoEsquerdo, BoxLayout.Y_AXIS));
        ladoEsquerdo.setBackground(Color.WHITE);

        // Linha vertical cinza apenas do lado direito deste painel
        ladoEsquerdo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));

        JLabel lblTituloFunc = new JLabel("Funcionários (0)");
        lblTituloFunc.setFont(new Font("Arial", Font.BOLD, 16));
        lblTituloFunc.setForeground(new Color(20, 60, 130)); // Azul escuro do print

        JLabel lblSubtituloFunc = new JLabel("Nenhum respondente ainda.");
        lblSubtituloFunc.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtituloFunc.setForeground(Color.GRAY);

        ladoEsquerdo.add(lblTituloFunc);
        ladoEsquerdo.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento
        ladoEsquerdo.add(lblSubtituloFunc);

        // --- LADO DIREITO: Detalhes ---
        JPanel ladoDireito = new JPanel();
        ladoDireito.setLayout(new FlowLayout(FlowLayout.LEFT));
        ladoDireito.setBackground(Color.WHITE);

        JLabel lblInstrucao = new JLabel("Selecione um funcionário para ver os detalhes.");
        lblInstrucao.setFont(new Font("Arial", Font.PLAIN, 12));
        lblInstrucao.setForeground(Color.GRAY);

        ladoDireito.add(lblInstrucao);

        // Adiciona as duas metades ao painel principal
        add(ladoEsquerdo);
        add(ladoDireito);
    }
}