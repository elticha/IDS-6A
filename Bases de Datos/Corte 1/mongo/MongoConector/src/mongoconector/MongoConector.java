
package mongoconector;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.util.Date;
import java.util.Scanner;

public class MongoConector {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese la ip : ");
        String ip = input.nextLine();
        
        MongoClient mongo = new MongoClient(ip,27017);
        System.out.println("Conexión exitosa");
                
        DB db = mongo.getDB("EQUIPODIFVA");
        System.out.println("Entró a la BD");
        DBCollection col = db.getCollection("alumnos");
                      
        Date fecha = new Date();
        
        System.out.println(fecha.toString());        
        for(int i = 0; i < 100; i++){
            BasicDBObject ob = new BasicDBObject();
            ob.append("ID", i+1);
            ob.append("Alumno", "Alumno"+(i+1));
            ob.append("idCiudad", i);
            col.insert(ob);
        }
        fecha = new Date();
        System.out.println(fecha.toString());                 
        
        Date fecha2 = new Date();
        System.out.println(fecha2.toString());        
        
         DB db2 = mongo.getDB("EQUIPODIFVA2");
         DBCollection col2 = db2.getCollection("Ciudades");
         for(int i = 0; i < 10000; i++){
             BasicDBObject ob2 = new BasicDBObject();
             ob2.append("id", i);
             ob2.append("Ciudad", "ciudad"+(i+1));
             col2.insert(ob2);
         }
         fecha2 = new Date();
         System.out.println(fecha2.toString());
         
    }
}
    

