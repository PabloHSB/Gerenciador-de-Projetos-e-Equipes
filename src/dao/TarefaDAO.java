package dao;

import factory.ConnectionFactory;
import model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe de Acesso a Dados (DAO) para a entidade Tarefa.
 * Encapsula a lógica de interação com a tabela 'tarefas' no banco de dados.
 */
public class TarefaDAO {

    /**
     * Insere uma nova tarefa no banco de dados.
     * @param tarefa O objeto Tarefa contendo os dados a serem salvos.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public void salvar(Tarefa tarefa) throws SQLException {
        // As colunas data_inicio_real e data_fim_real não são incluídas no INSERT inicial,
        // pois elas normalmente são preenchidas depois (UPDATE).
        String sql = "INSERT INTO tarefas (titulo, descricao, status, data_inicio_prevista, data_fim_prevista, id_projeto, id_responsavel) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, tarefa.getTitulo());
            pstm.setString(2, tarefa.getDescricao());
            pstm.setString(3, tarefa.getStatus());
            pstm.setDate(4, new java.sql.Date(tarefa.getDataInicioPrevista().getTime()));
            pstm.setDate(5, new java.sql.Date(tarefa.getDataFimPrevista().getTime()));
            pstm.setInt(6, tarefa.getIdProjeto());
            pstm.setInt(7, tarefa.getIdResponsavel());

            pstm.execute();
        }
    }

    // Futuramente, aqui entrariam outros métodos como:
    // public List<Tarefa> getTarefasPorProjeto(int idProjeto) { ... }
    // public void atualizar(Tarefa tarefa) { ... }
    // public void deletar(int id) { ... }
}