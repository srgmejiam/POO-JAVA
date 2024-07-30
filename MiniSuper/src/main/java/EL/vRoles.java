package EL;

import java.util.Objects;

public class vRoles {
    public byte IdRol;
    public String Rol;
    @Override
    public String toString() {
        return Rol; // Esto es lo que se mostrará en el JComboBox
    }
     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        vRoles roles = (vRoles) obj;
        return IdRol == roles.IdRol;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.IdRol;
        hash = 59 * hash + Objects.hashCode(this.Rol);
        return hash;
    }
}
