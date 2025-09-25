package test;

import dao.UsuarioDAO;
import model.Usuario;

// Se as palavras abaixo ficarem vermelhas, clique nelas, aperte Alt + Enter e escolha "Add JUnit 5.8.2 to classpath".
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * Classe de teste para a classe UsuarioDAO.
 * Cada método anotado com @Test é um caso de teste independente.
 */
public class UsuarioDAOTest {

    private UsuarioDAO usuarioDAO;

    // Este método é executado ANTES de cada teste.
    @BeforeEach
    public void setUp() {
        usuarioDAO = new UsuarioDAO();
    }

    /**
     * Testa o cenário de sucesso da autenticação.
     */
    @Test
    public void deveAutenticarUsuarioComCredenciaisValidas() throws SQLException {
        // Cenário (Arrange): Usamos um usuário que sabemos que existe no banco.
        String login = "gerente";
        String senha = "123";

        // Ação (Act): Chamamos o método a ser testado.
        Usuario usuario = usuarioDAO.autenticarUsuario(login, senha);

        // Verificação (Assert): Verificamos se o resultado é o esperado.
        Assertions.assertNotNull(usuario, "O usuário não deveria ser nulo para credenciais válidas.");
        Assertions.assertEquals("Gerente Padrão", usuario.getNomeCompleto(), "O nome do usuário retornado está incorreto.");
    }

    /**
     * Testa o cenário de falha da autenticação.
     */
    @Test
    public void naoDeveAutenticarUsuarioComCredenciaisInvalidas() throws SQLException {
        // Cenário (Arrange): Usamos credenciais que sabemos que NÃO existem no banco.
        String login = "usuario_inexistente";
        String senha = "senha_errada";

        // Ação (Act): Chamamos o método a ser testado.
        Usuario usuario = usuarioDAO.autenticarUsuario(login, senha);

        // Verificação (Assert): O teste PASSA se o usuário retornado for NULO.
        Assertions.assertNull(usuario, "O usuário deveria ser nulo para credenciais inválidas.");
    }
}