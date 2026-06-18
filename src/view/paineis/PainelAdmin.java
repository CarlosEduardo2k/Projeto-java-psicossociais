package view.paineis;

import view.telaInicial.TelaAdmin;

import javax.swing.*;
import java.awt.*;

public class PainelAdmin extends JPanel {
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public PainelAdmin() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Acesso da Administração"));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JLabel lblLogin = new JLabel("Login de Acesso:");
        txtLogin = new JTextField(15);
        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField(15);
        btnEntrar = new JButton("Entrar no Painel Admin");

        btnEntrar.addActionListener(e -> {
            String login = txtLogin.getText();
            String senha = new String(txtSenha.getPassword());

            if(login.equals("admin") && senha.equals("123")) {
                JFrame janelaLogin = (JFrame) SwingUtilities.getWindowAncestor(this);
                janelaLogin.dispose();

                TelaAdmin telaGerencial = new TelaAdmin();
                telaGerencial.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Login ou Senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        lblLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtLogin.setMaximumSize(new Dimension(200, 30));
        lblSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtSenha.setMaximumSize(new Dimension(200, 30));
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(lblLogin);
        this.add(txtLogin);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(lblSenha);
        this.add(txtSenha);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(btnEntrar);
    }
}