package DAL;

import EL.Usuarios;
import EL.vUsuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAL_Usuarios {

    public static List<vUsuarios> vUsuarios() {
        try {
            Conexion SqlConn = new Conexion();
            var Query = "Select * from vUsuarios";
            PreparedStatement stmt = SqlConn.Get().prepareStatement(Query);
            ResultSet Rs = stmt.executeQuery();
            List<vUsuarios> Listado = new ArrayList<>();
            while (Rs.next()) {
                vUsuarios vUsuario = new vUsuarios();
                vUsuario.IdUsuario = Rs.getInt("IdUsuario");
                vUsuario.Nombre = Rs.getString("Nombre");
                vUsuario.Login = Rs.getString("Login");
                vUsuario.Correo = Rs.getString("Correo");
                vUsuario.IdRol = Rs.getByte("IdRol");
                vUsuario.Rol = Rs.getString("Rol");
                Listado.add(vUsuario);
            }
            return Listado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }

    public static boolean ExiteLogin(String UserName,int IdUsuario) {
        try {
            Conexion SqlConn = new Conexion();
            var Query = "Select * from vUsuarios where Login like " + "'" + UserName + "' "+" and IdUsuario <> "+IdUsuario;;
            PreparedStatement stmt = SqlConn.Get().prepareStatement(Query);
            ResultSet Rs = stmt.executeQuery();
            vUsuarios User = new vUsuarios();
            while (Rs.next()) {
                User.IdUsuario = Rs.getInt("IdUsuario");
                User.Nombre = Rs.getString("Nombre");
                User.Login = Rs.getString("Login");
                User.Correo = Rs.getString("Correo");
                User.IdRol = Rs.getByte("IdRol");
                User.Rol = Rs.getString("Rol");
            }
            return (User.IdUsuario > 0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return false;
    }

    public static boolean ExiteCorreo(String Correo,int IdUsuario) {
        try {
            Conexion SqlConn = new Conexion();
            var Query = "Select * from vUsuarios where Correo like " + "'" + Correo + "' "+" and IdUsuario <> "+IdUsuario;
            PreparedStatement stmt = SqlConn.Get().prepareStatement(Query);
            ResultSet Rs = stmt.executeQuery();
            vUsuarios User = new vUsuarios();
            while (Rs.next()) {
                User.IdUsuario = Rs.getInt("IdUsuario");
                User.Nombre = Rs.getString("Nombre");
                User.Login = Rs.getString("Login");
                User.Correo = Rs.getString("Correo");
                User.IdRol = Rs.getByte("IdRol");
                User.Rol = Rs.getString("Rol");
            }
            return (User.IdUsuario > 0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return false;
    }

    public static int Insert(Usuarios Entidad) {
        try {
            Conexion SqlConn = new Conexion();
            var Query = "{CALL InsertarUsuario(?,?,?,?,?,?)}";
            PreparedStatement stmt = SqlConn.Get().prepareStatement(Query);
            stmt.setString(1,Entidad.Nombre);
            stmt.setString(2,Entidad.Login);
            stmt.setBytes(3,Entidad.Password);
            stmt.setString(4,Entidad.Correo);
            stmt.setByte(5,Entidad.IdRol);
            stmt.setInt(6,1);
            
            ResultSet Rs = stmt.executeQuery();
           int result = 0;
            while (Rs.next()) {
                result = Rs.getInt("Resultado");
            }
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return 0;
    }
    public static int Update(Usuarios Entidad,boolean resetPassword) {
        try {
            Conexion SqlConn = new Conexion();
            var Query = "{CALL ActualizarUsuario(?,?,?,?,?,?,?,?)}";
            PreparedStatement stmt = SqlConn.Get().prepareStatement(Query);
            stmt.setInt(1,Entidad.IdUsuario);
            stmt.setString(2,Entidad.Nombre);
            stmt.setString(3,Entidad.Login);
            stmt.setBytes(4,Entidad.Password);
            stmt.setString(5,Entidad.Correo);
            stmt.setByte(6,Entidad.IdRol);
            stmt.setInt(7,1);
            stmt.setBoolean(8, resetPassword);
            
            ResultSet Rs = stmt.executeQuery();
           int result = 0;
            while (Rs.next()) {
                result = Rs.getInt("Resultado");
            }
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return 0;
    }
    public static int Delete(Usuarios Entidad) {
        try {
            Conexion SqlConn = new Conexion();
            var Query = "{CALL AnularUsuario(?,?)}";
            PreparedStatement stmt = SqlConn.Get().prepareStatement(Query);
            stmt.setInt(1,Entidad.IdUsuario);
            stmt.setInt(2,1);
            ResultSet Rs = stmt.executeQuery();
           int result = 0;
            while (Rs.next()) {
                result = Rs.getInt("Resultado");
            }
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return 0;
    }

}
