package proyectobd;

import java.sql.SQLException;

/**
 * @author agonzalezacevedo
 */
public class ProyectoBD {

    public static void main(String[] args) throws SQLException {
        Conexion con = new Conexion();
//        Alumno alumno = new Alumno("32743004T","Gabriel","Villar","Sanchez","2016/11/23");
        con.connect();
//        con.actualizarAlumno();
        con.amosarAlumnos();
//        con.saveAlumno(alumno);
        con.close();
    }

}
