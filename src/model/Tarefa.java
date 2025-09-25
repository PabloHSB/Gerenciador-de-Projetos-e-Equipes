package model;

import java.util.Date;

/**
 * Classe Model que representa a entidade Tarefa.
 * Seus atributos correspondem às colunas da tabela 'tarefas' no banco de dados.
 */
public class Tarefa {

    // --- Atributos ---
    private int id;
    private String titulo;
    private String descricao;
    private String status;
    private Date dataInicioPrevista;
    private Date dataFimPrevista;
    private Date dataInicioReal;
    private Date dataFimReal;
    private int idProjeto;
    private int idResponsavel;

    // --- Construtores ---

    /**
     * Construtor vazio, útil para criar o objeto e preencher com setters.
     */
    public Tarefa() {
    }

    /**
     * Construtor completo, útil para criar objetos a partir de dados vindos do banco.
     */
    public Tarefa(int id, String titulo, String descricao, String status, Date dataInicioPrevista, Date dataFimPrevista, Date dataInicioReal, Date dataFimReal, int idProjeto, int idResponsavel) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataInicioPrevista = dataInicioPrevista;
        this.dataFimPrevista = dataFimPrevista;
        this.dataInicioReal = dataInicioReal;
        this.dataFimReal = dataFimReal;
        this.idProjeto = idProjeto;
        this.idResponsavel = idResponsavel;
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataInicioPrevista() {
        return dataInicioPrevista;
    }

    public void setDataInicioPrevista(Date dataInicioPrevista) {
        this.dataInicioPrevista = dataInicioPrevista;
    }

    public Date getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(Date dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }

    public Date getDataInicioReal() {
        return dataInicioReal;
    }

    public void setDataInicioReal(Date dataInicioReal) {
        this.dataInicioReal = dataInicioReal;
    }

    public Date getDataFimReal() {
        return dataFimReal;
    }

    public void setDataFimReal(Date dataFimReal) {
        this.dataFimReal = dataFimReal;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    /**
     * Retorna a representação em String do objeto, que por padrão é o título da tarefa.
     * @return O título da tarefa.
     */
    @Override
    public String toString() {
        return this.getTitulo();
    }
}