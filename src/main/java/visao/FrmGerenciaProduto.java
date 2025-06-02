
package visao;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Produto;
import dao.ProdutoDAO;
import modelo.Categoria;
import dao.CategoriaDAO;

public class FrmGerenciaProduto extends javax.swing.JFrame {

    private ProdutoDAO produtoDAO;
    private CategoriaDAO categoriaDAO;

    public FrmGerenciaProduto() {
        initComponents();
        try {
            produtoDAO = new ProdutoDAO();
            categoriaDAO = new CategoriaDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao inicializar DAOs: " + e.getMessage());
            produtoDAO = null;
            categoriaDAO = null;
        }
        carregarCategorias();
        carregarTabela();
    }

    private void carregarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        if (categoriaDAO != null) {
            // try {
                // categorias = categoriaDAO.buscarTodas(); // Se não existir, gera stub
            // } catch (Exception e) {
            //    JOptionPane.showMessageDialog(this, "Erro ao carregar categorias: " + e.getMessage());
            //}
        }
        cbCategoria.removeAllItems();
        for (Categoria c : categorias) {
            cbCategoria.addItem(c);
        }
    }

    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
        modelo.setRowCount(0);
        List<Produto> produtos = new ArrayList<>();
        if (produtoDAO != null) {
            try {
                produtos = produtoDAO.getMinhaLista(); // usa método real
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + e.getMessage());
            }
        }
        for (Produto p : produtos) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getUnidade(),
                p.getQuantidade(),
                p.getQuantidadeMinima(),
                p.getQuantidadeMaxima(),
                (p.getCategoria() != null) ? p.getCategoria().getNome() : "N/A"
            });
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtPreco.setText("");
        txtUnidade.setText("");
        txtQuantidade.setText("");
        txtQtdMinima.setText("");
        txtQtdMaxima.setText("");
        cbCategoria.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtUnidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtQtdMinima = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtQtdMaxima = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciar Produtos");

        jLabel1.setText("Nome:");
        jLabel2.setText("Preço:");
        jLabel3.setText("Categoria:");
        jLabel4.setText("Unidade:");
        jLabel5.setText("Quantidade:");
        jLabel6.setText("Qtd Mínima:");
        jLabel7.setText("Qtd Máxima:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> btnSalvarActionPerformed(evt));

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(evt -> btnExcluirActionPerformed(evt));

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Nome", "Preço", "Unidade", "Qtd", "Qtd Min", "Qtd Max", "Categoria"
            }
        ));
        jScrollPane1.setViewportView(tabelaProdutos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQtdMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQtdMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtQtdMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtQtdMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        if (produtoDAO == null) {
            JOptionPane.showMessageDialog(this, "Operação indisponível: Sem conexão com o banco.");
            return;
        }
        String nome = txtNome.getText();
        String precoStr = txtPreco.getText();
        String unidade = txtUnidade.getText();
        String qtdStr = txtQuantidade.getText();
        String qtdMinStr = txtQtdMinima.getText();
        String qtdMaxStr = txtQtdMaxima.getText();

        if (nome.isEmpty() || precoStr.isEmpty() || unidade.isEmpty() || qtdStr.isEmpty() ||
            qtdMinStr.isEmpty() || qtdMaxStr.isEmpty() || cbCategoria.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);
            int qtd = Integer.parseInt(qtdStr);
            int qtdMin = Integer.parseInt(qtdMinStr);
            int qtdMax = Integer.parseInt(qtdMaxStr);
            Categoria categoria = (Categoria) cbCategoria.getSelectedItem();

            Produto produto = new Produto(produtoDAO.maiorID() + 1, nome, preco, unidade, qtd, qtdMin, qtdMax, categoria);
            produtoDAO.insertProdutoBD(produto);
            JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!");
            limparCampos();
            carregarTabela();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique os valores numéricos!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if (produtoDAO == null) {
            JOptionPane.showMessageDialog(this, "Operação indisponível: Sem conexão com o banco.");
            return;
        }
        int linhaSelecionada = tabelaProdutos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (int) tabelaProdutos.getValueAt(linhaSelecionada, 0);
            try {
                produtoDAO.deleteProdutoBD(id);
                JOptionPane.showMessageDialog(this, "Produto excluído!");
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
        }
    }

    private void tabelaProdutosMouseClicked(java.awt.event.MouseEvent evt) {
        // edição futura ou stub
    }

    private javax.swing.JButton btnExcluir, btnSalvar;
    private javax.swing.JComboBox<Categoria> cbCategoria;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JTextField txtNome, txtPreco, txtUnidade, txtQuantidade, txtQtdMinima, txtQtdMaxima;
}
