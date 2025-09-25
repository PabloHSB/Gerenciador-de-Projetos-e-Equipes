package view;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Representa a tela de login do sistema.
 * É a porta de entrada da aplicação.
 */
public class TelaLogin extends JFrame {

    // --- Componentes da Interface Gráfica ---
    private JTextField loginField;
    private JPasswordField senhaField;
    private JButton entrarButton;

    public TelaLogin() {
        // --- Configurações essenciais da janela (JFrame) ---
        setTitle("Login - Sistema de Gestão");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // --- Montagem do Painel ---
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Login:"));
        loginField = new JTextField();
        panel.add(loginField);

        panel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);

        entrarButton = new JButton("Entrar");
        panel.add(new JLabel());
        panel.add(entrarButton);

        add(panel);

        // --- Lógica de Ações (Controller) ---
        entrarButton.addActionListener(e -> realizarLogin());
        loginField.addActionListener(e -> senhaField.requestFocusInWindow());
        senhaField.addActionListener(e -> realizarLogin());
    }

    /**
     * Centraliza a lógica de autenticação do usuário.
     */
    private void realizarLogin() {
        String login = loginField.getText();
        String senha = new String(senhaField.getPassword());

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            // Tenta autenticar o usuário
            Usuario usuarioAutenticado = usuarioDAO.autenticarUsuario(login, senha);

            if (usuarioAutenticado != null) {
                // Se o login for bem-sucedido, abre o dashboard e fecha esta tela.
                new TelaPrincipal().setVisible(true);
                dispose();
            } else {
                // Se as credenciais forem inválidas, exibe uma mensagem de erro.
                JOptionPane.showMessageDialog(this, "Login ou senha inválidos. Tente novamente.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            // Se ocorrer um erro de banco de dados, exibe uma mensagem amigável.
            JOptionPane.showMessageDialog(this, "Ocorreu uma falha ao conectar com o banco de dados.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Ponto de entrada para executar esta tela de forma independente.
     */
    public static void main(String[] args) {
        // Garante que a interface gráfica seja executada na thread correta do Swing.
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}