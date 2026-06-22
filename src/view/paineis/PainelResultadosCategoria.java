package view.paineis;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import controller.RespostasIndividuaisController;
import model.Funcionario;
import model.ResultadoCategoria;
import model.NivelRisco;

public class PainelResultadosCategoria extends JPanel {

    private final RespostasIndividuaisController controller;
    private JList<Funcionario> listaComponente;
    private DefaultListModel<Funcionario> modeloLista;
    private JPanel painelDetalhesDireita;

    public PainelResultadosCategoria() {
        // Inicializa o controlador
        this.controller = new RespostasIndividuaisController();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        montarLayoutTela();
        carregarListaFuncionarios();
    }

    private void montarLayoutTela() {
        // COLUNA DA ESQUERDA (Lista)
        JPanel painelEsquerda = new JPanel(new BorderLayout());
        painelEsquerda.setBackground(Color.WHITE);
        painelEsquerda.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblTituloLista = new JLabel("Funcionários");
        lblTituloLista.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloLista.setForeground(new Color(25, 70, 130));
        lblTituloLista.setBorder(new EmptyBorder(0, 0, 10, 0));
        painelEsquerda.add(lblTituloLista, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listaComponente = new JList<>(modeloLista);
        listaComponente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaComponente.setCellRenderer(new FuncionarioCardRenderer());

        listaComponente.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Funcionario selecionado = listaComponente.getSelectedValue();
                if (selecionado != null) exibirDetalhesDoFuncionario(selecionado);
            }
        });

        JScrollPane scrollLista = new JScrollPane(listaComponente);
        painelEsquerda.add(scrollLista, BorderLayout.CENTER);

        // COLUNA DA DIREITA (Detalhes)
        painelDetalhesDireita = new JPanel(new BorderLayout());
        painelDetalhesDireita.setBackground(Color.WHITE);
        JScrollPane scrollDetalhes = new JScrollPane(painelDetalhesDireita);
        scrollDetalhes.setBorder(null);

        // DIVISÓRIA
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerda, scrollDetalhes);
        splitPane.setDividerLocation(320);
        splitPane.setDividerSize(3);
        splitPane.setBorder(null);
        add(splitPane, BorderLayout.CENTER);
    }

    private void carregarListaFuncionarios() {
        modeloLista.clear();
        // Pede os dados puramente para o controlador
        List<Funcionario> funcionarios = controller.listaFuncionarios();
        funcionarios.forEach(modeloLista::addElement);

        if (!modeloLista.isEmpty()) listaComponente.setSelectedIndex(0);
    }

    private void exibirDetalhesDoFuncionario(Funcionario f) {
        painelDetalhesDireita.removeAll();

        JPanel conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));
        conteudo.setBackground(Color.WHITE);
        conteudo.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lblTituloDetalhe = new JLabel("Detalhes - " + f.getNome());
        lblTituloDetalhe.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloDetalhe.setForeground(new Color(25, 70, 130));
        conteudo.add(lblTituloDetalhe);
        conteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        // Pede as notas para o controlador
        ResultadoCategoria res = controller.obterNotasFuncionario(f.getId());

        if (res != null) {

            conteudo.add(criarCardCategoria("Organização do Trabalho", res.getOt(), 6));
            conteudo.add(criarCardCategoria("Condições de Trabalho", res.getCt(), 5));
            conteudo.add(criarCardCategoria("Relações Socioprofissionais", res.getRt(), 5));
            conteudo.add(criarCardCategoria("Realização Profissional", res.getRp(), 3));
            conteudo.add(criarCardCategoria("Liberdade e Autonomia", res.getLa(),2));
            conteudo.add(criarCardCategoria("Desgaste Emocional",res.getDe(), 2));
            conteudo.add(criarCardCategoria("Desvalorização Profissional", res.getDp(), 2));
        } else {
            conteudo.add(new JLabel("Este funcionário ainda não possui questionários respondidos."));
        }

        painelDetalhesDireita.add(conteudo, BorderLayout.NORTH);
        painelDetalhesDireita.revalidate();
        painelDetalhesDireita.repaint();
    }

    private JPanel criarCardCategoria(
            String nomeCategoria,
            int pontuacao,
            int quantidadePerguntas
    ) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(new Color(238, 238, 238));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(10, 15, 10, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

        JLabel lblNome = new JLabel(nomeCategoria);
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JLabel lblNota = new JLabel(String.valueOf(pontuacao));
        lblNota.setFont(new Font("Segoe UI", Font.BOLD, 16));

        NivelRisco risco = NivelRisco.definirPelaPontuacao(pontuacao, quantidadePerguntas);

        lblNota.setForeground(risco.getCor());

        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setOpaque(false);
        painelSuperior.add(lblNome, BorderLayout.WEST);
        painelSuperior.add(lblNota, BorderLayout.EAST);

        JProgressBar barraProgresso = new JProgressBar(
                quantidadePerguntas,
                quantidadePerguntas * 5
        );

        barraProgresso.setValue(pontuacao);
        barraProgresso.setForeground(risco.getCor());
        barraProgresso.setBackground(Color.WHITE);
        barraProgresso.setBorderPainted(false);
        barraProgresso.setPreferredSize(new Dimension(200, 12));

        JLabel lblStatus = new JLabel(risco.getDescricao());
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblStatus.setForeground(risco.getCor());

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setOpaque(false);
        painelInferior.add(barraProgresso, BorderLayout.CENTER);
        painelInferior.add(lblStatus, BorderLayout.EAST);

        card.add(painelSuperior, BorderLayout.NORTH);
        card.add(painelInferior, BorderLayout.SOUTH);

        JPanel containerComEspaco = new JPanel(new BorderLayout());
        containerComEspaco.setOpaque(false);
        containerComEspaco.add(card, BorderLayout.CENTER);
        containerComEspaco.setBorder(new EmptyBorder(0, 0, 8, 0));

        return containerComEspaco;
    }

    // Renderizador da lista (Visual dos nomes)
    private class FuncionarioCardRenderer extends JPanel implements ListCellRenderer<Funcionario> {
        private final JLabel lblNomeCard = new JLabel();
        private final JLabel lblSubCard = new JLabel();

        public FuncionarioCardRenderer() {
            setLayout(new GridLayout(2, 1, 2, 2));
            setBorder(new EmptyBorder(10, 12, 10, 12));
            add(lblNomeCard);
            add(lblSubCard);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Funcionario> list, Funcionario value, int index, boolean isSelected, boolean cellHasFocus) {
            lblNomeCard.setText(value.getNome());
            lblSubCard.setText("CPF: " + value.getCpf());

            if (isSelected) {
                setBackground(new Color(10, 45, 100));
                lblNomeCard.setForeground(Color.WHITE);
                lblSubCard.setForeground(new Color(200, 215, 240));
            } else {
                setBackground(Color.WHITE);
                lblNomeCard.setForeground(Color.BLACK);
                lblSubCard.setForeground(Color.GRAY);
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));
            }
            return this;
        }
    }
}