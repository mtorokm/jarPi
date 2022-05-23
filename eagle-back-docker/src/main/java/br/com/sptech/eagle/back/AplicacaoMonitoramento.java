
package br.com.sptech.eagle.back;

import java.net.UnknownHostException;


public class AplicacaoMonitoramento {
    public static void main(String[] args) throws UnknownHostException {
        ModelBd bancoDados = new ModelBd();
        ModelBd2 bancoDados2 = new ModelBd2();
        BuscarMedidas medidasGerais = new BuscarMedidas();
        String hostAtual = "";

        
        String host = medidasGerais.buscarIpMaquina();
        hostAtual = host;
        
        System.out.println(hostAtual);

        String totemComChave = bancoDados.verificarHost(hostAtual);
        String totemComChave2 = totemComChave.replace("]", "");
        String id_totem = totemComChave2.replace("[", "");
        if (id_totem.equals("inexistente")) {
            System.out.println("IP não cadastrado!!");
            String respostaCadastro = bancoDados.cadastrartotem(hostAtual);
            System.out.println(respostaCadastro);
        }

        bancoDados.inserirDadosBancoAzure(id_totem);
        bancoDados2.inserirDadosBancoLocal(id_totem);

    }                                  
    
}
