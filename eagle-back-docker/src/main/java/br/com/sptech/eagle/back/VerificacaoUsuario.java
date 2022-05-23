
package br.com.sptech.eagle.back;


public class VerificacaoUsuario {
    private String nome, email, senha;

    public VerificacaoUsuario() {
        this.nome = "";
        this.email = "";
        this.senha = "";
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return nome;
    }   
  
}
