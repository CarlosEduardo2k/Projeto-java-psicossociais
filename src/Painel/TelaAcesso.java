package Painel;

import javax.swing.*;
import java.awt.*;

// TelaAcesso É UM JFrame (Herança)
public class TelaAcesso extends JFrame {

    public TelaAcesso() {
        // Configurações da própria janela
        setTitle("Sistema - Tela de Acesso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLayout(new GridLayout(1, 2, 10, 0));
        setLocationRelativeTo(null);

        // Criação dos objetos dos painéis (Polimorfismo e Composição)
        PainelUsuario ladoUsuario = new PainelUsuario();
        PainelAdmin ladoAdmin = new PainelAdmin();

        // Adiciona os sub-painéis na janela principal
        add(ladoUsuario);
        add(ladoAdmin);
    }
}