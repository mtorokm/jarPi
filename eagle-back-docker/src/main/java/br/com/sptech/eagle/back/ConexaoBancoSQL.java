package br.com.sptech.eagle.back;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;


public class ConexaoBancoSQL {

    private JdbcTemplate conexaoSQL;

    //Método para conexão com o banco;
    public ConexaoBancoSQL() {
        //Vem da biblioteca apache e é uma forma simplificada de fazer uma conexão com um banco de dados.
        BasicDataSource dataSource = new BasicDataSource();

        //Driver do banco que quero conectar -->
        
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        //Bancos relacionais têm uma string de conexão, qual banco estou usando: MySQL, 
        //a url, nome do servidor, porta do servidor, nome do banco de dados.
        //jdbc:mysql:// server-name : server-port / database-name Note – NOTE: Default server port is 3306
        dataSource​.setUrl("jdbc:mysql://0.0.0.0:3306/eagle_totens?useTimezone=true&serverTimezone=America/Sao_Paulo");
        
        dataSource​.setUsername("root");
        dataSource​.setPassword("urubu100");

        //Colocamos dentro do JdbcTemplate a instância dataSource.
        //Crio uma nova instância toda vez que eu quiser conectar com o banco, 
        //eu passo por aqui, sem perigo de errar a url por exemplo.
        conexaoSQL = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoSQL() {
        return conexaoSQL;
    }
}
