package painel;

import controller.RespostasIndividuaisController;
import model.Funcionario;
import questionario.Pergunta;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PainelRespostasIndividuais extends JPanel {

    private final RespostasIndividuaisController controller;

    // Componentes da Interface
    private DefaultListModel<Funcionario> listModel;
    private JList<Funcionario> jListFuncionarios;
    private JPanel painelDireitoConteudo;
    private JLabel lblTituloDetalhes;

    public PainelRespostasIndividuais() {
        this.controller = new RespostasIndividuaisController();
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        initComponentes();
        carregarFuncionarios();
    }

    private void initComponentes() {
        // =========================================================================
        // 1. PAINEL ESQUERDO: Lista de Funcionários
        // =========================================================================
        JPanel painelEsquerdo = new JPanel(new BorderLayout(5, 5));
        painelEsquerdo.setBackground(Color.WHITE);
        painelEsquerdo.setPreferredSize(new Dimension(250, 0));
        painelEsquerdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

        JLabel lblTituloLista = new JLabel("Funcionários");
        lblTituloLista.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloLista.setForeground(new Color(0, 51, 102));
        painelEsquerdo.add(lblTituloLista, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        jListFuncionarios = new JList<>(listModel);
        jListFuncionarios.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jListFuncionarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Customização visual da linha selecionada (Azul escuro comercial)
        jListFuncionarios.setSelectionBackground(new Color(0, 51, 102));
        jListFuncionarios.setSelectionForeground(Color.WHITE);
        jListFuncionarios.setFixedCellHeight(45); // Dá espaço para respirar

        // Renderizador para mostrar Nome e CPF na lista lateral
        jListFuncionarios.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel cell = new JPanel(new GridLayout(2, 1, 2, 2));
                cell.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                if (isSelected) {
                    cell.setBackground(list.getSelectionBackground());
                } else {
                    cell.setBackground(index % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                }

                if (value instanceof Funcionario) {
                    Funcionario func =(Funcionario) value;
                    JLabel name = new JLabel(func.getNome());
                    name.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    name.setForeground(isSelected ? Color.WHITE : Color.BLACK);

                    JLabel cpf = new JLabel("CPF: " + func.getCpf());
                    cpf.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                    cpf.setForeground(isSelected ? new Color(200, 220, 240) : Color.GRAY);

                    cell.add(name);
                    cell.add(cpf);
                }
                return cell;
            }
        });

        // Evento de clique na lista
        jListFuncionarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Funcionario selecionado = jListFuncionarios.getSelectedValue();
                if (selecionado != null) {
                    atualizarDetalhesDoFuncionario(selecionado);
                }
            }
        });

        painelEsquerdo.add(new JScrollPane(jListFuncionarios), BorderLayout.CENTER);
        add(painelEsquerdo, BorderLayout.WEST);

        // =========================================================================
        // 2. PAINEL DIREITO: Exibição das Respostas (Com Rolagem)
        // =========================================================================
        JPanel painelDireitoMaster = new JPanel(new BorderLayout(10, 10));
        painelDireitoMaster.setBackground(Color.WHITE);
        painelDireitoMaster.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblTituloDetalhes = new JLabel("Selecione um funcionário para ver os detalhes.");
        lblTituloDetalhes.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloDetalhes.setForeground(new Color(0, 51, 102));
        painelDireitoMaster.add(lblTituloDetalhes, BorderLayout.NORTH);

        // Painel interno que vai segurar os 25 cards empilhados verticalmente
        painelDireitoConteudo = new JPanel();
        painelDireitoConteudo.setLayout(new BoxLayout(painelDireitoConteudo, BoxLayout.Y_AXIS));
        painelDireitoConteudo.setBackground(Color.WHITE);

        JScrollPane scrollDireito = new JScrollPane(painelDireitoConteudo);
        scrollDireito.setBorder(null);
        scrollDireito.getVerticalScrollBar().setUnitIncrement(16); // Rolagem suave macia

        painelDireitoMaster.add(scrollDireito, BorderLayout.CENTER);
        add(painelDireitoMaster, BorderLayout.CENTER);
    }

    // Carrega os funcionários vindos do Controller para preencher a JList
    private void carregarFuncionarios() {
        listModel.clear();
        List<Funcionario> funcionarios = controller.listaFuncionarios();
        for (Funcionario f : funcionarios) {
            listModel.addElement(f);
        }
    }

    // Monta dinamicamente a lista de perguntas cruzando com as respostas do banco
    private void atualizarDetalhesDoFuncionario(Funcionario func) {
        lblTituloDetalhes.setText("Respostas - " + func.getNome().toLowerCase());
        painelDireitoConteudo.removeAll();

        // Busca o mapa <pergunta_id, resposta> no banco
        Map<Integer, Integer> respostasDoBanco = controller.obterRespostasDoFuncionario(func.getId());
        List<Pergunta> todasAsPerguntas = controller.obterListaDePerguntas();

        for (Pergunta perg : todasAsPerguntas) {
            // Se o funcionário não respondeu a pergunta por algum motivo, assume 0
            int alternativaEscolhida = respostasDoBanco.getOrDefault(perg.getId(), 0);

            // Adiciona o card gráfico da pergunta
            painelDireitoConteudo.add(criarCardResposta(perg.getId(), perg.getTexto(), alternativaEscolhida));
            painelDireitoConteudo.add(Box.createVerticalStrut(10)); // Espacinho entre cards
        }

        painelDireitoConteudo.revalidate();
        painelDireitoConteudo.repaint();
    }

    // Método de fábrica que desenha cada Card de Resposta exatamente igual ao modelo
    private JPanel criarCardResposta(int numeroQuestao, String textoQuestao, int alternativaEscolhida) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(new Color(230, 230, 230)); // Cor cinza de fundo dos cards modelo
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 205, 205), 1),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 95));

        // Enunciado da questão
        JLabel lblPergunta = new JLabel(numeroQuestao + ". " + textoQuestao);
        lblPergunta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPergunta.setForeground(new Color(40, 40, 40));
        card.add(lblPergunta, BorderLayout.NORTH);

        // Sub-painel para alinhar as caixas de número e o texto explicativo
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        painelInferior.setOpaque(false);

        // Gera os quadradinhos de escolha (1 a 5)
        for (int i = 1; i <= 5; i++) {
            JLabel lblNumero = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            lblNumero.setOpaque(true);
            lblNumero.setPreferredSize(new Dimension(28, 28));
            lblNumero.setFont(new Font("Segoe UI", Font.BOLD, 12));

            if (i == alternativaEscolhida) {
                // Destaca em Azul Escuro se for o valor do banco
                lblNumero.setBackground(new Color(0, 51, 102));
                lblNumero.setForeground(Color.WHITE);
            } else {
                // Fundo branco comum para as não selecionadas
                lblNumero.setBackground(Color.WHITE);
                lblNumero.setForeground(new Color(130, 130, 130));
                lblNumero.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));
            }
            painelInferior.add(lblNumero);
        }

        // Determina o texto de legenda e sua respectiva cor semântica
        JLabel lblStatus = new JLabel();
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 13));

        switch (alternativaEscolhida) {
            case 1: lblStatus.setText("Discordo Totalmente"); lblStatus.setForeground(new Color(220, 53, 69));
            break;
            case 2: lblStatus.setText("Discordo"); lblStatus.setForeground(new Color(240, 100, 40));
            break;
            case 3:  lblStatus.setText("Neutro"); lblStatus.setForeground(new Color(255, 159, 64));
            break;
            case 4:  lblStatus.setText("Concordo"); lblStatus.setForeground(new Color(40, 167, 69));
            break;
            case 5:  lblStatus.setText("Concordo Totalmente"); lblStatus.setForeground(new Color(25, 130, 50));
            break;
            default: lblStatus.setText("Não Respondida"); lblStatus.setForeground(Color.GRAY);
            break;
        }

        painelInferior.add(Box.createHorizontalStrut(12)); // Espaço antes do texto
        painelInferior.add(lblStatus);

        card.add(painelInferior, BorderLayout.CENTER);
        return card;
    }
}