package model; // Ou o nome do seu pacote de modelos

import java.util.Date;

/**
 * Classe Model que representa a entidade Projeto.
 * Seus atributos correspondem às colunas da tabela 'projetos' no banco de dados.
 */
public class Projeto {

    // Atributos
    private int id;
    private String nome;
    private String descricao;
    private Date dataInicio;
    private Date dataTerminoPrevista;
    private String status;
    private int idGerente; // Armazena o ID do usuário gerente

    // Construtor (opcional, mas boa prática)
    public Projeto() {
    }

    // Getters e Setters
    // Métodos públicos para acessar e modificar os atributos privados de forma controlada.

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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTerminoPrevista() {
        return dataTerminoPrevista;
    }

    public void setDataTerminoPrevista(Date dataTerminoPrevista) {
        this.dataTerminoPrevista = dataTerminoPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    @Override
    public String toString() {
        // Isso fará com que qualquer JComboBox ou lista
        // que use um objeto Projeto, mostre o seu nome.
        return this.getNome();
    }
}