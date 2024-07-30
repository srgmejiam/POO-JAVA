package DAL;
import EL.vRoles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAL_Roles 
{
    public static List<vRoles> Lista()
    {
        try 
        {
            Conexion SqlConn = new Conexion();
            var Query = "Select * from vRoles";
            PreparedStatement stmt = SqlConn.Get().prepareStatement(Query);
            ResultSet Rs = stmt.executeQuery();          
            List<vRoles> Listado = new ArrayList<>();
             while (Rs.next()) {
                vRoles rol = new vRoles();
                rol.IdRol = Rs.getByte("IdRol");
                rol.Rol = Rs.getString("Rol");
               Listado.add(rol);
            }
            return Listado;
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
     public static vRoles GetRol(int IdRol)
    {
        try 
        {
            Conexion SqlConn = new Conexion();
            var Query = "Select * from vRoles where IdRol = " + IdRol;
            PreparedStatement stmt = SqlConn.Get().prepareStatement(Query);
            ResultSet Rs = stmt.executeQuery();          
            vRoles rol = new vRoles();
             while (Rs.next()) {
                rol.IdRol = Rs.getByte("IdRol");
                rol.Rol = Rs.getString("Rol");
            }
            return rol;
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
}
