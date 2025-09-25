package dao;

import factory.ConnectionFactory;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Acesso a Dados (DAO) para a entidade Usuario.
 * Encapsula toda a lógica de interação com a tabela 'usuarios' do banco de dados.
 */
public class UsuarioDAO {

    /**
     * Insere um novo usuário no banco de dados.
     * @param usuario O objeto Usuario contendo os dados a serem salvos.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome_completo, cpf, email, cargo, login, senha, perfil) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, usuario.getNomeCompleto());
            pstm.setString(2, usuario.getCpf());
            pstm.setString(3, usuario.getEmail());
            pstm.setString(4, usuario.getCargo());
            pstm.setString(5, usuario.getLogin());
            pstm.setString(6, usuario.getSenha());
            pstm.setString(7, usuario.getPerfil());

            pstm.execute();
        }
    }

    /**
     * Busca e retorna todos os usuários cadastrados no banco de dados.
     * @return Uma Lista de objetos Usuario.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public List<Usuario> getUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome_completo"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("cargo"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("perfil")
                ));
            }
        }
        return usuarios;
    }

    /**
     * Autentica um usuário, verificando se o login e a senha correspondem a um registro no banco.
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return Um objeto Usuario se as credenciais forem válidas; caso contrário, null.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public Usuario autenticarUsuario(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, login);
            pstm.setString(2, senha);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    // Se encontrou, cria e retorna o objeto Usuario
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome_completo"),
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getString("cargo"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getString("perfil")
                    );
                }
            }
        }
        // Se não encontrou ou ocorreu um erro, retorna null
        return null;
    }
}