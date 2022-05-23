package br.com.sptech.eagle.back;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoBancoH2 {

    private JdbcTemplate conexaoET;

    //Método para conexão com o banco;
    public ConexaoBancoH2() {
        //Vem da biblioteca apache e é uma forma simplificada de fazer uma conexão com um banco de dados.
        BasicDataSource dataSource = new BasicDataSource();

        //Driver do banco que quero conectar (h2).
        dataSource.setDriverClassName("org.h2.Driver");

        //Bancos relacionais têm uma string de conexão, qual banco estou usando: h2, 
        //a url, tipo de armazenamento memoria = mem ou arquivo = file e nome do meu banco.
        dataSource​.setUrl("jdbc:h2:file:./eagle_totens");

        //Usuário padrão é o sa = system administrator.
        dataSource​.setUsername("sa");

        //Senha padrão é vazio.
        dataSource​.setPassword("");

        //Colocamos dentro do JdbcTemplate a instância dataSource.
        //Crio uma nova instância toda vez que eu quiser conectar com o banco, 
        //eu passo por aqui, sem perigo de errar a url por exemplo.
        conexaoET = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoET() {
        return conexaoET;
    }
}
