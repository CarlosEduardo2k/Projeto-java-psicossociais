package painel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import questionario.BancoPerguntas;
import questionario.Pergunta;



public class TelaQuestionario extends JFrame {
    private JPanel painelPrincipal;

    public TelaQuestionario(){
        painelPrincipal = new JPanel();
        painelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );
        JScrollPane scroll = new JScrollPane(
                painelPrincipal
        );
        painelPrincipal.setLayout(
                new BoxLayout(
                        painelPrincipal,
                        BoxLayout.Y_AXIS
                )
        );
        BancoPerguntas banco = new BancoPerguntas();
        List<Pergunta> perguntas = banco.carregarPerguntas();
        JLabel lblTitulo = new JLabel(
                "Questionário Psicossocial - NR-01"
        );
        JLabel lblEscala = new JLabel(
                "1 = Discordo Totalmente | 2 = Discordo | 3 = Neutro | 4 = Concordo | 5 = Concordo Totalmente"
        );

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );
        lblEscala.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(lblEscala);

        int numeroPergunta = 1;
        for(Pergunta pergunta : perguntas) {

            JPanel painelPergunta = new JPanel();
            painelPergunta.setAlignmentX(LEFT_ALIGNMENT);

            painelPergunta.setLayout(
                    new BorderLayout()
            );
            painelPergunta.setBorder(
                    BorderFactory.createEmptyBorder(
                            10, 10, 10, 10
                    )
            );

            JLabel lblPergunta = new JLabel(
                    numeroPergunta + ". " + pergunta.getTexto()
            );
            numeroPergunta++;
            lblPergunta.setAlignmentX(LEFT_ALIGNMENT);

            // Escala da pergunta
            JPanel painelEscala = new JPanel();
            painelEscala.setAlignmentX(LEFT_ALIGNMENT);

            JRadioButton rb1 = new JRadioButton("1");
            JRadioButton rb2 = new JRadioButton("2");
            JRadioButton rb3 = new JRadioButton("3");
            JRadioButton rb4 = new JRadioButton("4");
            JRadioButton rb5 = new JRadioButton("5");

            ButtonGroup grupo = new ButtonGroup();

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

            painelPergunta.add(lblPergunta, BorderLayout.WEST);
            painelPergunta.add(painelEscala, BorderLayout.EAST);

            painelPrincipal.add(painelPergunta);
        }
        JButton btnFinalizar = new JButton(
                "Finalizar Questionário"
        );
        btnFinalizar.setPreferredSize(
                new Dimension(
                        250,
                        40
                )
        );
        painelPrincipal.add(btnFinalizar);

        add(scroll);

        setTitle("Questionário Psicossocial");

        setSize(900, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);
    }
}
