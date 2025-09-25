package view;

import dao.ProjetoDAO;
import dao.TarefaDAO;
import dao.UsuarioDAO;
import model.Projeto;
import model.Tarefa;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Representa a tela para cadastro de uma nova Tarefa.
 */
public class TelaCadastroTarefa extends JFrame {

    // --- Componentes da Interface ---
    private JTextField tituloField;
    private JTextArea descricaoArea;
    private JComboBox<Projeto> projetoComboBox;
    private JComboBox<Usuario> responsavelComboBox;
    private JTextField dataInicioField;
    private JTextField dataFimField;
    private JButton salvarButton;

    public TelaCadastroTarefa() {
        // --- Configurações da Janela ---
        setTitle("Cadastro de Nova Tarefa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Painel e Layout ---
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Título:"));
        tituloField = new JTextField();
        panel.add(tituloField);

        panel.add(new JLabel("Descrição:"));
        descricaoArea = new JTextArea(3, 20);
        panel.add(new JScrollPane(descricaoArea));

        panel.add(new JLabel("Projeto:"));
        projetoComboBox = new JComboBox<>();
        panel.add(projetoComboBox);

        panel.add(new JLabel("Responsável:"));
        responsavelComboBox = new JComboBox<>();
        panel.add(responsavelComboBox);

        panel.add(new JLabel("Data de Início (dd/MM/yyyy):"));
        dataInicioField = new JTextField();
        panel.add(dataInicioField);

        panel.add(new JLabel("Data de Término (dd/MM/yyyy):"));
        dataFimField = new JTextField();
        panel.add(dataFimField);

        salvarButton = new JButton("Salvar Tarefa");
        panel.add(new JLabel()); // Célula vazia
        panel.add(salvarButton);

        add(panel);

        salvarButton.addActionListener(e -> salvarTarefa());

        carregarProjetos();
        carregarUsuarios();
    }

    /**
     * Busca os projetos no banco de dados e popula o JComboBox correspondente.
     */
    private void carregarProjetos() {
        try {
            ProjetoDAO projetoDAO = new ProjetoDAO();
            List<Projeto> projetos = projetoDAO.getProjetos();
            for (Projeto projeto : projetos) {
                projetoComboBox.addItem(projeto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar projetos do banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Busca os usuários no banco de dados e popula o JComboBox correspondente.
     */
    private void carregarUsuarios() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = usuarioDAO.getUsuarios();
            for (Usuario usuario : usuarios) {
                responsavelComboBox.addItem(usuario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários do banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Orquestra o processo de salvamento, validando, criando o objeto e chamando o DAO.
     */
    private void salvarTarefa() {
        // Validação de campos
        Projeto projetoSelecionado = (Projeto) projetoComboBox.getSelectedItem();
        Usuario responsavelSelecionado = (Usuario) responsavelComboBox.getSelectedItem();
        if (tituloField.getText().isEmpty() || dataInicioField.getText().isEmpty() || dataFimField.getText().isEmpty() || projetoSelecionado == null || responsavelSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Chama o método auxiliar para criar o objeto a partir dos dados da tela
            Tarefa novaTarefa = criarTarefaAPartirDaTela();

            // Chama o DAO para persistir o objeto no banco
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefaDAO.salvar(novaTarefa);

            JOptionPane.showMessageDialog(this, "Tarefa cadastrada com sucesso!");
            dispose();

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use dd/MM/yyyy.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar a tarefa no banco de dados.", "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Coleta os dados dos componentes da tela e cria um objeto Tarefa.
     * @return um objeto Tarefa preenchido.
     * @throws ParseException se o formato da data for inválido.
     */
    private Tarefa criarTarefaAPartirDaTela() throws ParseException {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo(tituloField.getText());
        novaTarefa.setDescricao(descricaoArea.getText());
        novaTarefa.setStatus("pendente");

        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        novaTarefa.setDataInicioPrevista(formatoData.parse(dataInicioField.getText()));
        novaTarefa.setDataFimPrevista(formatoData.parse(dataFimField.getText()));

        Projeto projetoSelecionado = (Projeto) projetoComboBox.getSelectedItem();
        novaTarefa.setIdProjeto(projetoSelecionado.getId());

        Usuario responsavelSelecionado = (Usuario) responsavelComboBox.getSelectedItem();
        novaTarefa.setIdResponsavel(responsavelSelecionado.getId());

        return novaTarefa;
    }

    /**
     * Ponto de entrada para executar esta tela de forma independente para testes.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroTarefa().setVisible(true));
    }
}