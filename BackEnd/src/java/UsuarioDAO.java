
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    // metodo de criacao de usuarios no banco de dados
    public void Create(Usuario u){
        // iniciar a conexao com o banco usando a classe connection factory
        Connection con = ConnectionFactory.getConnection();
        // gerar uma variavel de operacao com o banco
        PreparedStatement stmt = null;
        try {
            // operacao de inserção de dados (login, Nome, Senha, Adm)
            stmt = con.prepareStatement("INSERT INTO Users VALUES(?,?,?,?)");
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getNomeCompleto());
            stmt.setString(3, u.getSenha());
            stmt.setInt(4, u.getCPF());
            // executar a operacao no banco
            stmt.executeUpdate();
            // mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            // caso ocorra uma excecao tratar ela
        } catch (SQLException ex) {
            // mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            // sempre finalizar encerrando a conexão com o banco
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    // metodo para a atualizacao de um usuario com o login sendo a chave de atualizacao
    public void Update(Usuario u,String chave) {
        // iniciar a conexao com o banco usando a classe connection factory
        Connection con = ConnectionFactory.getConnection();
        // gerar uma variavel de operacao com o banco
        PreparedStatement stmt = null;
        try {
            // operacao de atualização de dados
            stmt = con.prepareStatement("UPDATE Users SET email= ?,nome = ?, senha = ?, cpf = ? WHERE email = ?");
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getNomeCompleto());
            stmt.setString(3, u.getSenha());
            stmt.setInt(4, u.getCPF());;
            stmt.setString(5, chave);
            // executar a operacao no banco
            stmt.executeUpdate();
            // mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
             // caso ocorra uma excecao tratar ela
        } catch (SQLException ex) {
            // mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            // sempre finalizar encerrando a conexão com o banco
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    // metodo para exclusao de usuario com chave sendo o login
    public void Delete(Usuario u) {
        // iniciar a conexao com o banco usando a classe connection factory
        Connection con = ConnectionFactory.getConnection();
        // gerar uma variavel de operacao com o banco
        PreparedStatement stmt = null;
        try {
            // operacao de exclusao de usuarios
            stmt = con.prepareStatement("DELETE FROM Users WHERE email = ?");
            stmt.setString(1, u.getEmail());
            // executar a operacao no banco
            stmt.executeUpdate();
            // mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            // caso ocorra uma excecao tratar ela
        } catch (SQLException ex) {
            // mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            // sempre finalizar encerrando a conexão com o banco
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    // metodo de listagem de usuarios (abastecer a tabela)
    public List<Usuario> Read() {
        // iniciar a conexao com o banco usando a classe connection factory
        Connection con = ConnectionFactory.getConnection();
        // gerar uma variavel de operacao com o banco
        PreparedStatement stmt = null;
        // gera a variavel de resultado de busca de banco
        ResultSet rs = null;
        // cria uma lista de usuarios
        List<Usuario> users = new ArrayList<>();

        try {
            // operacao de busca de todos os usuarios
            stmt = con.prepareStatement("SELECT * FROM Users");
            // executar a operacao no banco
            rs = stmt.executeQuery();
            // percorre a lista do resultado alimentando a lista dos usuarios
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setEmail(rs.getString("email"));
                u.setNomeCompleto(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setCPF(rs.getInt("cpf"));
                users.add(u);
            }
        } catch (SQLException ex) {
            // mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao ler dados: " + ex);
        } finally {
            // sempre finalizar encerrando a conexão com o banco
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return users;
    }
    // metodo para a busca de um usuario em especifico
    public List<Usuario> Busca(String nome){
         // iniciar a conexao com o banco usando a classe connection factory
        Connection con = ConnectionFactory.getConnection();
        // gerar uma variavel de operacao com o banco
        PreparedStatement stmt = null;
        // gera a variavel de resultado de busca de banco
        ResultSet rs = null;
        // cria uma lista de usuarios
        List<Usuario> users = new ArrayList<>();
        try{
             // operacao de busca de todos os usuarios
            stmt = con.prepareStatement("SELECT * FROM Users WHERE nome LIKE '%" + nome + "%'");
            // executar a operacao no banco
            rs = stmt.executeQuery();
            // percorre a lista do resultado alimentando a lista dos usuarios
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setEmail(rs.getString("email"));
                u.setNomeCompleto(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setCPF(rs.getInt("cpf"));
                users.add(u);
            }
        } catch(SQLException ex){
            // mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao ler dados: " + ex);
        } finally{
            // sempre finalizar encerrando a conexão com o banco
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return users;
    }
    // metodo para validar a usuario e senha
    public boolean Check(String login, String senha) {
        // iniciar a conexao com o banco usando a classe connection factory
        Connection con = ConnectionFactory.getConnection();
        // gerar uma variavel de operacao com o banco
        PreparedStatement stmt = null;
        // gera a variavel de resultado de busca de banco
        ResultSet rs = null;
        boolean check = false;

        try {
            // operacao de busca onde o login e senha fornecidos batem
            stmt = con.prepareStatement("SELECT * FROM Users WHERE email = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            // executar a operacao no banco
            rs = stmt.executeQuery();
            if (rs.next()) {

                check = true;
            }
        } catch (SQLException ex) {
            // mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao ler usuarios!: " + ex);
        } finally {
            // sempre finalizar encerrando a conexão com o banco
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }
    
}