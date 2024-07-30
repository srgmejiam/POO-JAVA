package UI;

import EL.vRoles;
import DAL.DAL_Roles;
import DAL.DAL_Usuarios;
import EL.Roles;
import EL.Usuarios;
import EL.vUsuarios;
import Utility.General;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdministracionUsuarios extends javax.swing.JFrame {

    private int IdRegistro = 0;
    private byte IdRol = 0;

    public AdministracionUsuarios() {
        initComponents();
        cargarRoles();
        CargarGrid();
    }

    private void ocultarColumna(int indice, JTable Grid) {
        Grid.getColumnModel().getColumn(indice).setMinWidth(0);
        Grid.getColumnModel().getColumn(indice).setMaxWidth(0);
        Grid.getColumnModel().getColumn(indice).setWidth(0);
        Grid.getColumnModel().getColumn(indice).setPreferredWidth(0);
    }

    private void cargarRoles() {
        try {
            vRoles PlaceHolder = new vRoles();
            PlaceHolder.IdRol = 0;
            PlaceHolder.Rol = "-- Seleccione --";
            ddlRol.addItem(PlaceHolder);

            List<vRoles> Listado = new ArrayList();
            Listado = DAL_Roles.Lista();

            if (Listado != null) {
                for (vRoles Rol : Listado) {
                    ddlRol.addItem(Rol);
                }
                return;
            }
            JOptionPane.showMessageDialog(null, "Error al cargar el componente roles");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }

    private void CargarGrid() {
        try {
            String[] columnNames = {"ID", "Nombre Completo", "Login", "Correo", "IdRol", "Rol"};
            NonEditableTableModel model = new NonEditableTableModel(columnNames, 0);
            for (vUsuarios vUsuario : DAL_Usuarios.vUsuarios()) {
                model.addRow(new Object[]{
                    vUsuario.IdUsuario,
                    vUsuario.Nombre,
                    vUsuario.Login,
                    vUsuario.Correo,
                    vUsuario.IdRol,
                    vUsuario.Rol
                });
            }

            gvUsuarios.setModel(model);
            ocultarColumna(0, gvUsuarios);
            ocultarColumna(4, gvUsuarios);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private boolean Validar() {
        if ("".equals(txtNombre.getText()) || txtNombre.getText() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if ("".equals(txtLogin.getText()) || txtLogin.getText() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese el login", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (DAL_Usuarios.ExiteLogin(txtLogin.getText(), IdRegistro)) {
            JOptionPane.showMessageDialog(null, "El login ingresado ya existe", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ("".equals(txtCorreo.getText()) || txtCorreo.getText() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese el correo", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!General.isValidEmail(txtCorreo.getText())) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo válido", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (DAL_Usuarios.ExiteCorreo(txtCorreo.getText(), IdRegistro)) {
            JOptionPane.showMessageDialog(null, "El correo ingresado ya existe", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!(ddlRol.getSelectedIndex() > 0)) {
            JOptionPane.showMessageDialog(null, "Ingrese el rol", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (("".equals(txtPassword.getText()) || txtPassword.getText() == null) && !(IdRegistro > 0)) {
            JOptionPane.showMessageDialog(null, "Ingrese la contraseña", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void ResetForm() {
        txtNombre.setText("");
        txtLogin.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
        ddlRol.setSelectedIndex(0);
        IdRegistro = 0;
        IdRol = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        ddlRol = new javax.swing.JComboBox<vRoles>();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        gvUsuarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administración de Usuarios");
        setName("AdminUsuarios"); // NOI18N
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Administración de Usuarios");

        jLabel2.setText("Nombre");

        jLabel3.setText("Login");

        jLabel4.setText("Correo");

        jLabel5.setText("Rol");

        txtNombre.setName("txtNombre"); // NOI18N

        txtCorreo.setName("txtCorreo"); // NOI18N

        txtLogin.setName("txtLogin"); // NOI18N

        ddlRol.setName("ddlRol"); // NOI18N

        btnGuardar.setText("Guardar");
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAnular.setText("Anular");
        btnAnular.setName("btnAnular"); // NOI18N
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        jLabel6.setText("Contraseña");

        txtPassword.setText("jPasswordField1");

        gvUsuarios.setAutoCreateRowSorter(true);
        gvUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Login", "Correo", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        gvUsuarios.setColumnSelectionAllowed(false);
        gvUsuarios.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gvUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        gvUsuarios.setUpdateSelectionOnSort(false);
        gvUsuarios.setVerifyInputWhenFocusTarget(false);
        gvUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gvUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gvUsuarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar)
                                .addGap(18, 18, 18)
                                .addComponent(btnAnular))
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                        .addComponent(txtPassword)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtLogin)
                                    .addComponent(ddlRol, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ddlRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnAnular)
                    .addComponent(btnGuardar))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        ResetForm();
        CargarGrid();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void gvUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gvUsuariosMouseClicked
        int Index = gvUsuarios.getSelectedRow();
        IdRegistro = (int) gvUsuarios.getValueAt(Index, 0);
        txtNombre.setText((String) gvUsuarios.getValueAt(Index, 1));
        txtLogin.setText((String) gvUsuarios.getValueAt(Index, 2));
        txtCorreo.setText((String) gvUsuarios.getValueAt(Index, 3));
        IdRol = (byte) gvUsuarios.getValueAt(Index, 4);
        ddlRol.setSelectedItem(DAL_Roles.GetRol(IdRol));
    }//GEN-LAST:event_gvUsuariosMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (Validar()) {

            if (IdRegistro > 0) {
                int Result = JOptionPane.showConfirmDialog(null, "Seguro desea actualizar el registro", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
                if (Result == JOptionPane.OK_OPTION) {
                    //Actualizamos registro
                    boolean ResetPass = false;
                    Usuarios User = new Usuarios();
                    User.IdUsuario = IdRegistro;
                    User.Nombre = txtNombre.getText();
                    User.Login = txtLogin.getText();
                    String Pass = txtPassword.getText();
                    if (Pass != null && !"".equals(Pass)) {
                        User.Password = Pass.getBytes();
                        ResetPass = true;
                    }
                    User.Correo = txtCorreo.getText();

                    vRoles Rol = new vRoles();
                    Rol = (vRoles) ddlRol.getSelectedItem();
                    User.IdRol = Rol.IdRol;
                    if (DAL_Usuarios.Update(User, ResetPass) > 0) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado con exito!!!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        CargarGrid();
                        ResetForm();
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Error al actualizar el registro", "Error", JOptionPane.ERROR_MESSAGE);
                    ResetForm();
                    return;
                }
                JOptionPane.showMessageDialog(null, "Operación Cancelada");
            }
            if (IdRegistro == 0) {
                int Result = JOptionPane.showConfirmDialog(null, "Seguro desea agregar el registro", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
                if (Result == JOptionPane.OK_OPTION) {
                    Usuarios User = new Usuarios();
                    User.Nombre = txtNombre.getText();
                    User.Login = txtLogin.getText();
                    User.Password = txtPassword.getText().getBytes();
                    User.Correo = txtCorreo.getText();

                    vRoles Rol = new vRoles();
                    Rol = (vRoles) ddlRol.getSelectedItem();
                    User.IdRol = Rol.IdRol;

                    if (DAL_Usuarios.Insert(User) > 0) {
                        JOptionPane.showMessageDialog(null, "Registro agregado con exito!!!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        CargarGrid();
                        ResetForm();
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Error al insertar el registro", "Error", JOptionPane.ERROR_MESSAGE);
                    ResetForm();
                    return;
                }
                JOptionPane.showMessageDialog(null, "Operación Cancelada");
                ResetForm();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        if (IdRegistro > 0) {
            int Result = JOptionPane.showConfirmDialog(null, "Seguro desea anular el registro", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
            if (Result == JOptionPane.OK_OPTION) {
                //Anulamos registro
                Usuarios User = new Usuarios();
                User.IdUsuario = IdRegistro;
                if (DAL_Usuarios.Delete(User) > 0) {
                    JOptionPane.showMessageDialog(null, "Registro anulado con exito!!!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    CargarGrid();
                    ResetForm();
                    return;
                }
                JOptionPane.showMessageDialog(null, "Error al anular el registro", "Error", JOptionPane.ERROR_MESSAGE);
                ResetForm();
                return;
            }
            JOptionPane.showMessageDialog(null, "Operación Cancelada");
        }
        JOptionPane.showMessageDialog(null, "Seleccione un registro");
    }//GEN-LAST:event_btnAnularActionPerformed

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
            java.util.logging.Logger.getLogger(AdministracionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministracionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministracionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministracionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministracionUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<vRoles> ddlRol;
    private javax.swing.JTable gvUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

    class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // Todas las celdas no son editables
            return false;
        }
    }

}
