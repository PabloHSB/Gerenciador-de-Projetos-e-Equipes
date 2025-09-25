package view;

import dao.ProjetoDAO;
import dao.UsuarioDAO;
import model.Projeto;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException; // <-- Import necessário
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TelaCadastroProjeto extends JFrame {

    // --- Componentes ---
    private JTextField nomeField;
    private JTextArea descricaoArea;
    private JTextField dataInicioField;
    private JTextField dataTerminoField;
    private JComboBox<String> statusComboBox;
    private JComboBox<Usuario> gerenteComboBox;
    private JButton salvarButton;

    public TelaCadastroProjeto() {
        // ... (configurações da janela) ...
        setTitle("Cadastro de Novo Projeto");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Descrição:"));
        descricaoArea = new JTextArea(3, 20);
        panel.add(new JScrollPane(descricaoArea));

        panel.add(new JLabel("Data de Início (dd/MM/yyyy):"));
        dataInicioField = new JTextField();
        panel.add(dataInicioField);

        panel.add(new JLabel("Data de Término (dd/MM/yyyy):"));
        dataTerminoField = new JTextField();
        panel.add(dataTerminoField);

        panel.add(new JLabel("Status:"));
        String[] statusOptions = {"planejado", "em andamento", "concluido", "cancelado"};
        statusComboBox = new JComboBox<>(statusOptions);
        panel.add(statusComboBox);

        panel.add(new JLabel("Gerente Responsável:"));
        gerenteComboBox = new JComboBox<>();
        carregarGerentes(); // Renomeado para mais clareza
        panel.add(gerenteComboBox);

        salvarButton = new JButton("Salvar Projeto");
        panel.add(new JLabel());
        panel.add(salvarButton);

        add(panel);

        // Ação do botão salvar
        salvarButton.addActionListener(e -> salvarProjeto());
    }

    private void carregarGerentes() {
        // Agora a chamada ao DAO está dentro de um try-catch
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = usuarioDAO.getUsuarios();
            for (Usuario usuario : usuarios) {
                gerenteComboBox.addItem(usuario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar gerentes: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarProjeto() {
        // ... (validação de campos, etc.) ...

        try {
            // Coleta e conversão de dados
            String nome = nomeField.getText();
            String descricao = descricaoArea.getText();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInicio = formatoData.parse(dataInicioField.getText());
            Date dataTermino = formatoData.parse(dataTerminoField.getText());
            String status = (String) statusComboBox.getSelectedItem();
            Usuario gerenteSelecionado = (Usuario) gerenteComboBox.getSelectedItem();
            int idGerente = gerenteSelecionado.getId();

            // Criação do objeto
            Projeto novoProjeto = new Projeto();
            novoProjeto.setNome(nome);
            novoProjeto.setDescricao(descricao);
            novoProjeto.setDataInicio(dataInicio);
            novoProjeto.setDataTerminoPrevista(dataTermino);
            novoProjeto.setStatus(status);
            novoProjeto.setIdGerente(idGerente);

            // Chamada ao DAO (agora dentro do try-catch)
            ProjetoDAO projetoDAO = new ProjetoDAO();
            projetoDAO.salvar(novoProjeto);

            JOptionPane.showMessageDialog(this, "Projeto salvo com sucesso!");
            dispose();

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            // Aqui tratamos o erro que o DAO "lançou"
            JOptionPane.showMessageDialog(this, "Erro ao salvar projeto no banco de dados: " + ex.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroProjeto().setVisible(true));
    }
}