package view;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Interface gráfica (GUI) para o cadastro de novos usuários.
 */
public class TelaCadastroUsuario extends JFrame {

    // --- Componentes da Interface ---
    private JTextField campoNome, campoCpf, campoEmail, campoCargo, campoLogin;
    private JPasswordField campoSenha;
    private JComboBox<String> comboPerfil;
    private JButton botaoSalvar;

    public TelaCadastroUsuario() {
        // --- Configurações da Janela ---
        setTitle("Cadastro de Novo Usuário");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Painel com Gerenciador de Layout ---
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- Adicionando Componentes ao Painel ---
        panel.add(new JLabel("Nome Completo:"));
        campoNome = new JTextField();
        panel.add(campoNome);

        panel.add(new JLabel("CPF:"));
        campoCpf = new JTextField();
        panel.add(campoCpf);

        panel.add(new JLabel("Email:"));
        campoEmail = new JTextField();
        panel.add(campoEmail);

        panel.add(new JLabel("Cargo:"));
        campoCargo = new JTextField();
        panel.add(campoCargo);

        panel.add(new JLabel("Login:"));
        campoLogin = new JTextField();
        panel.add(campoLogin);

        panel.add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        panel.add(campoSenha);

        panel.add(new JLabel("Perfil:"));
        String[] perfis = {"administrador", "gerente", "colaborador"};
        comboPerfil = new JComboBox<>(perfis);
        panel.add(comboPerfil);

        botaoSalvar = new JButton("Salvar");
        panel.add(new JLabel()); // Célula vazia para alinhar
        panel.add(botaoSalvar);

        add(panel);

        // --- Lógica de Ações ---
        botaoSalvar.addActionListener(e -> salvarUsuario());
        adicionarNavegacaoComEnter();
    }

    /**
     * Coleta os dados da tela, valida e chama o DAO para salvar o novo usuário.
     */
    private void salvarUsuario() {
        // Validação simples
        if (campoNome.getText().isEmpty() || campoLogin.getText().isEmpty() || campoSenha.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Nome, Login e Senha são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Coleta dos dados
        Usuario novoUsuario = new Usuario(
                campoNome.getText(),
                campoCpf.getText(),
                campoEmail.getText(),
                campoCargo.getText(),
                campoLogin.getText(),
                new String(campoSenha.getPassword()),
                (String) comboPerfil.getSelectedItem()
        );

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.salvar(novoUsuario);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            limparCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário no banco de dados.", "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adiciona a funcionalidade de pular para o próximo campo com a tecla Enter.
     */
    private void adicionarNavegacaoComEnter() {
        campoNome.addActionListener(e -> campoCpf.requestFocusInWindow());
        campoCpf.addActionListener(e -> campoEmail.requestFocusInWindow());
        campoEmail.addActionListener(e -> campoCargo.requestFocusInWindow());
        campoCargo.addActionListener(e -> campoLogin.requestFocusInWindow());
        campoLogin.addActionListener(e -> campoSenha.requestFocusInWindow());
        campoSenha.addActionListener(e -> comboPerfil.requestFocusInWindow());
        comboPerfil.addActionListener(e -> botaoSalvar.doClick());
    }

    /**
     * Limpa todos os campos do formulário após o salvamento.
     */
    private void limparCampos() {
        campoNome.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoCargo.setText("");
        campoLogin.setText("");
        campoSenha.setText("");
        comboPerfil.setSelectedIndex(0);
    }

    /**
     * Método principal para testar esta tela de forma isolada.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroUsuario().setVisible(true));
    }
}