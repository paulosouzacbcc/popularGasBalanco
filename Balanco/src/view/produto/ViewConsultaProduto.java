/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.produto;

import controller.ControllerProduto;
import facade.FacadeJpa;
import java.util.List;
import javax.swing.JOptionPane;
import model.Produto;
import util.Internal;
import util.MyDefaultTableModel;

/**
 *
 * @author Paulo
 */
public class ViewConsultaProduto extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewConsultaProduto
     */
    MyDefaultTableModel tableModel;

    public ViewConsultaProduto()
    {
        initComponents();
        iniciarTabela();
        recarregarTabela();
        Internal.retiraBotao(this);
        this.pack();

    }

    public void iniciarTabela()
    {
        tableModel = new MyDefaultTableModel(new String[]{"Nome", "Valor"}, 0, false);
        jTableProduto.setModel(tableModel);
    }

    public void preencherTabela(List<Produto> listProduto)
    {
        for (int i = 0; i < listProduto.size(); i++) {
            String[] linhas = new String[]{
                listProduto.get(i).getNome(),
                String.valueOf(listProduto.get(i).getValor())
            };
            tableModel.addRow(linhas);
        }
        jTableProduto.setModel(tableModel);
    }

    public void recarregarTabela()
    {
        iniciarTabela();
        preencherTabela(FacadeJpa.getInstance().getProduto().selectAllProdutos());
        jTextFieldBuscar.setText("");
    }

    public void buscarDigitado(String produto)
    {
        iniciarTabela();
        preencherTabela(FacadeJpa.getInstance().getProduto().findByNomeList(produto));
    }

    public Produto getProduto()
    {
        return FacadeJpa.getInstance().getProduto().findByNomeSingle(captureNomeLinhaTabela());

    }

    public String captureNomeLinhaTabela()
    {
        return jTableProduto.getValueAt(jTableProduto.getSelectedRow(), 0).toString();
    }

    public void showEditar()
    {
        ViewNovoProduto viewNovoProduto = new ViewNovoProduto(null, true);
        viewNovoProduto.editarProduto(getProduto());
        viewNovoProduto.setVisible(true);
    }

    public boolean excluirProduto()
    {
        boolean excluir = false;
        ControllerProduto controllerProduto = new ControllerProduto();
        excluir = controllerProduto.excluir(getProduto());

        if (excluir)
            return true;
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProduto = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButtonNovoProduto = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Buscar Produto:");

        jTextFieldBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextFieldBuscarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refresh3.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jTableProduto.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTableProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableProduto);

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excluir.png"))); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonNovoProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Novoproduto2.png"))); // NOI18N
        jButtonNovoProduto.setText("Novo Produto");
        jButtonNovoProduto.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonNovoProdutoActionPerformed(evt);
            }
        });

        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(146, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNovoProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExcluir)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonNovoProduto)
                    .addComponent(jButtonEditar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoProdutoActionPerformed
        ViewNovoProduto viewNovoProduto = new ViewNovoProduto(null, true);
        viewNovoProduto.cadastrarProduto();
        viewNovoProduto.setVisible(true);
        recarregarTabela();
    }//GEN-LAST:event_jButtonNovoProdutoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        recarregarTabela();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        buscarDigitado(jTextFieldBuscar.getText());
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEditarActionPerformed
    {//GEN-HEADEREND:event_jButtonEditarActionPerformed
        if (jTableProduto.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto na tabela.");
            return;
        }
        showEditar();
        recarregarTabela();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonExcluirActionPerformed
    {//GEN-HEADEREND:event_jButtonExcluirActionPerformed
        if(jTableProduto.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Selecione um produto na tabela");
            return;
        }
        if (excluirProduto())
            JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
        else JOptionPane.showMessageDialog(null, "Erro ao excluir.");
        recarregarTabela();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovoProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProduto;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
