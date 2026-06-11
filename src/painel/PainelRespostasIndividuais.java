package painel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import dao.FuncionarioDAO;
import dao.ResultadoCategoriaDAO;
import model.Funcionario;
import model.ResultadoCategoria;

public class PainelRespostasIndividuais extends JPanel {

    private JList<Funcionario> listaComponente;
    private DefaultListModel<Funcionario> modeloLista;
    private JPanel painelDetalhesDireita;

    public PainelRespostasIndividuais() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 1. CRIANDO O PAINEL DA ESQUERDA (Lista de Funcionários)
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

        // Evento: Quando o admin clicar em um funcionário da lista
        listaComponente.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Funcionario selecionado = listaComponente.getSelectedValue();
                if (selecionado != null) {
                    exibirDetalhesDoFuncionario(selecionado);
                }
            }
        });

        JScrollPane scrollLista = new JScrollPane(listaComponente);
        scrollLista.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        painelEsquerda.add(scrollLista, BorderLayout.CENTER);

        // 2. CRIANDO O PAINEL DA DIREITA (Detalhes e Gráficos)
        painelDetalhesDireita = new JPanel(new BorderLayout());
        painelDetalhesDireita.setBackground(Color.WHITE);

        JScrollPane scrollDetalhes = new JScrollPane(painelDetalhesDireita);
        scrollDetalhes.setBorder(null);

        // 3. DIVIDINDO A TELA EM DUAS PARTES (Split Pane)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerda, scrollDetalhes);
        splitPane.setDividerLocation(320); // Largura da coluna da esquerda
        splitPane.setDividerSize(3);
        splitPane.setBorder(null);
        add(splitPane, BorderLayout.CENTER);

        // Carrega a lista de funcionários assim que a tela abre
        carregarListaFuncionarios();
    }

    private void carregarListaFuncionarios() {
        modeloLista.clear();
        FuncionarioDAO dao = new FuncionarioDAO();
        List<Funcionario> funcionario = dao.listarTodos();
        for (Funcionario f : funcionario) {
            modeloLista.addElement(f);
        }

        // Seleciona o primeiro da lista automaticamente, se houver
        if (!modeloLista.isEmpty()) {
            listaComponente.setSelectedIndex(0);
        }
    }

    private void exibirDetalhesDoFuncionario(Funcionario f) {
        painelDetalhesDireita.removeAll();

        // Cria o painel interno com margens internas (padding)
        JPanel conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));
        conteudo.setBackground(Color.WHITE);
        conteudo.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Título Principal
        JLabel lblTituloDetalhe = new JLabel("Detalhes - " + f.getNome());
        lblTituloDetalhe.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloDetalhe.setForeground(new Color(25, 70, 130));
        lblTituloDetalhe.setAlignmentX(Component.LEFT_ALIGNMENT);
        conteudo.add(lblTituloDetalhe);
        conteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        // Bloco Cinza de Informações Gerais (CPF e Data)
        JPanel cardInfoGeral = new JPanel(new GridLayout(2, 1, 5, 5));
        cardInfoGeral.setBackground(new Color(225, 225, 225));
        cardInfoGeral.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 190, 190)),
                new EmptyBorder(12, 15, 12, 15)
        ));
        cardInfoGeral.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardInfoGeral.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JLabel lblCpf = new JLabel("CPF: " + f.getCpf());
        lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Nota: Como o modelo atual não salva a data, usamos uma fixa ou o CPF como base de exibição visual
        JLabel lblData = new JLabel("Data de Envio: Verificado no Sistema");
        lblData.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        cardInfoGeral.add(lblCpf);
        cardInfoGeral.add(lblData);
        conteudo.add(cardInfoGeral);
        conteudo.add(Box.createRigidArea(new Dimension(0, 20)));

        // Subtítulo das Categorias
        JLabel lblSubtitulo = new JLabel("Análise por Categoria:");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        conteudo.add(lblSubtitulo);
        conteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        // BUSCA AS NOTAS NO BANCO DE DADOS
        ResultadoCategoriaDAO resultadoDAO = new ResultadoCategoriaDAO();
        ResultadoCategoria res = resultadoDAO.buscarPorFuncionarioId(f.getId());

        if (res != null) {
            // Adiciona cada bloco de categoria exatamente igual ao mockup fornecido
            // Nota: Se suas notas forem somas altas (Ex: 15), divida pelo fator do questionário para virar escala de 1 a 5.
            conteudo.add(criarCardCategoria("Organização do Trabalho", res.getOt()));
            conteudo.add(criarCardCategoria("Condições de Trabalho", res.getCt()));
            conteudo.add(criarCardCategoria("Relações Socioprofissionais", res.getRt()));
            conteudo.add(criarCardCategoria("Realização Profissional", res.getRp()));
            conteudo.add(criarCardCategoria("Liberdade e Autonomia", res.getLa()));
            conteudo.add(criarCardCategoria("Desgaste Emocional", res.getDp()));
        } else {
            JLabel lblAviso = new JLabel("Este funcionário ainda não possui questionários respondidos.");
            lblAviso.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            lblAviso.setForeground(Color.GRAY);
            conteudo.add(lblAviso);
        }

        painelDetalhesDireita.add(conteudo, BorderLayout.NORTH);
        painelDetalhesDireita.revalidate();
        painelDetalhesDireita.repaint();
    }

    // MÉTODO AUXILIAR: Cria o retângulo cinza com a barra de progresso interna
    private JPanel criarCardCategoria(String nomeCategoria, double nota) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(new Color(238, 238, 238));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(10, 15, 10, 15)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

        // Linha Superior: Nome da Categoria (Esquerda) e Nota Numérica (Direita)
        JLabel lblNome = new JLabel(nomeCategoria);
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNome.setForeground(new Color(50, 50, 50));

        JLabel lblNota = new JLabel(String.format("%.1f", nota));
        lblNota.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setOpaque(false);
        painelSuperior.add(lblNome, BorderLayout.WEST);
        painelSuperior.add(lblNota, BorderLayout.EAST);

        // Linha Inferior: Barra de Progresso (Centro) e Texto do Status (Direita)
        // Definindo dinamicamente as cores e status baseados no nível de criticidade
        Color corBarra;
        String statusTexto;

        if (nota >= 4.0) {
            corBarra = new Color(197, 28, 64); // Vermelho da imagem (Alto)
            statusTexto = "Alto";
            lblNota.setForeground(corBarra);
        } else if (nota >= 2.0) {
            corBarra = new Color(255, 140, 0); // Laranja da imagem (Moderado)
            statusTexto = "Moderado";
            lblNota.setForeground(corBarra);
        } else {
            corBarra = new Color(46, 139, 87); // Verde (Baixo)
            statusTexto = "Baixo";
            lblNota.setForeground(corBarra);
        }

        // Configuração da barra de progresso (Mapeada de 0 a 5.0)
        JProgressBar barraProgresso = new JProgressBar(0, 50);
        barraProgresso.setValue((int) (nota * 10)); // Transforma decimal em inteiro para preenchimento da barra
        barraProgresso.setForeground(corBarra);
        barraProgresso.setBackground(Color.WHITE);
        barraProgresso.setBorderPainted(false);
        barraProgresso.setPreferredSize(new Dimension(200, 12));

        JLabel lblStatus = new JLabel(statusTexto);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblStatus.setForeground(corBarra);
        lblStatus.setBorder(new EmptyBorder(0, 10, 0, 0));

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setOpaque(false);
        painelInferior.add(barraProgresso, BorderLayout.CENTER);
        painelInferior.add(lblStatus, BorderLayout.EAST);

        card.add(painelSuperior, BorderLayout.NORTH);
        card.add(painelInferior, BorderLayout.SOUTH);

        // Cria um pequeno espaçamento invisível abaixo de cada bloco
        JPanel containerComEspaco = new JPanel(new BorderLayout());
        containerComEspaco.setOpaque(false);
        containerComEspaco.add(card, BorderLayout.CENTER);
        containerComEspaco.setBorder(new EmptyBorder(0, 0, 8, 0));
        containerComEspaco.setAlignmentX(Component.LEFT_ALIGNMENT);

        return containerComEspaco;
    }

    // RENDEREZADOR PERSONALIZADO CORRIGIDO:
    private class FuncionarioCardRenderer extends JPanel implements ListCellRenderer<Funcionario> {
        private JLabel lblNomeCard = new JLabel();
        private JLabel lblSubCard = new JLabel();

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
                setBackground(new Color(10, 45, 100)); // Azul Escuro de seleção
                lblNomeCard.setForeground(Color.WHITE);
                lblNomeCard.setFont(new Font("Segoe UI", Font.BOLD, 13));
                lblSubCard.setForeground(new Color(200, 215, 240));
                lblSubCard.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            } else {
                setBackground(Color.WHITE);
                lblNomeCard.setForeground(Color.BLACK);
                lblNomeCard.setFont(new Font("Segoe UI", Font.BOLD, 13));
                lblSubCard.setForeground(Color.GRAY);
                lblSubCard.setFont(new Font("Segoe UI", Font.PLAIN, 11));

                // Borda inferior discreta para separar os funcionários
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));
            }
            return this;
        }
    }
}