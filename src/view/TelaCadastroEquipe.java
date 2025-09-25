package view;

import dao.EquipeDAO;
import model.Equipe;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Classe responsável pela interface gráfica (GUI) para o cadastro de novas equipes.
 */
public class TelaCadastroEquipe extends JFrame {

    // --- Componentes da Interface ---
    private JTextField campoNome;
    private JTextArea campoDescricao;
    private JButton botaoSalvar;

    /**
     * Construtor que inicializa a janela e seus componentes.
     */
    public TelaCadastroEquipe() {
        // --- Configurações da Janela ---
        setTitle("Cadastro de Nova Equipe");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        setLocationRelativeTo(null); // Centraliza na tela

        // --- Painel com Gerenciador de Layout ---
        // Usando GridLayout para um formulário simples e organizado.
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Margem

        // --- Criação e Adição dos Componentes ---
        panel.add(new JLabel("Nome da Equipe:"));
        campoNome = new JTextField();
        panel.add(campoNome);

        panel.add(new JLabel("Descrição:"));
        campoDescricao = new JTextArea();
        // Adicionamos a área de texto a um JScrollPane para ter barra de rolagem
        panel.add(new JScrollPane(campoDescricao));

        botaoSalvar = new JButton("Salvar");
        panel.add(new JLabel()); // Célula vazia para alinhamento
        panel.add(botaoSalvar);

        // Adiciona o painel à janela
        add(panel);

        // --- Ação do Botão Salvar ---
        botaoSalvar.addActionListener(e -> {
            // Validação simples para campos vazios
            if (campoNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome da equipe é obrigatório.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Coleta os dados preenchidos na tela
            String nome = campoNome.getText();
            String descricao = campoDescricao.getText();
            Equipe novaEquipe = new Equipe(nome, descricao);
            EquipeDAO equipeDAO = new EquipeDAO();

            try {
                // Tenta salvar a nova equipe no banco de dados
                equipeDAO.salvar(novaEquipe);
                JOptionPane.showMessageDialog(this, "Equipe cadastrada com sucesso!");

                // Limpa os campos da tela para um novo cadastro
                campoNome.setText("");
                campoDescricao.setText("");

            } catch (SQLException ex) {
                // Captura o erro específico do banco de dados e exibe uma mensagem amigável
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar equipe no banco de dados.", "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Mantém o log técnico no console para o desenvolvedor
            }
        });
    }

    /**
     * Método principal para testar esta tela de forma isolada.
     */
    public static void main(String[] args) {
        // Garante que a interface gráfica seja executada na thread correta do Swing
        SwingUtilities.invokeLater(() -> new TelaCadastroEquipe().setVisible(true));
    }
}