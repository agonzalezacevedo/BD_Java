package proyectobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author agonzalezacevedo
 */
public class Conexion {

    String url = "C:\\Users\\funny\\Documents\\Daniel Castelao\\PROG\\bd_programacion.db";
    Connection connect;

    public void connect() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            System.err.println("No ha sido posible conectarse a la base de datos\n" + ex.getMessage());
        }
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveAlumno(Alumno alumno) {
        try {
            PreparedStatement st = connect.prepareStatement("insert into alumnos (dni, nombre, apellido1, apellido2, fecha_alta) values (?,?,?,?,?)");
            st.setString(1, alumno.getDni());
            st.setString(2, alumno.getNombre());
            st.setString(3, alumno.getApellido1());
            st.setString(4, alumno.getApellido2());
            st.setString(5, alumno.getFecha_alta());
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void amosarAlumnos() {
        ResultSet result = null;
        try {

            PreparedStatement st = connect.prepareStatement("select * from alumnos");
            result = st.executeQuery();
            while (result.next()) {
                System.out.print("Dni: ");
                System.out.println(result.getString("dni"));

                System.out.print("Nombre: ");
                System.out.println(result.getString("nombre"));

                System.out.print("Apellido1: ");
                System.out.println(result.getString("apellido1"));

                System.out.print("Apellido2: ");
                System.out.println(result.getString("apellido2"));

                System.out.print("Fecha: ");
                System.out.println(result.getString("fecha_alta"));

                System.out.println("=======================");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void actualizarAlumno() throws SQLException{
        Statement act = connect.createStatement();
        act.execute("update alumnos set nombre='Javier', apellido1='Gonzalez', apellido2='Martinez', fecha_alta='2015/06/08' where dni='32743004T'");
        
    }
    
}
