package dao;

import factory.ConnectionFactory;
import model.Equipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo acesso aos dados da tabela 'equipes' no banco de dados.
 * Implementa as operações de CRUD para a entidade Equipe.
 */
public class EquipeDAO {

    /**
     * Salva uma nova equipe no banco de dados.
     * @param equipe O objeto do tipo Equipe com os dados a serem inseridos.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public void salvar(Equipe equipe) throws SQLException {
        String sql = "INSERT INTO equipes(nome, descricao) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, equipe.getNome());
            pstm.setString(2, equipe.getDescricao());

            pstm.execute();
        }
    }

    /**
     * Busca e retorna todas as equipes cadastradas no banco de dados.
     * @return Uma Lista de objetos Equipe.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public List<Equipe> getEquipes() throws SQLException {
        String sql = "SELECT * FROM equipes";
        List<Equipe> equipes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                // MUDANÇA: Usando o construtor completo, o código fica mais enxuto.
                Equipe equipe = new Equipe(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao")
                );
                equipes.add(equipe);
            }
        }
        return equipes;
    }
}