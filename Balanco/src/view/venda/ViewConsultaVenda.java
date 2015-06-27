/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.venda;

import facade.FacadeJpa;
import java.util.List;
import model.Venda;
import util.Internal;
import util.MyDefaultTableModel;
import util.Texto;

/**
 *
 * @author Paulo
 */
public class ViewConsultaVenda extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewConsultaVenda
     */
    MyDefaultTableModel tableModel;

    public ViewConsultaVenda()
    {
        initComponents();
        iniciarTabela();
        recarregarTabela();
        Internal.retiraBotao(this);
    }

    public void iniciarTabela()
    {
        tableModel = new MyDefaultTableModel(new String[]{"Id", "Cliente", "Valor", "Desconto", "Data"}, 0, false);
        jTableVenda.setModel(tableModel);
    }

    public void preencherTabela(List<Venda> listVenda)
    {
        for (int i = 0; i < listVenda.size(); i++) {
            String[] linhas = new String[]{
                String.valueOf(listVenda.get(i).getVendaPK().getIdvenda()),
                listVenda.get(i).getNomeCliente(),
                String.valueOf(listVenda.get(i).getValor()),
                String.valueOf(listVenda.get(i).getDesconto()),
                Texto.formataData(listVenda.get(i).getData())};
            tableModel.addRow(linhas);

        }
        jTableVenda.setModel(tableModel);
    }

    public void recarregarTabela()
    {
        iniciarTabela();
        preencherTabela(FacadeJpa.getInstance().getVenda().selectAllVenda());
    }

    public void buscarVendaDigitada(String nome)
    {
        iniciarTabela();
        preencherTabela(FacadeJpa.getInstance().getVenda().findByNomeList(nome));
    }

    public Venda getVenda()
    {
        Venda venda = new Venda();
        venda = FacadeJpa.getInstance().getVenda().findByNomeSingle(Texto.getLinhaTable(jTableVenda));

        System.out.println(venda);
        return venda;
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
        jTextFieldBuscarVenda = new javax.swing.JTextField();
        jButtonRecarregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVenda = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButtonNovaVenda = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Buscar Venda:");

        jTextFieldBuscarVenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldBuscarVenda.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextFieldBuscarVendaActionPerformed(evt);
            }
        });

        jButtonRecarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refresh3.png"))); // NOI18N
        jButtonRecarregar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonRecarregarActionPerformed(evt);
            }
        });

        jTableVenda.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTableVenda.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableVenda.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTableVendaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableVenda);

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excluir.png"))); // NOI18N
        jButtonExcluir.setText("Excluir");

        jButtonNovaVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/novaVenda.png"))); // NOI18N
        jButtonNovaVenda.setText("Nova Venda");
        jButtonNovaVenda.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonNovaVendaActionPerformed(evt);
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
                .addComponent(jTextFieldBuscarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRecarregar)
                .addContainerGap(80, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonNovaVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExcluir))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldBuscarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRecarregar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonNovaVenda))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaVendaActionPerformed
        ViewNovaVenda viewNovaVenda = new ViewNovaVenda(null, true);
        viewNovaVenda.cadastrarNovaVenda();
        viewNovaVenda.setVisible(true);
        recarregarTabela();
    }//GEN-LAST:event_jButtonNovaVendaActionPerformed

    private void jTextFieldBuscarVendaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextFieldBuscarVendaActionPerformed
    {//GEN-HEADEREND:event_jTextFieldBuscarVendaActionPerformed

        buscarVendaDigitada(jTextFieldBuscarVenda.getText());
    }//GEN-LAST:event_jTextFieldBuscarVendaActionPerformed

    private void jButtonRecarregarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonRecarregarActionPerformed
    {//GEN-HEADEREND:event_jButtonRecarregarActionPerformed
        recarregarTabela();
    }//GEN-LAST:event_jButtonRecarregarActionPerformed

    private void jTableVendaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableVendaMouseClicked
    {//GEN-HEADEREND:event_jTableVendaMouseClicked
        if (evt.getClickCount() >= 2) {
            ViewNovaVenda viewNovaVenda = new ViewNovaVenda(null, true);
            viewNovaVenda.editarVenda(getVenda());
            viewNovaVenda.setVisible(true);
            recarregarTabela();
        }

    }//GEN-LAST:event_jTableVendaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovaVenda;
    private javax.swing.JButton jButtonRecarregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVenda;
    private javax.swing.JTextField jTextFieldBuscarVenda;
    // End of variables declaration//GEN-END:variables
}
