package view;

import dao.ProjetoDAO;
import model.Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Interface gráfica (GUI) para listar os projetos cadastrados.
 */
public class TelaListagemProjetos extends JFrame {

    private JTable tabelaProjetos;
    private DefaultTableModel tableModel;
    private JButton atualizarButton;

    public TelaListagemProjetos() {
        setTitle("Listagem de Projetos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"ID", "Nome", "Status", "Data de Início", "Prazo Final"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaProjetos = new JTable(tableModel);
        tabelaProjetos.setFont(new Font("Arial", Font.PLAIN, 14));
        tabelaProjetos.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabelaProjetos);

        atualizarButton = new JButton("Atualizar Lista");

        add(scrollPane, BorderLayout.CENTER);
        add(atualizarButton, BorderLayout.SOUTH);

        atualizarButton.addActionListener(e -> carregarDadosNaTabela());

        carregarDadosNaTabela();
    }

    /**
     * Busca os dados no DAO e popula a tabela, tratando possíveis erros de banco de dados.
     */
    private void carregarDadosNaTabela() {
        tableModel.setRowCount(0); // Limpa a tabela antes de carregar novos dados
        ProjetoDAO projetoDAO = new ProjetoDAO();

        try {
            // Tenta buscar a lista de projetos
            List<Projeto> projetos = projetoDAO.getProjetos();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            // Popula a tabela com os dados
            for (Projeto p : projetos) {
                Object[] linha = {
                        p.getId(),
                        p.getNome(),
                        p.getStatus(),
                        formatoData.format(p.getDataInicio()),
                        formatoData.format(p.getDataTerminoPrevista())
                };
                tableModel.addRow(linha);
            }
        } catch (SQLException e) {
            // Se o DAO lançar um erro, exibe uma mensagem amigável para o usuário
            JOptionPane.showMessageDialog(this, "Erro ao carregar projetos do banco de dados.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaListagemProjetos().setVisible(true));
    }
}