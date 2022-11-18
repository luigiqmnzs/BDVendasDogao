/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Clientes;
import model.ItensDeVendas;
import model.Vendas;
import persistence.ClientesDAO;
import persistence.ItensDeVendasDAO;
import persistence.VendasDAO;
import util.tableModel;

/**
 *
 * @author Sir Parish
 */
public class ConsultarClientesUI extends javax.swing.JFrame {

    /**
     * Creates new form ListaGruposUI
     */
    
    
    public ConsultarClientesUI() throws SQLException {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width, screenSize.height);
        prepararTable();
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
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        buscarText = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        botaoAlterar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jPanel3.setLayout(new java.awt.GridLayout(3, 3));

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel12);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel10);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel7);

        buscarText.setText("Código");
        jPanel3.add(buscarText);

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });
        jPanel3.add(botaoBuscar);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel8);

        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });
        jPanel3.add(botaoAlterar);

        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });
        jPanel3.add(botaoExcluir);

        jPanel1.add(jPanel3);

        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        tabela.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabela);

        jPanel2.add(jScrollPane1);

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    ClientesUI ui = new ClientesUI();
    ui.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        if(!(buscarText.getText()).equalsIgnoreCase("")){
            ClientesDAO dao = new ClientesDAO();
            tableModel model = new tableModel(0,0);
            boolean produtoExiste = false;
            try {
                for(Clientes produto : dao.listar()) {
                    if((produto.getCodigo()+"").equalsIgnoreCase(buscarText.getText())){
                        produtoExiste = true;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConsultarClientesUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(produtoExiste){
                tabela.setModel(model);
                model.addColumn("Código");
        model.addColumn("Nome");
        model.addColumn("Endereço");
        model.addColumn("Telefone");
                model.isCellEditable(1, 1);
                try {
                    for(Clientes produto : dao.listar()) {
                        if(produto.getCodigo() == Integer.parseInt(buscarText.getText())){
                            String[] grupoAux = {produto.getCodigo()+"",produto.getNome(),produto.getEndereco(), produto.getTelefone()};
                            model.addRow(grupoAux);
                        }
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultarClientesUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Código de produto não encontrado!");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Digite um código para pesquisar seu preguiçoso!");
        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        ClientesDAO dao = new ClientesDAO();
        int row = tabela.getSelectedRow();
        int codigo = 0;
        if(tabela.getSelectedRowCount() > 1 || tabela.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Selecione um cliente por vez!");
        }else{
            try {
                for (Clientes produto : dao.listar()) {
                    if(produto.getCodigo() == Integer.parseInt(tabela.getValueAt(row, 0)+"")){
                        codigo = produto.getCodigo();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConsultarClientesUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            Clientes p = dao.buscar(codigo);
            AlterarClienteUI ui = new AlterarClienteUI();
            ui.limpar();
            ui.setCliente(p);
            ui.setarTF();
            ui.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int row = tabela.getSelectedRow();
        int id = 0;
        ClientesDAO dao = new ClientesDAO();
        VendasDAO daoV = new VendasDAO();
        boolean podeExcluir = true;
        if(tabela.getSelectedRowCount() > 1 || tabela.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Selecione um cliente por vez!");
        }else{
            Object[] opcoes = {"Sim", "Não"};
            int optionPane = JOptionPane.showOptionDialog(this, "Você deseja excluir esse cliente?", "" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,opcoes, opcoes[0]);
            if(optionPane == JOptionPane.YES_OPTION){
                try {
                    for (Clientes produto : dao.listar()) {
                        if(produto.getCodigo() == Integer.parseInt(tabela.getValueAt(row, 0)+"")){
                            id = produto.getCodigo();
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultarClientesUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    for (Vendas vendas : daoV.listar()) {
                        if(vendas.getCodigoCliente() == id){
                            podeExcluir = false;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultarClientesUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(podeExcluir){
                    dao.deletar(id);
                    ConsultarClientesUI ui = null;
                    try {
                        ui = new ConsultarClientesUI();
                    } catch (SQLException ex) {
                        Logger.getLogger(ConsultarClientesUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ui.setVisible(true);
                    this.setVisible(false);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Esse produto não pode ser excluído pois já foi usado em uma venda!");
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_botaoExcluirActionPerformed

    /*
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultarClientesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarClientesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarClientesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarClientesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ConsultarClientesUI().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultarClientesUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
   
public void prepararTable() throws SQLException{
        ClientesDAO dao = new ClientesDAO();
        tableModel model = new tableModel(0,0);
        tabela.setModel(model);
        model.addColumn("Código");
        model.addColumn("Nome");
        model.addColumn("Endereço");
        model.addColumn("Telefone");
        model.isCellEditable(1, 1);
        for(Clientes cliente : dao.listar()) {
            String[] grupoAux = {cliente.getCodigo()+"",cliente.getNome(),cliente.getEndereco(),cliente.getTelefone()};
            model.addRow(grupoAux);
        }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JTextField buscarText;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}