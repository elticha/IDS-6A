/*
    Conexion a DB2 usando JDBC
 */
package practica;

import com.ibm.db2.jcc.DBTimestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author fernando
 */
public class Practica {

    public static void main(String[] args) throws SQLException, SQLException {
        Scanner input = new Scanner(System.in);
        String jdbcClassName = "com.ibm.db2.jcc.DB2Driver";
        String IP_SERVER = null;
        String PUERTO = null;
        String DB_NAME = null;
        //String url="jdbc:db2://localhost:50000/exampledb";
        String user = null;
        String password = null;
        int numeroDeIteraciones = 0;

        System.out.println("*** Conexion al manejador DB2 usando JDBC ***\n\n\n");
        System.out.print("Ingrese la direccion IP del servidor: ");
        IP_SERVER = input.nextLine();
        System.out.print("\nIngrese el puerto para realizar la conexion [ 50000 ] : ");
        PUERTO = input.nextLine();
        System.out.print("\nIngrese el nombre de la base de datos: ");
        DB_NAME = input.nextLine();

        System.out.println("\n\n*** [ L O G I N ] ***\n\n");

        System.out.print("Ingrese el nombre de usuario: ");
        user = input.nextLine();
        System.out.print("\nIngrese la contraseña para " + user + ": ");
        password = input.nextLine();

        String url = "jdbc:db2://" + IP_SERVER + ":" + PUERTO + "/" + DB_NAME + ":currentSchema=LUIS FERNANDO";

        System.out.println("\n\n***************************\n\nRealizando conexion\n\n");

        Connection coneccion = null;

        try {
            //cargar la clase en memoria
            Class.forName(jdbcClassName);
            //establecer conexion
            coneccion = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (coneccion != null) {
                System.out.println("Conexion exitosa");
            } else {
                System.out.println("La conexion no se pudo establecer, valide sus datos...");
            }
        }

        //Insertar en la BD
        Statement st = coneccion.createStatement();
        
        Date date = new Date();
        Date date2;
        String idCiudad = "";
        String ciudad = "";
        String consulta1 = "";
        long inicio = System.currentTimeMillis();
        long final_;
        for (int i = 0; i < 100; i++) {
            idCiudad = String.valueOf(i + 1);
            ciudad = "Tuxtla " + idCiudad;
            consulta1 = "INSERT INTO CIUDAD VALUES(" + Integer.valueOf(idCiudad) + ", '" + ciudad + "')";
            st.execute(consulta1);
        }
        final_ = System.currentTimeMillis();
        double operaciones;
        date2 = new Date();
        System.out.println("---------- Consulta ----------");
        System.out.println("Inicio = " + date);
        System.out.println("Final = " + date2);
        operaciones = (100/(final_ - inicio));
        System.out.println("Operaciones por segundo " + operaciones);
        System.out.println("-----------------------------");
        
        int random = 0;
        date = new Date();
        for(int i = 0; i < 10000; i++){
            idCiudad = String.valueOf(i + 1);
            ciudad = "Alumno " + idCiudad;
            random = (int) (Math.random() * 100);
            consulta1 = "INSERT INTO ALUMNO VALUES(" + Integer.valueOf(idCiudad) + ", '" + ciudad + "', " + random + ")";
            st.execute(consulta1);
        }
        date2 = new Date();
        System.out.println("---------- Consulta ----------");
        System.out.println("Inicio = " + date);
        System.out.println("Final = " + date2);
        operaciones = (10000/(final_ - inicio));
        System.out.println("Operaciones por segundo " + operaciones);
        System.out.println("-----------------------------");
        
        date = new Date();
        for(int i = 0; i < 25000; i++){
            idCiudad = String.valueOf(i + 1);
            ciudad = "Alumno " + idCiudad;
            random = (int) (Math.random() * 100);
            consulta1 = "INSERT INTO ALUMNO VALUES(" + Integer.valueOf(idCiudad) + ", '" + ciudad + "', " + random + ")";
            st.execute(consulta1);
        }
        date2 = new Date();
        System.out.println("---------- Consulta ----------");
        System.out.println("Inicio = " + date);
        System.out.println("Final = " + date2);
        operaciones = (25000/(final_ - inicio));
        System.out.println("Operaciones por segundo " + operaciones);
        System.out.println("-----------------------------");
        
        date = new Date();
        for(int i = 0; i < 100000; i++){
            idCiudad = String.valueOf(i + 1);
            ciudad = "Alumno " + idCiudad;
            random = (int) (Math.random() * 100);
            consulta1 = consulta1 = "INSERT INTO ALUMNO VALUES(" + Integer.valueOf(idCiudad) + ", '" + ciudad + "', " + random + ")";
            st.execute(consulta1);
        }
        date2 = new Date();
        System.out.println("---------- Consulta ----------");
        System.out.println("Inicio = " + date);
        System.out.println("Final = " + date2);
        operaciones = (100000/(final_ - inicio));
        System.out.println("Operaciones por segundo " + operaciones);
        System.out.println("-----------------------------");
        
        System.out.println("FIN");
        
        try {
            coneccion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
