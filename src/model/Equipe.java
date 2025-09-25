package model;

/**
 * Classe Model que representa a entidade Equipe.
 */
public class Equipe {
    private int id;
    private String nome;
    private String descricao;

    /**
     * Construtor completo, geralmente utilizado para criar objetos a partir de dados do banco.
     * @param id O ID da equipe.
     * @param nome O nome da equipe.
     * @param descricao A descrição da equipe.
     */
    public Equipe(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * Construtor para criar uma nova equipe, onde o ID ainda não foi gerado pelo banco.
     * @param nome O nome da equipe.
     * @param descricao A descrição da equipe.
     */
    public Equipe(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * Construtor vazio.
     */
    public Equipe() {
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a representação em String do objeto, que por padrão é o nome da equipe.
     * Útil para exibir em JComboBoxes e listas.
     * @return O nome da equipe.
     */
    @Override
    public String toString() {
        return this.getNome();
    }
}