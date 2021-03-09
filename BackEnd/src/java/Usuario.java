
public class Usuario {
    private String NomeCompleto;
    private String Email;
    private Integer CPF;
    private String Senha;

    public Usuario(String NomeCompleto, String Email, Integer CPF, String Senha) {
        this.NomeCompleto = NomeCompleto;
        this.Email = Email;
        this.CPF = CPF;
        this.Senha = Senha;
    }

    public Usuario() {
    }

    

    public String getNomeCompleto() {
        return NomeCompleto;
    }

    public void setNomeCompleto(String NomeCompleto) {
        this.NomeCompleto = NomeCompleto;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Integer getCPF() {
        return CPF;
    }

    public void setCPF(Integer CPF) {
        this.CPF = CPF;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    
    
    
}
