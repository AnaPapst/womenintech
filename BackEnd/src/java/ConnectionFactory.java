
/*Importação das bibliotecas de Banco de dados*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    // Dados para se conectar com o BD
   // private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/gestao_saocarlos?zeroDateTimeBehavior=CONVERT_TO_NULL&useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "senha123";
    
    //Metodo para iniciar a conexão com o Banco
    public static Connection getConnection(){
        try {
         //   Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch ( SQLException ex) {
            throw new RuntimeException("ERRO NA CONEXÃO: "+ex.getMessage(),ex);
        }
    }
    // Metodo para fechar a conexão com o banco (genérico)
    public static void closeConnection(Connection con){
            try {
                if (con != null){
                con.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("ERRO AO FECHAR CONEXÃO: ",ex);
            }
    }
    // Metodo para fechar a conexão com o banco (com o statement)
    public static void closeConnection(Connection con, PreparedStatement stmt){
            closeConnection(con);
            try {
               if (stmt != null){
                stmt.close();
               }
            } catch (SQLException ex) {
                throw new RuntimeException("ERRO AO FECHAR CONEXÃO: ",ex);
            }
    }
    // Metodo para fechar a conexão com o banco (com o statement e o resultado)
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
            closeConnection(con,stmt);
            try {
               if (rs != null){
                   rs.close();
               }
            } catch (SQLException ex) {
                throw new RuntimeException("ERRO AO FECHAR CONEXÃO: ",ex);
            }
    }
}