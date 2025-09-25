package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) {
        // --- INFORMAÇÕES DO BANCO DE DADOS ---
        String url = "jdbc:mysql://localhost:3306/gestao_projetos";
        String usuario = "root";

        // !! ALTERE AQUI PARA A SUA SENHA !!
        // Se você não definiu uma senha na instalação, deixe as aspas vazias: ""
        String senha = "flamengo";

        // --- TENTATIVA DE CONEXÃO ---
        try {
            // !! ADICIONE ESTA LINHA !!
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Tentando conectar ao banco de dados...");
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("--- SUCESSO! ---");
            System.out.println("Conexão com o banco de dados estabelecida!");
            conexao.close();
            System.out.println("Conexão fechada.");

        } catch (SQLException | ClassNotFoundException e) { // Adicionei ClassNotFoundException aqui
            System.out.println("--- FALHA! ---");
            System.out.println("Ocorreu um erro ao tentar conectar:");
            e.printStackTrace();
        }
    }
}