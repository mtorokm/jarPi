package br.com.sptech.eagle.back;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class ModelBd {

    public void inserirDadosBancoAzure(String totem) {

        // INSERIR DADOS A CADA PERIODO DE TEMPO DE FORMA AUTOMATIZADA!!!!!!!!!!!!!!!!!!
        // ----- CRIANDO OBJETOS NECESSÁRIOS PARA BUSCAR MÉTODOS -----
//        ConexaoBancoH2 conexaoServer = new ConexaoBancoH2();
        Timer timer = new Timer();
        final long SEGUNDOS = (1000 * 10); //executando a tarefa de 5 em 5 segundos
        ConexaoBancoServer conexaoServer = new ConexaoBancoServer();
        MedidaDisco disco = new MedidaDisco();
        MedidaCpu cpu = new MedidaCpu();
        MedidaMemoria memoria = new MedidaMemoria();

        conexaoServer.getConexaoServer().execute("use eagle_totens;");
   
//        Aqui setamos um timer para que o código execute em loop a cada x segundos e assim capturar os dados da máquina continuamente
        TimerTask tarefa = new TimerTask() {

            @Override
            public void run() {
                
                // ---------- INSERINDO VALORES NA TABELA MEDIDA_DISCO ----------
//                LocalDateTime dataHoraMedidaDisco = ;
                Double usoDeDisco = disco.buscarEspacoOcupadoDisco();
                Double discoLivre = disco.buscarEspacoLivreDisco();

                conexaoServer.getConexaoServer().update("insert into medida_disco values "
                        + "(?, ?, ?, 302, ?);", usoDeDisco, discoLivre, LocalDateTime.now(), totem);

                //Listar informações de disco
                List<MedidaDisco> listaDeMedidasDisco = conexaoServer.getConexaoServer().query("select * from "
                        + "medida_disco order by id_medida_disco desc offset 1 rows fetch next 1 rows only;", new BeanPropertyRowMapper(MedidaDisco.class));

                // ---------- INSERINDO VALORES NA TABELA MEDIDA_CPU ---------
//                LocalDateTime dataHoraMedidaCpu = cpu.getDataHoraMedidaCpu();
                Long tempoCpu = cpu.buscarFrequenciaCpu();
                Integer processosCpu = cpu.buscarProcessosCpu();

                conexaoServer.getConexaoServer().update("insert into medida_cpu values "
                        + "(?, ?, ?, 300, ?);", tempoCpu, processosCpu, LocalDateTime.now(), totem);

                //Listar informações da cpu
                List<MedidaCpu> listaDeMedidaCpu = conexaoServer.getConexaoServer().query("select * from "
                        + "medida_cpu order by id_medida_cpu desc offset 1 rows fetch next 1 rows only;", new BeanPropertyRowMapper(MedidaCpu.class));

                // ---------- INSERINDO VALORES NA TABELA MEDIDA_MEMORIA ---------
                Double usoRam = memoria.buscarMemoriaEmUso();
                Double ramLivre = memoria.buscarMemoriaDisponivel();
//                LocalDateTime dataHoraMedidaMemoria = memoria.getDataHoraMedidaMemoria();

                conexaoServer.getConexaoServer().update("insert into medida_memoria values "
                        + "(?, ?, ?, 301, ?);", usoRam, ramLivre, LocalDateTime.now(),totem);

                //Listar informações da memoria
                List<MedidaMemoria> listaDeMedidaMemoria = conexaoServer.getConexaoServer().query("select * from "
                        + "medida_memoria order by id_medida_memoria desc offset 1 rows fetch next 1 rows only;", new BeanPropertyRowMapper(MedidaMemoria.class));

                //Imprimindo listas
                System.out.println("-----------------------Populando Azure-------------------------------");
                System.out.println(listaDeMedidasDisco);
                listaDeMedidasDisco.clear();

                System.out.println(listaDeMedidaCpu);
                listaDeMedidaCpu.clear();

                System.out.println(listaDeMedidaMemoria);
                listaDeMedidaMemoria.clear();

            }
//          Timer funciona com milissegundos, então 1000 ms = 1 segundo
        };
        timer.scheduleAtFixedRate(tarefa, 0, SEGUNDOS );
    }

    
    public String verificarHost(String host) {
//        ConexaoBancoSQL conexaoServer = new ConexaoBancoSQL();
        ConexaoBancoServer conexaoServer = new ConexaoBancoServer();
        conexaoServer.getConexaoServer().execute("use eagle_totens;");
        List<VerificarHost> listaMaquinas = conexaoServer.getConexaoServer().query("select "
                + "id_host, id_totem from totem where id_host = ?",
                new BeanPropertyRowMapper(VerificarHost.class), host);

        if (!listaMaquinas.isEmpty()) {
            return listaMaquinas.toString();
        } else {
            return "inexistente";
        }

    }
    
    public String cadastrartotem(String hostAtual){
        ConexaoBancoServer conexaoServer = new ConexaoBancoServer();
        conexaoServer.getConexaoServer().execute("use eagle_totens;");
        conexaoServer.getConexaoServer().update("INSERT INTO totem VALUES "
                        + "('Ativo', 1002, ?);", hostAtual);
        List<VerificarHost> listaMaquinas = conexaoServer.getConexaoServer().query("select "
                + "id_host, id_totem from totem where id_host = ?",
                new BeanPropertyRowMapper(VerificarHost.class), hostAtual);
        if (!listaMaquinas.isEmpty()) {
            return "Totem cadastrado com sucesso";
        } else {
            return "Não cadastrado";
        }
        
    }

}
