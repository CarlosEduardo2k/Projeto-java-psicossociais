package view.paineis;

import controller.RelatorioGeralController;
import model.RelatorioGeral;

import javax.swing.*;
import java.awt.*;

public class PainelRelatorioGeral extends JPanel {

    private final RelatorioGeralController controller;
    private JPanel containerConteudo;

    public PainelRelatorioGeral() {
        this.controller = new RelatorioGeralController();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        containerConteudo = new JPanel();
        containerConteudo.setLayout(new BoxLayout(containerConteudo, BoxLayout.Y_AXIS));
        containerConteudo.setBackground(Color.WHITE);
        containerConteudo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(containerConteudo);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        montarTela();
    }

    private void montarTela() {
        containerConteudo.removeAll();

        // 1. Carrega os dados calculados do banco
        RelatorioGeral dados = controller.carregarIndicadoresEmpresa();

        // TÍTULO PRINCIPAL
        JLabel lblTitulo = new JLabel("Relatório Geral da Organização");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 51, 102));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerConteudo.add(lblTitulo);
        containerConteudo.add(Box.createVerticalStrut(20));

        // 2. BLOCOS SUPERIORES DE MÉTRICAS (Respondentes | Perguntas | Categorias)
        JPanel painelCards = new JPanel(new GridLayout(1, 3, 15, 0));
        painelCards.setBackground(Color.WHITE);
        painelCards.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCards.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        painelCards.add(criarCardMetrica(String.valueOf(dados.getTotalRespondentes()), "Respondentes"));
        painelCards.add(criarCardMetrica("25", "Perguntas"));
        painelCards.add(criarCardMetrica("7", "Categorias"));

        containerConteudo.add(painelCards);
        containerConteudo.add(Box.createVerticalStrut(25));

        // SUBTÍTULO DA LISTAGEM
        JLabel lblSub = new JLabel("Médias por Categoria:");
        lblSub.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblSub.setForeground(Color.BLACK);
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerConteudo.add(lblSub);
        containerConteudo.add(Box.createVerticalStrut(15));

        // 3. ADICIONA AS BARRAS DE CADA UMA DAS CATEGORIAS
        containerConteudo.add(criarCardProgressoEmpresa("Organização do Trabalho (OT)",
                "Avalia aspectos relacionados à estrutura e dinâmica das tarefas, como pressão por resultados e ritmo excessivo.", dados.getMediaOt()));
        containerConteudo.add(Box.createVerticalStrut(12));

        containerConteudo.add(criarCardProgressoEmpresa("Condições de Trabalho (CT)",
                "Analisa se o ambiente oferece recursos adequados para as atividades, incluindo estrutura física e segurança.", dados.getMediaCt()));
        containerConteudo.add(Box.createVerticalStrut(12));

        containerConteudo.add(criarCardProgressoEmpresa("Relações Socioprofissionais (RT)",
                "Avalia a qualidade das relações interpessoais, comunicação e suporte entre colegas e lideranças.", dados.getMediaRt()));
        containerConteudo.add(Box.createVerticalStrut(12));

        containerConteudo.add(criarCardProgressoEmpresa("Realização Profissional (RP)",
                "Mede o sentimento de orgulho, prazer e compatibilidade do trabalho com os objetivos de carreira.", dados.getMediaRp()));
        containerConteudo.add(Box.createVerticalStrut(12));

        containerConteudo.add(criarCardProgressoEmpresa("Liberdade e Autonomia (LA)",
                "Mede o nível de controle que o funcionário possui para organizar e executar suas demandas diárias.", dados.getMediaLa()));
        containerConteudo.add(Box.createVerticalStrut(12));

        containerConteudo.add(criarCardProgressoEmpresa("Distanciamento Emocional (DE)",
                "Mede a necessidade ou ocorrência de isolamento afetivo como defesa frente às pressões laborais.", dados.getMediaDe()));
        containerConteudo.add(Box.createVerticalStrut(12));

        containerConteudo.add(criarCardProgressoEmpresa("Desgaste Emocional (DP)",
                "Mapeia sintomas de esgotamento, estresse frequente ou sentimentos de desvalorização.", dados.getMediaDp()));

        containerConteudo.add(Box.createVerticalGlue()); // Empurra o conteúdo restante para cima

        containerConteudo.revalidate();
        containerConteudo.repaint();
    }

    // Cria os pequenos painéis numéricos superiores
    private JPanel criarCardMetrica(String valor, String legenda) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(new Color(235, 235, 235));
        card.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.CENTER;

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblValor.setForeground(new Color(0, 51, 102));
        card.add(lblValor, gbc);

        gbc.gridy = 1;
        JLabel lblLegenda = new JLabel(legenda);
        lblLegenda.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblLegenda.setForeground(Color.DARK_GRAY);
        card.add(lblLegenda, gbc);

        return card;
    }

    // Monta o Bloco de Progresso com a cor semântica do risco baseada na nota real (0 a 35)
    private JPanel criarCardProgressoEmpresa(String titulo, String descricao, double mediaValor) {
        JPanel card = new JPanel(new BorderLayout(10, 5));
        card.setBackground(new Color(230, 230, 230));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 205, 205), 1),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 105));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Textos informativo-descritivos
        JPanel painelTextos = new JPanel(new BorderLayout(0, 4));
        painelTextos.setOpaque(false);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK);
        painelTextos.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblDesc = new JLabel("<html>" + descricao + "</html>");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDesc.setForeground(Color.GRAY);
        painelTextos.add(lblDesc, BorderLayout.CENTER);

        card.add(painelTextos, BorderLayout.CENTER);

        //Pontuação numérica e Classificação de Risco
        JPanel painelPontos = new JPanel(new GridLayout(2, 1, 0, 2));
        painelPontos.setOpaque(false);
        painelPontos.setPreferredSize(new Dimension(90, 40));

        // Define as cores do termômetro com base nas faixas padrão do questionário
        Color corRisco;
        String textoRisco;
        if (mediaValor >= 23.4) {
            corRisco = new Color(220, 53, 69); // Alto (Vermelho)
            textoRisco = "Alto";
        } else if (mediaValor >=15){
            corRisco = new Color(255, 140, 0); // Moderado / Médio (Laranja)
            textoRisco = "Moderado";
        } else {
            corRisco = new Color(40, 167, 69);
            textoRisco = "baixo";
        }

        JLabel lblNota = new JLabel(String.format("%.1f", mediaValor), SwingConstants.RIGHT);
        lblNota.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblNota.setForeground(corRisco);

        JLabel lblRisco = new JLabel(textoRisco, SwingConstants.RIGHT);
        lblRisco.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblRisco.setForeground(corRisco);

        painelPontos.add(lblNota);
        painelPontos.add(lblRisco);
        card.add(painelPontos, BorderLayout.EAST);

        // Barra de progresso horizontal linear
        JProgressBar barra = new JProgressBar(0, 35);
        barra.setValue((int) Math.round(mediaValor));
        barra.setStringPainted(false);
        barra.setForeground(corRisco);
        barra.setBackground(Color.WHITE);
        barra.setPreferredSize(new Dimension(0, 14));
        barra.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));

        card.add(barra, BorderLayout.SOUTH);

        return card;
    }
}