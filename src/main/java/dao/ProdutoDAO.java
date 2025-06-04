package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Categoria;
import modelo.Produto;

/**
 * Realiza a persistência de dados.
 */
public class ProdutoDAO {
    private CategoriaDAO categoriaDao = new CategoriaDAO();
    //Utilizado para retornar uma lista de Produtos.
    public ArrayList<Produto> minhaLista = new ArrayList<>();

    /**
     * Retorna a Lista de Produtos(objetos)
     */
    public ArrayList<Produto> getMinhaLista() {

        minhaLista.clear(); // Limpa nosso ArrayList

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_produtos");
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                double preco = res.getDouble("preco");
                String unidade = res.getString("unidade");
                int quantidade = res.getInt("quantidade");
                int quantidadeMinima = res.getInt("quantidade_minima");
                int quantidadeMaxima = res.getInt("quantidade_maxima");
                int idCategoria = res.getInt("id_categoria");
                
                Categoria categoria = categoriaDao.carregaCategoria(idCategoria);

                Produto objeto = new Produto(id, nome, preco, unidade, quantidade, quantidadeMinima, quantidadeMaxima, categoria);
                minhaLista.add(objeto);
            }
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return minhaLista;
    }

    public void setMinhaLista(ArrayList<Produto> minhaLista) {
        this.minhaLista = minhaLista;
    }

    /**
     * Retorna o maior id de um produto.
     */
    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_produtos");
            res.next();
            maiorID = res.getInt("id");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return maiorID;
    }

    /**
     * Retorna uma conexão com o banco de dados.
     */
    public Connection getConexao() {

        Connection connection = null;  //instância da conexão
        try {
            // Carregamento do JDBC Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            // Configurar a conexão
            String server = "localhost"; //caminho do MySQL
            String database = "db_produtos";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "Ln5n2skk3r1!";

            connection = DriverManager.getConnection(url, user, password);
            // Testando..
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }
            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    /**
     * Cadastra um novo produto.
     */
    public boolean insertProdutoBD(Produto objeto) {
        String sql = "INSERT INTO tb_produtos(id,nome,preco,unidade,quantidade,quantidade_minima,quantidade_maxima,categoria) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setDouble(3, objeto.getPreco());
            stmt.setString(4, objeto.getUnidade());
            stmt.setInt(5, objeto.getQuantidade());
            stmt.setInt(6, objeto.getQuantidadeMinima());
            stmt.setInt(7, objeto.getQuantidadeMaxima());
//            stmt.setInt(8, objeto.getCategoria());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Deleta um produto específico pelo seu campo ID
     */
    public boolean deleteProdutoBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_produtos WHERE id = " + id);
            stmt.close();

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return true;
    }

    /**
     * Edita um produto específico pelo seu campo ID
     */
    public boolean updateProdutoBD(Produto objeto) {

        String sql = "UPDATE tb_produtos set nome = ? ,preco = ? ,unidade = ? ,quantidade = ? ,quantidade_minima = ? ,quantidade_maxima = ?, id_categoria = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(2, objeto.getNome());
            stmt.setDouble(3, objeto.getPreco());
            
            stmt.setString(4, objeto.getUnidade());
            stmt.setInt(5, objeto.getQuantidade());
            stmt.setInt(6, objeto.getQuantidadeMinima());
            stmt.setInt(7, objeto.getQuantidadeMaxima());
            
            
//            stmt.setInt(8, objeto.getCategoria());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Carrega um produto pelo ID
     */
    public Produto carregaProduto(int id) {

        String nome = "";
        double preco = 0.0;
        String unidade = "";
        int quantidade = 0;
        int quantidadeMinima = 0;
        int quantidadeMaxima = 0;
        int categoriaId = 0;
        
        try {
            Statement stmt = this.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM tb_produtos WHERE id = " + id);
            res.next();

            nome = res.getString("nome");
            preco = res.getDouble("preco");
            unidade = res.getString("unidade");
            quantidade = res.getInt("quantidade");
            quantidadeMinima = res.getInt("quantidade_minima");
            quantidadeMaxima  = res.getInt("quantidade_maxima");
            categoriaId = res.getInt("id_categoria");

            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        CategoriaDAO categoriaDao = new CategoriaDAO();
        Categoria categoria = new Categoria();
        categoria = categoriaDao.carregaCategoria(categoriaId);
        Produto objeto = new Produto(id, nome, preco, unidade, quantidade, quantidadeMinima, quantidadeMaxima, categoria);
        return objeto;
    }
}
