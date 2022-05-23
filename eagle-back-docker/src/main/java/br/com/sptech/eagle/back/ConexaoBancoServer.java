package br.com.sptech.eagle.back;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoBancoServer {

    private JdbcTemplate conexaoServer;

    //Método para conexão com o banco;
    public ConexaoBancoServer() {

//            System.out.println("chegou aqui");
        //Vem da biblioteca apache e é uma forma simplificada de fazer uma conexão com um banco de dados.
        BasicDataSource dataSource = new BasicDataSource();

        //Driver do banco que quero conectar --> SQL Server
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //Bancos relacionais têm uma string de conexão, qual banco estou usando: SQL Server, 
        //a url, nome do servidor, porta do servidor, nome do banco de dados.
        dataSource​.setUrl("jdbc:sqlserver://eagle-totens.database.windows.net:1433;database=eagle_totens;user=adminEagleTotens2022@eagle-totens;password=2ads$grupo9;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

            dataSource​.setUsername("adminEagleTotens2022");

            dataSource​.setPassword("2ads$grupo9");
        conexaoServer = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoServer() {
        return conexaoServer;
    }

}
