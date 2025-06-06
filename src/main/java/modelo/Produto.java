package modelo;

import dao.ProdutoDAO;
import java.util.ArrayList;

/**
 * Classe que representa uma Aluno.
 */
public class Produto {

    /**
     * Identificador do Produto.
     */
    private int id;
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
        this(0, "", 0.0, "", 0, 0, 0, null);
    }

    /**
     * Construtor com parâmetro.
     *
     * @param id Identificador do Produto.
     * @param nome Nome do Produto.
     * @param preco preço do produto.
     * @param unidade unidade de medida do produto.
     * @param quantidade quantidade de produto em estoque.
     * @param quantidadeMinima quantidade minima recomendada de produto em
     * estoque.
     * @param quantidadeMaxima quantidade maxima recomendada de produto em
     * estoque.
     * @param categoria a categoria do produto.
     */
    public Produto(int id, String nome, double preco, String unidade, int quantidade, int quantidadeMinima, int quantidadeMaxima, Categoria categoria) {
        this.id = id;
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
     * @return um int com o indentificador do produto.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return Uma String com o nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Modifica o nome do produto.
     *
     * @param nome Uma string com o nome do produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return um double com o preço do produto.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Modifica o Preço do produto.
     *
     * @param preco um double com o preço do produto.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna o tipo de unidade do Produto.
     *
     * @return uma String com o tipo da unidade do produto.
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * Modifica a unidade do produto.
     *
     * @param unidade uma String com o tipo da unidade do produto.
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * Retorna a quantidade de produto em estoque.
     *
     * @return um int com a quantidade de produtos em estoque.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Modifica a quantidade de produto em estoque.
     *
     * @param quantidade um int com a quantidade de produtos em estoque.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna a quantidade minima recomendada em estoque.
     *
     * @return um int com a quantidade Minima em estoque recomendada.
     */
    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    /**
     * Modifica a quantidade minima recomendada do produto em estoque.
     *
     * @param quantidadeMinima uma int com a quantidade minima do produto
     * recomendada em estoque.
     */
    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    /**
     * Retorna a quantidade maxima do produto em estoque.
     *
     * @return um int com a quantidade Maxima do produto em estoque.
     */
    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    /**
     * Modifica a quantidade maxima de produto recomendade em estoque.
     *
     * @param quantidadeMaxima uma String com a quantidade Maxima do produto
     * recomendada em estoque.
     */
    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return uma Categoria com as especificações da categoria do produto.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Modifica a categoria do produto.
     *
     * @param categoria uma Categoria com as especificações da categoria do
     * produto.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

//    public String toString() {
//        return super.toString() + "curso=" + curso + ", fase=" + fase;
//    }

    /*  ABAIXO OS MÉTODOS PARA USO JUNTO COM O DAO
        SIMULANDO A ESTRUTURA EM CAMADAS PARA USAR COM BANCOS DE DADOS.
     */
    /**
     * Retorna a Lista de Alunos(objetos).
     *
     * @return Um ArrayList com todos os Produtos.
     */
    public ArrayList<Produto> getMinhaLista() {
        return dao.getMinhaLista();
    }

    /**
     * Insere um novo produto.
     *
     * @param nome Nome do produto.
     * @param preco preço do produto.
     * @param unidade unidade de medida do produto.
     * @param quantidade quantidade de produto em estoque.
     * @param quantidadeMinima quantidade minima recomendada de produto em
     * estoque.
     * @param quantidadeMaxima quantidade maxima recomendada de produto em
     * estoque.
     * @param categoria a categoria do produto.
     * @return Verdadeiro ou falso se conseguiu fazer a inclusão.
     */
    public boolean inserProdutoBD(String nome, double preco, String unidade, int quantidade, int quantidadeMinima, int quantidadeMaxima, Categoria categoria) {
        int id = this.maiorID() + 1;
        Produto objeto = new Produto(id, nome, preco, unidade, quantidade, quantidadeMinima, quantidadeMaxima, categoria);
        dao.insertProdutoBD(objeto);
        return true;
    }

    /**
     * Deleta um Produto especÍfico pelo seu ID.
     *
     * @param id Id do Produto a ser excluído.
     * @return Verdadeiro ou falso se conseguiu fazer a exclusão.
     */
    public boolean deleteProdutoBD(int id) {
        dao.deleteProdutoBD(id);
        return true;
    }

    /**
     * Edita um produto especÍfico pelo seu ID.
     *
     * @param id Identificação do Produto.
     * @param nome Nome do Produto.
     * @param preco preço do produto.
     * @param unidade unidade de medida do produto.
     * @param quantidade quantidade de produto em estoque.
     * @param quantidadeMinima quantidade minima recomendada de produto em
     * estoque.
     * @param quantidadeMaxima quantidade maxima recomendada de produto em
     * estoque.
     * @param categoria a categoria do produto.
     * @return Verdadeiro ou falso se conseguiu fazer a alteração.
     */
    public boolean updateProdutoBD(int id, String nome, double preco, String unidade, int quantidade, int quantidadeMinima, int quantidadeMaxima, Categoria categoria) {
        Produto objeto = new Produto(id, nome, preco, unidade, quantidade, quantidadeMinima, quantidadeMaxima, categoria);
        dao.updateProdutoBD(objeto);
        return true;
    }

    /**
     * Carrega dados de um Produto especÍfico pelo seu ID.
     *
     * @param id O id do Produto a ser carregado.
     * @return Um objeto aluno preenchido.
     */
    public Produto carregaProduto(int id) {
        return dao.carregaProduto(id);
    }

    /**
     * Retorna o maior ID da nossa base de dados.
     *
     * @return Um inteiro com o maior valor de Id de aluno.
     */
    public int maiorID() {
        return dao.maiorID();
    }
}
