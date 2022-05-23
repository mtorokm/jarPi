package br.com.sptech.eagle.back;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author gustavo.caxile
 */
public class BuscarMedidas {
    
    Looca looca = new Looca();
    Memoria memoria = new Memoria();
    Processador cpu = new Processador();
    DiscosGroup disco = new DiscosGroup();
    
   
    
    public Long buscarFrequenciaCpu(){
        Long tempoCpu = cpu.getFrequencia();
        return tempoCpu;
    }
    public Double buscarMemoriaEmUso(){
        Double usoRam = longParaDoubleEmUso(memoria.getEmUso()); 
        
        return usoRam;
    }
    public Double buscarMemoriaDisponivel(){
        Double ramLivre = longParaFloatMemDisponivel(memoria.getDisponivel());
        return ramLivre;
    }
    public Double buscarEspacoOcupadoDisco(){        
        Double discoTotal = longParaDouble(looca.getGrupoDeDiscos().getVolumes().get(0).getTotal());
        Double discoLivre = longParaDouble(looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel());
        Double usoDeDisco = discoTotal - discoLivre;
        return usoDeDisco;
    }
    public Double buscarEspacoLivreDisco(){
        Double discoLivre = longParaDouble(looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel());
        return discoLivre;
    }
    public Integer buscarProcessosCpu(){
        Integer processosCpu = looca.getGrupoDeProcessos().getTotalProcessos();
        return processosCpu;
    }
    public String buscarIpMaquina() throws UnknownHostException{
        String IpMaquina = InetAddress.getLocalHost().getHostAddress();
        //System.out.println(IpMaquina);
        return IpMaquina;
    }
    
    //Convertendo long para double
    public double longParaDouble(Long valorLong) {
        String valorConvertido = Conversor.formatarBytes(valorLong);
        String valorString = valorConvertido.replace(",", ".");
        valorString = valorString.replace("GiB", "");
        double valorDouble = Double.parseDouble(valorString);
        return valorDouble;
    }

    //Convertendo long para integer
    public Integer longParaInteger(Long valorLong) {
        String valorConvertido = Conversor.formatarBytes(valorLong);
        String valorString = valorConvertido.replace(",", ".");
        valorString = valorString.replace("GiB", "");
        Integer valorInteger = Integer.parseInt(valorString);
        return valorInteger;
    }

    //Convertendo long para float
    public Double longParaFloatMemDisponivel(Long valorLong) {        
        double converted = (double) valorLong;
        return converted;
    }
    
    public Double longParaDoubleEmUso(Long valorLong) {
        double converted = (double) valorLong;
        return converted;
    }

    
    
    
}
