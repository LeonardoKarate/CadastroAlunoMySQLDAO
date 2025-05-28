package modelo;

import dao.ProdutoDAO;
import java.util.ArrayList;

/**
 * Classe que representa uma Aluno.
 */
public class Produto {

    /**
     * Nome do Produto.
     */
    private String nome;
    /**
     * preço do produto.
     */
    private double preco;
    /**
     * Unidade de medida do Produto.
     */
    private String unidade;
        /**
     * quantidade do produto em estoque;
     */
    private int quantidade;
        /**
     * quantidade minima do produto estipulado para o estoque.
     */
    private int quantidadeMinima;
        /**
     * quantidade maxima do produto estipulado para o estoque.
     */
    private int quantidadeMaxima;
        /**
     * Objeto dao manipulado.
     */
    private Categoria categoria;
        /**
     * Objeto dao manipulado.
     */
    private ProdutoDAO dao;
    
    /**
     * Construtor de Objeto Vazio.
     */
    public Produto() {
        this(0, "", 0, "", 0);
    }

    /**
     * Construtor com parâmetro.
     *
     * @param nome Nome do aluno.
     * @param preco preço do produto.
     * @param unidade unidade de medida do produto.
     * @param quantidade quantidade de produto em estoque.
     * @param quantidadeMinima quantidade minima recomendada de produto em estoque.
     * @param quantidadeMaxima quantidade maxima recomendada de produto em estoque.
     * @param categoria a categoria do produto.
     */
    public Produto(String nome, double preco, String unidade, int quantidade, int quantidadeMinima, int quantidadeMaxima, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeMaxima = quantidadeMaxima;
        this.categoria = categoria;
        this.dao = new ProdutoDAO();
    }

    // Métodos GET e SET
    /**
     * Retorna o curso do aluno.
     *
     * @return Uma String com o nome do curso do aluno.
     */

    /**
     * Modifica o curso do aluno.
     *
     * @param curso Uma string com o nome do curso do aluno.
     */
    public String toString() {
        return super.toString() + "curso=" + curso + ", fase=" + fase;
    }

    /*  ABAIXO OS MÉTODOS PARA USO JUNTO COM O DAO
        SIMULANDO A ESTRUTURA EM CAMADAS PARA USAR COM BANCOS DE DADOS.
     */
    /**
     * Retorna a Lista de Alunos(objetos).
     *
     * @return Um ArrayList com todos os Alunos.
     */
    public ArrayList<Produto> getMinhaLista() {
        return dao.getMinhaLista();
    }

    /**
     * Insere um novo aluno.
     *
     * @param nome O nome do aluno.
     * @param idade A idade do aluno.
     * @param curso O curso do aluno
     * @param fase A fase do aluno.
     * @return Verdadeiro ou falso se conseguiu fazer a inclusão.
     */
    public boolean insertAlunoBD(String nome, int idade, String curso, int fase) {
        int id = this.maiorID() + 1;
        Produto objeto = new Produto(id, nome, idade, curso, fase);
        dao.insertAlunoBD(objeto);
        return true;
    }

    /**
     * Deleta um aluno especÍfico pelo seu ID.
     *
     * @param id Id do aluno a ser excluído.
     * @return Verdadeiro ou falso se conseguiu fazer a exclusão.
     */
    public boolean deleteAlunoBD(int id) {
        dao.deleteAlunoBD(id);
        return true;
    }

    /**
     * Edita um aluno especÍfico pelo seu ID.
     *
     * @param id O id do aluno.
     * @param nome O nome do aluno.
     * @param idade A idade do aluno.
     * @param curso O curso do aluno
     * @param fase A fase do aluno.
     * @return Verdadeiro ou falso se conseguiu fazer a inclusão.
     */
    public boolean updateAlunoBD(int id, String nome, int idade, String curso, int fase) {
        Produto objeto = new Produto(id, nome, idade, curso, fase);
        dao.updateAlunoBD(objeto);
        return true;
    }

    /**
     * Carrega dados de um aluno especÍfico pelo seu ID.
     *
     * @param id O id do aluno a ser carregado.
     * @return Um objeto aluno preenchido.
     */
    public Produto carregaAluno(int id) {
        return dao.carregaAluno(id);
    }

    /**
     * Retorna o maior ID da nossa base de dados.
     *
     * @return Um inteiro com o maior valor de Id de aluno.
     */
    public int maiorID() {
        return dao.maiorID();
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the unidade
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * @param unidade the unidade to set
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the quantidadeMinima
     */
    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    /**
     * @param quantidadeMinima the quantidadeMinima to set
     */
    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    /**
     * @return the quantidadeMaxima
     */
    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    /**
     * @param quantidadeMaxima the quantidadeMaxima to set
     */
    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
