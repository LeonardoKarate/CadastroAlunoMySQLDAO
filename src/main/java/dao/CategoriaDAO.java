package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Categoria;
import modelo.CategoriaComContagem;

/**
 * Realiza a persistência de dados.
 */
public class CategoriaDAO {

    //Utilizado para retornar uma lista de Categorias.
    public ArrayList<Categoria> minhaLista = new ArrayList<>();
    public ArrayList<CategoriaComContagem> listaComContagem = new ArrayList<>();

    /**
     * Retorna a Lista de Categorias(objetos)
     */
    public ArrayList<Categoria> getMinhaLista() {

        minhaLista.clear(); // Limpa nosso ArrayList

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_categorias");
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                String tamanho = res.getString("tamanho");
                String embalagem = res.getString("embalagem");

                Categoria objeto = new Categoria(id, nome, tamanho, embalagem);
                minhaLista.add(objeto);
            }
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return minhaLista;
    }

    public void setMinhaLista(ArrayList<Categoria> minhaLista) {
        this.minhaLista = minhaLista;
    }

    /**
     * Retorna o maior id de um aluno.
     */
    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_categorias");
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
            String database = "db_Produtos";
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
     * Cadastra uma nova categoria.
     */
    public boolean insertCategoriaBD(Categoria objeto) {
        String sql = "INSERT INTO tb_categorias(nome,tamanho,embalagem) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTamanho());
            stmt.setString(3, objeto.getEmbalagem());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Deleta uma categoria específico pelo seu campo ID
     */
    public boolean deleteCategoriaBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_categorias WHERE id = " + id);
            stmt.close();

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return true;
    }

    /**
     * Edita uma categoria específicada pelo seu campo ID
     */
    public boolean updateCategoriaBD(Categoria objeto) {

        String sql = "UPDATE tb_categorias set nome = ? ,tamanho = ? ,embalagem = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTamanho());
            stmt.setString(3, objeto.getEmbalagem());
            stmt.setInt(4, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Carrega uma categoria pelo ID
     */
    public Categoria carregaCategoria(int id) {

        String nome = "";
        String tamanho = "";
        String embalagem = "";
        try {
            Statement stmt = this.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM tb_categorias WHERE id = " + id);
            res.next();

            nome = res.getString("nome");
            tamanho = res.getString("tamanho");
            embalagem = res.getString("embalagem");

            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        Categoria objeto = new Categoria(id, nome, tamanho, embalagem);

        return objeto;
    }

    public ArrayList<CategoriaComContagem> getCategoriaDistinta() {

        minhaLista.clear(); // Limpa nosso ArrayList

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT nome,(SELECT COUNT(*) FROM tb_produtos WHERE tb_produtos.id_categoria = tb_categorias.id) AS total FROM tb_categorias;");
            while (res.next()) {

                String nome = res.getString("nome");
                int total = res.getInt("total");

                CategoriaComContagem objeto = new CategoriaComContagem(nome, total);
                listaComContagem.add(objeto);
            }
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return listaComContagem;
    }
}
