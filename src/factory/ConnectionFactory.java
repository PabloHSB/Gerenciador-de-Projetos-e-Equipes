package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por criar e gerenciar a conexão com o banco de dados.
 * Segue o padrão de projeto "Factory".
 */
public class ConnectionFactory {

    // Constantes com as informações de conexão
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gestao_projetos";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "flamengo";

    /**
     * Cria e retorna uma conexão ativa com o banco de dados.
     * Este método agora "lança" uma SQLException em caso de falha na conexão,
     * delegando o tratamento de erro para a camada que o chama (DAO).
     *
     * @return um objeto do tipo Connection se a conexão for bem-sucedida.
     * @throws SQLException Se a conexão com o banco de dados falhar.
     */
    public static Connection createConnectionToMySQL() throws SQLException {
        // Tenta estabelecer a conexão usando as credenciais definidas.
        // Se DriverManager não conseguir conectar, ele mesmo lançará uma SQLException.
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
}