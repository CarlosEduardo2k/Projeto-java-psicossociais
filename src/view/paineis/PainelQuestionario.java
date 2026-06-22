package view.paineis;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


import dao.FuncionarioDAO;
import dao.RespostaDAO;
import dao.ResultadoCategoriaDAO;
import model.Funcionario;
import model.NivelRisco;
import model.Resposta;
import model.ResultadoCategoria;
import questionario.BancoPerguntas;
import questionario.Pergunta;
import questionario.Categoria;
import telaInicial.TelaAcesso;

public class PainelQuestionario extends JFrame {

    // Componentes principais da tela
    private JPanel painelPrincipal;

    // Lista que guarda os grupos de respostas
    private List<ButtonGroup> gruposRespostas;

    private List<Pergunta> perguntas;

    public PainelQuestionario(String nome, String cpf) {
        setLayout(new BorderLayout());

        // =========================
        // CONFIGURAÇÕES INICIAIS
        // =========================

        // Painel principal da tela
        painelPrincipal = new JPanel();
        painelPrincipal.setBackground(Color.WHITE);

        gruposRespostas = new ArrayList<>();

        painelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        painelPrincipal.setLayout(
                new BoxLayout(
                        painelPrincipal,
                        BoxLayout.Y_AXIS
                )
        );

        JScrollPane scroll = new JScrollPane(
                painelPrincipal
        );
        scroll.getVerticalScrollBar().setUnitIncrement(15);

        // =========================
        // CARREGAMENTO DAS PERGUNTAS
        // =========================

        BancoPerguntas banco = new BancoPerguntas();
        perguntas = banco.carregarPerguntas();



        // =========================
        // CABEÇALHO DA TELA
        // =========================

        JPanel painelCabecalho = new JPanel();

        painelCabecalho.setLayout(
                new BoxLayout(
                        painelCabecalho,
                        BoxLayout.Y_AXIS
                )
        );

        painelCabecalho.setBackground(
                new Color(25, 70, 130)
        );

        painelCabecalho.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel lblTitulo = new JLabel(
                "Questionário Psicossocial - NR-01"
        );

        lblTitulo.setForeground(
                Color.WHITE
        );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        JLabel lblEscala = new JLabel(
                "1 = Discordo Totalmente | 2 = Discordo | 3 = Neutro | 4 = Concordo | 5 = Concordo Totalmente"
        );

        lblEscala.setForeground(
                Color.WHITE
        );

        lblEscala.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        painelCabecalho.add(lblTitulo);
        painelCabecalho.add(Box.createVerticalStrut(5));
        painelCabecalho.add(lblEscala);

        //painelPrincipal.add(painelCabecalho);
        painelPrincipal.add(Box.createVerticalStrut(20));

        // =========================
        // CRIAÇÃO DAS PERGUNTAS
        // =========================

        int numeroPergunta = 1;

        for (Pergunta pergunta : perguntas) {

            // Painel individual da pergunta
            JPanel painelPergunta = new JPanel();
            // Card da pergunta
            painelPergunta.setBackground(
                    Color.WHITE
            );

            painelPergunta.setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            80
                    )
            );

            painelPergunta.setAlignmentX(
                    LEFT_ALIGNMENT
            );

            painelPergunta.setLayout(
                    new BorderLayout()
            );

            painelPergunta.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    new Color(220,220,220)
                            ),
                            BorderFactory.createEmptyBorder(
                                    15,15,15,15
                            )
                    )
            );

            // Texto da pergunta
            JLabel lblPergunta = new JLabel(
                    numeroPergunta + ". " + pergunta.getTexto()
            );
            lblPergunta.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            15
                    )
            );

            numeroPergunta++;

            lblPergunta.setAlignmentX(
                    LEFT_ALIGNMENT
            );

            // =========================
            // ESCALA DE RESPOSTAS
            // =========================

            JPanel painelEscala = new JPanel();
            painelEscala.setBackground(
                    Color.WHITE
            );

            painelEscala.setAlignmentX(
                    LEFT_ALIGNMENT
            );

            JRadioButton rb1 = new JRadioButton("1");
            JRadioButton rb2 = new JRadioButton("2");
            JRadioButton rb3 = new JRadioButton("3");
            JRadioButton rb4 = new JRadioButton("4");
            JRadioButton rb5 = new JRadioButton("5");

            rb1.setBackground(Color.WHITE);
            rb2.setBackground(Color.WHITE);
            rb3.setBackground(Color.WHITE);
            rb4.setBackground(Color.WHITE);
            rb5.setBackground(Color.WHITE);

            // Valor interno dos botões
            rb1.setActionCommand("1");
            rb2.setActionCommand("2");
            rb3.setActionCommand("3");
            rb4.setActionCommand("4");
            rb5.setActionCommand("5");

            // Grupo da pergunta
            ButtonGroup grupo = new ButtonGroup();

            gruposRespostas.add(grupo);

            grupo.add(rb1);
            grupo.add(rb2);
            grupo.add(rb3);
            grupo.add(rb4);
            grupo.add(rb5);

            painelEscala.add(rb1);
            painelEscala.add(rb2);
            painelEscala.add(rb3);
            painelEscala.add(rb4);
            painelEscala.add(rb5);

            // Montagem do painel da pergunta
            painelPergunta.add(
                    lblPergunta,
                    BorderLayout.WEST
            );

            painelPergunta.add(
                    painelEscala,
                    BorderLayout.EAST
            );

            painelPrincipal.add(
                    Box.createVerticalStrut(8)
            );

            painelPrincipal.add(
                    painelPergunta
            );
        }

        // =========================
        // BOTÃO FINALIZAR
        // =========================

        // Botão responsável por finalizar e enviar o questionário
        JButton btnFinalizar = new JButton(
                "Finalizar Questionário"
        );

