package telaInicial;
import painel.TelaQuestionario;

import javax.swing.*;
import java.awt.*;

public class PainelUsuario extends JPanel {
    // Atributos (componentes que precisamos acessar)
    private JTextField txtNome;
    private JTextField txtCpf;
    private JButton btnEntrar;

    // Construtor: define as propriedades do painel ao ser criado
    public PainelUsuario() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Acesso do Usuário"));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JLabel lblNome = new JLabel("Nome Completo:");
        txtNome = new JTextField(15);
        JLabel lblCpf = new JLabel("CPF:");
        txtCpf = new JTextField(15);
        btnEntrar = new JButton("Entrar como Usuário");
        // Evento do botão
        btnEntrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();

            if (nome.trim().isEmpty()|| cpf.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Por favor, preencha o Nome e o CPF!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JFrame janelaLogin = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (janelaLogin != null) {
                janelaLogin.dispose();
            }
            TelaQuestionario  TelaQuestoes = new TelaQuestionario(nome, cpf);
            TelaQuestoes.setVisible(true);
        });
        // Alinhamentos
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtNome.setMaximumSize(new Dimension(200, 30));
        lblCpf.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtCpf.setMaximumSize(new Dimension(200, 30));
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Montagem do Painel (this se refere ao próprio JPanel)
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(lblNome);
        this.add(txtNome);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(lblCpf);
        this.add(txtCpf);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(btnEntrar);
    }
}