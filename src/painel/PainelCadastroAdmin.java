package painel;

import javax.swing.*;
import java.awt.*;

public class PainelCadastroAdmin extends JPanel {

    private JTextField txtNome;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmarSenha;
    private JButton btnSalvar;

    public PainelCadastroAdmin() {
        // Define um layout vertical alinhado ao centro
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Título do Formulário
        JLabel lblTitulo = new JLabel("Cadastrar Novo Administrador");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campos do Formulário
        JLabel lblNome = new JLabel("Nome Completo:");
        txtNome = new JTextField(20);

        JLabel lblLogin = new JLabel("Login de Acesso:");
        txtLogin = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField(20);

        JLabel lblConfirmar = new JLabel("Confirme a Senha:");
        txtConfirmarSenha = new JPasswordField(20);

        btnSalvar = new JButton("Salvar Cadastro");
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSalvar.setBackground(new Color(25, 70, 130));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);

        // Configurando alinhamentos e tamanhos máximos para não deformar na tela cheia
        Dimension tamanhoCampo = new Dimension(300, 30);

        configurarComponente(lblNome, txtNome, tamanhoCampo);
        configurarComponente(lblLogin, txtLogin, tamanhoCampo);
        configurarComponente(lblSenha, txtSenha, tamanhoCampo);
        configurarComponente(lblConfirmar, txtConfirmarSenha, tamanhoCampo);
        btnSalvar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalvar.setMaximumSize(new Dimension(200, 40));

        // Evento do Botão Salvar
        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText();
            String login = txtLogin.getText();
            String senha = new String(txtSenha.getPassword());
            String confirmar = new String(txtConfirmarSenha.getPassword());

            // Validações básicas
            if (nome.trim().isEmpty() || login.trim().isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!senha.equals(confirmar)) {
                JOptionPane.showMessageDialog(this, "As senhas não conferem!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lógica do Banco de Dados entrará aqui futuramente
            JOptionPane.showMessageDialog(this, "Administrador " + nome + " cadastrado com sucesso!");

            // Limpa os campos após salvar
            limparCampos();
        });

        // Adicionando os componentes ao Painel com espaçamentos
        add(lblTitulo);
        add(Box.createVerticalStrut(25));

        add(lblNome); add(Box.createVerticalStrut(5)); add(txtNome); add(Box.createVerticalStrut(15));
        add(lblLogin); add(Box.createVerticalStrut(5)); add(txtLogin); add(Box.createVerticalStrut(15));
        add(lblSenha); add(Box.createVerticalStrut(5)); add(txtSenha); add(Box.createVerticalStrut(15));
        add(lblConfirmar); add(Box.createVerticalStrut(5)); add(txtConfirmarSenha); add(Box.createVerticalStrut(25));

        add(btnSalvar);
    }

    private void configurarComponente(JLabel label, JComponent campo, Dimension tamanho) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campo.setMaximumSize(tamanho);
    }

    private void limparCampos() {
        txtNome.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        txtConfirmarSenha.setText("");
    }
}