// Configurações visuais do botão
        btnFinalizar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        btnFinalizar.setBackground(
                new Color(25, 70, 130)
        );

        btnFinalizar.setForeground(
                Color.WHITE
        );

        btnFinalizar.setFocusPainted(
                false
        );

        btnFinalizar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnFinalizar.setPreferredSize(
                new Dimension(
                        250,
                        40
                )
        );

// Evento executado ao clicar no botão
        btnFinalizar.addActionListener(e -> {

            boolean todasRespondidas = true;

            // Verifica se todas as perguntas foram respondidas
            for (ButtonGroup grupo : gruposRespostas) {

                if (grupo.getSelection() == null) {

                    todasRespondidas = false;
                    break;
                }
            }

            // Impede o envio caso existam perguntas sem resposta
            if (!todasRespondidas) {

                JOptionPane.showMessageDialog(
                        null,
                        "Existem perguntas sem resposta!"
                );

            } else {

                Funcionario funcionario =
                        new Funcionario(nome, cpf);

                FuncionarioDAO funcionarioDAO =
                        new FuncionarioDAO();

                int funcionarioId =
                        funcionarioDAO.salvar(funcionario);

                RespostaDAO respostaDAO =
                        new RespostaDAO();

                // Variáveis responsáveis por acumular a pontuação
                // de cada categoria do questionário
                int somaOT = 0;
                int somaCT = 0;
                int somaRT = 0;
                int somaRP = 0;
                int somaLA = 0;
                int somaDE = 0;
                int somaDP = 0;

                // Percorre todas as perguntas respondidas
                for(int i = 0; i < perguntas.size(); i++) {

                    Pergunta pergunta = perguntas.get(i);

                    ButtonGroup grupo = gruposRespostas.get(i);

                    int respostaOriginal =
                            Integer.parseInt(
                                    grupo.getSelection()
                                            .getActionCommand()
                            );

                    // Salva a resposta individual
                    Resposta respostaBanco =
                            new Resposta(
                                    funcionarioId,
                                    pergunta.getId(),
                                    respostaOriginal
                            );

                    respostaDAO.salvar(respostaBanco);

                    // Resposta usada para cálculo
                    int respostaCalculada =
                            respostaOriginal;

                    if(pergunta.isInvertida()){
                        respostaCalculada =
                                6 - respostaCalculada;
                    }

                    if(pergunta.getCategoria() == Categoria.OT){
                        somaOT += respostaCalculada;
                    }

                    if(pergunta.getCategoria() == Categoria.CT){
                        somaCT += respostaCalculada;
                    }

                    if(pergunta.getCategoria() == Categoria.RT){
                        somaRT += respostaCalculada;
                    }

                    if(pergunta.getCategoria() == Categoria.RP){
                        somaRP += respostaCalculada;
                    }

                    if(pergunta.getCategoria() == Categoria.LA){
                        somaLA += respostaCalculada;
                    }

                    if(pergunta.getCategoria() == Categoria.DE){
                        somaDE += respostaCalculada;
                    }

                    if(pergunta.getCategoria() == Categoria.DP){
                        somaDP += respostaCalculada;
                    }
                }
                NivelRisco otRisco =
                        NivelRisco.definirPelaPontuacao(somaOT, 6);

                NivelRisco ctRisco =
                        NivelRisco.definirPelaPontuacao(somaCT, 5);

                NivelRisco rtRisco =
                        NivelRisco.definirPelaPontuacao(somaRT, 5);

                NivelRisco rpRisco =
                        NivelRisco.definirPelaPontuacao(somaRP, 3);

                NivelRisco laRisco =
                        NivelRisco.definirPelaPontuacao(somaLA, 2);

                NivelRisco deRisco =
                        NivelRisco.definirPelaPontuacao(somaDE, 2);

                NivelRisco dpRisco =
                        NivelRisco.definirPelaPontuacao(somaDP, 2);

                // Cria o objeto contendo os resultados do questionário
                ResultadoCategoria resultado =
                        new ResultadoCategoria(
                                funcionarioId,
                                somaOT,
                                somaCT,
                                somaRT,
                                somaRP,
                                somaLA,
                                somaDE,
                                somaDP,
                                otRisco,
                                ctRisco,
                                rtRisco,
                                rpRisco,
                                laRisco,
                                deRisco,
                                dpRisco
                        );

                // Salva as pontuações das categorias no banco
                ResultadoCategoriaDAO resultadoDAO = new ResultadoCategoriaDAO();

                resultadoDAO.salvar(resultado);

                // Logs para conferência durante o desenvolvimento
                System.out.println(
                        "Funcionário salvo com ID: " + funcionarioId
                );

                System.out.println("OT = " + somaOT);
                System.out.println("CT = " + somaCT);
                System.out.println("RT = " + somaRT);
                System.out.println("RP = " + somaRP);
                System.out.println("LA = " + somaLA);
                System.out.println("DE = " + somaDE);
                System.out.println("DP = " + somaDP);

                JOptionPane.showMessageDialog(this,"Respostas enviadas com sucesso! \n Obrigado por Participar ", "Concluído", JOptionPane.INFORMATION_MESSAGE);
                TelaAcesso retorno = new TelaAcesso();
                retorno.setVisible(true);
            }
        });

// Espaçamento antes do botão
        painelPrincipal.add(
                Box.createVerticalStrut(20)
        );

// Adiciona o botão ao painel principal
        painelPrincipal.add(btnFinalizar);

        // =========================
        // CONFIGURAÇÕES DA JANELA
        // =========================

        add(painelCabecalho, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        setTitle("Questionário Psicossocial");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
