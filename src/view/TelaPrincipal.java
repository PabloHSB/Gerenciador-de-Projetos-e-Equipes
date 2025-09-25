package view;

// Imports para os componentes Swing
import javax.swing.*;
import java.awt.*;

// Não precisamos importar as outras telas (TelaLogin, TelaCadastroProjeto, etc.)
// porque todas elas estão no mesmo pacote "view". O Java as encontra automaticamente.

/**
 * Representa a tela principal (Dashboard) do sistema, servindo como menu de navegação.
 */
public class TelaPrincipal extends JFrame {

    // --- Componentes da Interface declarados como atributos da classe ---
    private JButton cadastrarProjetoButton;
    private JButton listarProjetosButton;
    private JButton cadastrarTarefaButton;
    private JButton cadastrarUsuarioButton;
    private JButton sairButton;

    public TelaPrincipal() {
        // --- Configurações da Janela ---
        setTitle("Dashboard - Sistema de Gestão");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Painel e Layout ---
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // --- Título ---
        JLabel tituloLabel = new JLabel("Menu Principal", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(tituloLabel);

        // --- Inicialização dos Botões ---
        cadastrarProjetoButton = new JButton("Cadastrar Novo Projeto");
        listarProjetosButton = new JButton("Listar Projetos");
        cadastrarTarefaButton = new JButton("Cadastrar Nova Tarefa");
        cadastrarUsuarioButton = new JButton("Cadastrar Novo Usuário");
        sairButton = new JButton("Sair (Logout)");

        // --- Adicionando os Botões ao Painel ---
        panel.add(cadastrarProjetoButton);
        panel.add(listarProjetosButton);
        panel.add(cadastrarTarefaButton);
        panel.add(cadastrarUsuarioButton);
        panel.add(sairButton);

        add(panel);

        // --- Ações dos Botões ---
        cadastrarProjetoButton.addActionListener(e -> new TelaCadastroProjeto().setVisible(true));
        listarProjetosButton.addActionListener(e -> new TelaListagemProjetos().setVisible(true));
        cadastrarTarefaButton.addActionListener(e -> new TelaCadastroTarefa().setVisible(true));
        cadastrarUsuarioButton.addActionListener(e -> new TelaCadastroUsuario().setVisible(true));
        sairButton.addActionListener(e -> {
            dispose(); // Fecha o dashboard
            new TelaLogin().setVisible(true); // Abre a tela de login
        });
    }

    /**
     * Ponto de entrada para executar esta tela de forma independente para testes.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}