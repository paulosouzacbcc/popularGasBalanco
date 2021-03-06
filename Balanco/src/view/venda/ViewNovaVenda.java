/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.venda;

import controller.ControllerVenda;
import facade.FacadeJpa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Produto;
import model.Venda;
import util.CheckDefaultTableModel;
import util.Texto;

/**
 *
 * @author Paulo
 */
public class ViewNovaVenda extends javax.swing.JDialog {

    /**
     * Creates new form ViewNovaVenda
     */
    Venda venda = new Venda();

    boolean novaVenda = false;
    private DefaultComboBoxModel comboBoxModel;
    private CheckDefaultTableModel defaultTableModel;
    private int linhaSelected = -1;

    public ViewNovaVenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        popularComboBoxCadastrar();
        iniciarCheckTabela();
        preencherCheckTable();
        this.pack();
    }

    public void cadastrarNovaVenda() {
        setTitle("Cadastrar Nova Venda");
        novaVenda = true;
    }

    public void editarVenda(Venda vendaUsuario) {
        novaVenda = false;
        venda = vendaUsuario;

        editarPopularComboBox(vendaUsuario);
        jTextFieldValor.setText(String.valueOf(vendaUsuario.getValor()));
        jTextFieldDesconto.setText(String.valueOf(vendaUsuario.getDesconto()));
        jTextAreaObservacao.setText(vendaUsuario.getObservacao());

    }

    public void novaVenda(Cliente cliente) {
        novaVenda = true;
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        comboBoxModel.addElement(cliente.getNome());
        jComboBoxCliente.setModel(comboBoxModel);
        jComboBoxCliente.setEnabled(false);

    }

    public void iniciarCheckTabela() {
        defaultTableModel = new CheckDefaultTableModel(new String[]{"Selecionar", "Produto", "Valor", "Quantidade"}, 0, true);
        jTableProdutosCheckBox.setModel(defaultTableModel);

    }

    public void preencherCheckTable() {

        List<Produto> listProdutos = FacadeJpa.getInstance().getProduto().selectAllProdutos();
        for (int i = 0; i < listProdutos.size(); i++) {
            Object[] linhas = {
                false,
                listProdutos.get(i).getNome(),
                String.valueOf(listProdutos.get(i).getValor())
            };
            defaultTableModel.addRow(linhas);

        }
        jTableProdutosCheckBox.setModel(defaultTableModel);
        jTableProdutosCheckBox.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTableProdutosCheckBox.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTableProdutosCheckBox.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTableProdutosCheckBox.getColumnModel().getColumn(3).setPreferredWidth(100);
    }

    public void editarPopularComboBox(Venda venda) {
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        comboBoxModel.addElement(venda.getNomeCliente());
        jComboBoxCliente.setModel(comboBoxModel);
        jComboBoxCliente.setEnabled(false);
    }

    public void popularComboBoxCadastrar() {

        comboBoxModel = new DefaultComboBoxModel();
        List<Cliente> listCliente;
        listCliente = FacadeJpa.getInstance().getCliente().selectAllCliente();
        comboBoxModel.addElement("--Selecione um Cliente--");
        for (int i = 0; i < listCliente.size(); i++) {

            comboBoxModel.addElement(listCliente.get(i).getNome());
        }
        jComboBoxCliente.setModel(comboBoxModel);
    }

    public boolean validarCampos() {
        if (jComboBoxCliente.getSelectedItem() == "--Selecione um Cliente--") {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um cliente.");
            return false;
        }
        if (jTextFieldValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo ''Valor'' não pode ser vazio.");
            return false;
        }
        return true;
    }

    public void capturarProdutosCheckTable() {
        int TAM = jTableProdutosCheckBox.getRowCount();
        int Col = jTableProdutosCheckBox.getColumnCount();
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < TAM; i++) {

            if ((boolean) jTableProdutosCheckBox.getModel().getValueAt(i, 0) == true) {
                jTableProdutosCheckBox.getModel().getValueAt(i, 1).toString();

            }

        }

    }
    private double getValorProdutos() {
        int TAM = jTableProdutosCheckBox.getRowCount();
        int Col = jTableProdutosCheckBox.getColumnCount();
        double valorTotal = 0;
        double valorProduto = 0;
        int quantidade = 0;
        boolean validacao = false;
        ArrayList<String> arrayList = new ArrayList<>();
        viewQuantidadeVenda viewQuantidadeVenda = new viewQuantidadeVenda(null, true);

        linhaSelected = jTableProdutosCheckBox.getSelectedRow();
        

            if ((boolean) jTableProdutosCheckBox.getModel().getValueAt(linhaSelected, 0) == true) {
                
                
                viewQuantidadeVenda.setVisible(true);
                validacao = viewQuantidadeVenda.validaValor();
                

                
                    quantidade = viewQuantidadeVenda.getValueQuantidade();
                    valorProduto = Double.parseDouble(jTableProdutosCheckBox.getModel().getValueAt(linhaSelected, 2).toString());
                    jTableProdutosCheckBox.getModel().setValueAt(quantidade, linhaSelected, 3);
                    valorTotal += valorTotal + valorProduto * quantidade;
                
                

            }else {
                jTableProdutosCheckBox.getModel().setValueAt(0, linhaSelected, 3);
                String quantidadeTable = jTableProdutosCheckBox.getModel().getValueAt(0, 3).toString();
                if (quantidadeTable == null)
                    jTableProdutosCheckBox.getModel().setValueAt(false, linhaSelected, 0);
            }

        
        return valorTotal;

    }
    
    private void setValorVenda(){
        jTextFieldValor.setText(String.valueOf(getValorProdutos()));
    }

    public void cancelarQuantidade(){
        jTableProdutosCheckBox.getModel().setValueAt(false, linhaSelected, 0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxCliente = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProdutosCheckBox = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Cliente:");

        jComboBoxCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Valor:");

        jTextFieldValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValorKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Desconto:");

        jTextFieldDesconto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDescontoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Observação:");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextAreaObservacao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObservacao);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jTableProdutosCheckBox.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableProdutosCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProdutosCheckBoxMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableProdutosCheckBox);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDesconto)
                            .addComponent(jScrollPane1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldValor)
                            .addComponent(jComboBoxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSalvarActionPerformed
    {//GEN-HEADEREND:event_jButtonSalvarActionPerformed
        boolean save = false;
        if (validarCampos()) {

            venda.setData(new Date());
            venda.setDesconto(Double.parseDouble(jTextFieldDesconto.getText()));
            venda.setValor(Double.parseDouble(jTextFieldValor.getText()));
            venda.setObservacao(jTextAreaObservacao.getText());
            venda.setNomeCliente(jComboBoxCliente.getSelectedItem().toString());
            venda.setCliente(FacadeJpa.getInstance().getCliente().findByNomeSingle(jComboBoxCliente.getSelectedItem().toString()));
            double valorTotal = Double.parseDouble(jTextFieldValor.getText()) - Double.parseDouble(jTextFieldDesconto.getText());
            venda.setValorTotal(valorTotal);

            ControllerVenda controllerVenda = new ControllerVenda();
            if (novaVenda) {
                save = controllerVenda.criarNovaVenda(venda);
                JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
            } else {
                save = controllerVenda.editarVenda(venda);
                JOptionPane.showMessageDialog(null, "Editado com Sucesso!");
            }

            if (save)
                this.dispose();

        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextFieldValorKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldValorKeyTyped
    {//GEN-HEADEREND:event_jTextFieldValorKeyTyped
        Texto.somenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldValorKeyTyped

    private void jTextFieldDescontoKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldDescontoKeyTyped
    {//GEN-HEADEREND:event_jTextFieldDescontoKeyTyped
        Texto.somenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldDescontoKeyTyped

    private void jTableProdutosCheckBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProdutosCheckBoxMouseClicked
                setValorVenda();
    }//GEN-LAST:event_jTableProdutosCheckBoxMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewNovaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewNovaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewNovaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewNovaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewNovaVenda dialog = new ViewNovaVenda(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProdutosCheckBox;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables
}
