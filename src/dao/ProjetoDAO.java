package dao;

import factory.ConnectionFactory;
import model.Projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Acesso a Dados (DAO) para a entidade Projeto.
 * Encapsula toda a lógica de interação com a tabela 'projetos' do banco de dados.
 */
public class ProjetoDAO {

    /**
     * Insere um novo projeto no banco de dados.
     * @param projeto O objeto Projeto contendo os dados a serem salvos.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public void salvar(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_termino_prevista, status, id_gerente) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, projeto.getNome());
            pstm.setString(2, projeto.getDescricao());
            pstm.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            pstm.setDate(4, new java.sql.Date(projeto.getDataTerminoPrevista().getTime()));
            pstm.setString(5, projeto.getStatus());
            pstm.setInt(6, projeto.getIdGerente());

            pstm.execute();
        }
    }

    /**
     * Busca e retorna todos os projetos cadastrados no banco de dados.
     * @return Uma Lista de objetos Projeto.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public List<Projeto> getProjetos() throws SQLException {
        String sql = "SELECT * FROM projetos";
        List<Projeto> projetos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataInicio(rs.getDate("data_inicio"));
                projeto.setDataTerminoPrevista(rs.getDate("data_termino_prevista"));
                projeto.setStatus(rs.getString("status"));
                projeto.setIdGerente(rs.getInt("id_gerente"));
                projetos.add(projeto);
            }
        }
        return projetos;
    }
}