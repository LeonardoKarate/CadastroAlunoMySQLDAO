/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author leonardoNunes
 */
public class Categoria {

    /**
     * identificador do produto.
     */
    private int id;
    /**
     * nome da categoria.
     */
    private String nome;
    /**
     * tamanho da categoria.
     */
    private String tamanho;
    /**
     * tipo da embalagem.
     */
    private String embalagem;
    
    /**
     * Construtor de Objeto Vazio.
     */
    public Categoria() {
        this( 0, "", "", "");
    }

    /**
     * Construtor com parâmetro.
     *
     * @param nome Nome do da categoria.
     * @param tamanho tamanho da categoria.
     * @param embalagem tipo da empalagem.
     */
    public Categoria(int id, String nome, String tamanho, String embalagem) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }
    
        public Categoria(String nome, String tamanho, String embalagem) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    // Métodos GET e SET
    /** 
     * Retorna o identificador da categoraa.
     * 
     * @return um int com o identificador da categoria.
     */
    public int getId() {
        return id;
    }

    /**
     * retorna o nome da categoria.
     * 
     * @return uma String com o nome da categoria.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Modifica o nome da categoria.
     * 
     * @param nome uma String com o nome da categoira.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o tamanho da categoria.
     * 
     * @return uma String com o tamanho da categoria.
     */
    public String getTamanho() {
        return tamanho;
    }

    /**
     * Modifica o tamanho da categoria.
     * 
     * @param tamanho uma String com o tamanho da categoria.
     */
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Retorna o tipo da  embalagem.
     * 
     * @return uma String com o tipo da embalagem.
     */
    public String getEmbalagem() {
        return embalagem;
    }

    /**
     * Modifica o tipo da embalagem.
     * 
     * @param embalagem uma String com o tipo da embalagem.
     */
    public void setEmbalagem(String embalagem) {
        this.embalagem = embalagem;
    }
    
    public String toString() {
    return nome; // ou qualquer campo que você quer mostrar no combo
}
}
