package br.com.sptech.eagle.back;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import java.time.LocalDateTime;

public class MedidaCpu {

    Looca looca = new Looca();
    Processador cpu = new Processador();
    LocalDateTime DateTime = LocalDateTime.now();

    // ----- ATRIBUTOS MEDIDA CPU -----
    private Integer idMedidaCpu;
    private Long tempoCpu;
    private Integer processosCpu;
    private LocalDateTime dataHoraMedidaCpu;
    private Integer fkTotem;
    private Integer fkComponente;
    
    // ----- GET & SET -----
    public Integer getIdMedidaCpu() {
        return idMedidaCpu;
    }

    public void setIdMedidaCpu(Integer idMedidaCpu) {
        this.idMedidaCpu = idMedidaCpu;
    }

    public Integer getFkTotem() {
        return fkTotem;
    }

    public void setFkTotem(Integer fkTotem) {
        this.fkTotem = fkTotem;
    }

    public Integer getFkComponente() {
        return fkComponente;
    }

    public void setFkComponente(Integer fkComponente) {
        this.fkComponente = fkComponente;
    }

    
    public Long getTempoCpu() {
        return tempoCpu;
    }

    public void setTempoCpu(Long tempoCpu) {
        this.tempoCpu = tempoCpu;
    }

    public Integer getProcessosCpu() {
        return processosCpu;
    }

    public void setProcessosCpu(Integer processosCpu) {
        this.processosCpu = processosCpu;
    }

    public LocalDateTime getDataHoraMedidaCpu() {
        return DateTime;
    }

    public void setDataHoraMedidaCpu(LocalDateTime dataHoraMedidaCpu) {
        this.dataHoraMedidaCpu = dataHoraMedidaCpu;
    }

    // ----- NOSSOS MÉTODOS API LOOCA -----
    // ---------- CPU ----------
    public Long buscarFrequenciaCpu() {
        Long tempoCpu = cpu.getFrequencia();
//        Essa divisão converte os valores --> 1 GB = 1.024 MB = 1.048.576 KB
//        Usamos 1000 para facilitar
        Long tempoCpuGHz = (tempoCpu / 1000) / 1000 / 1000;
        return tempoCpuGHz;
    }

    public Integer buscarProcessosCpu() {
        Integer processosCpu = looca.getGrupoDeProcessos().getTotalProcessos();
        return processosCpu;
    }
//    
//     //Convertendo long para double
//    public double longParaDouble(Long valorLong) {
//        String valorConvertido = Conversor.formatarBytes(valorLong);
//        String valorString = valorConvertido.replace(",", ".");
//        valorString = valorString.replace("GiB", "");
//        double valorDouble = Double.parseDouble(valorString);
//        return valorDouble;
//    }
//
//    //Convertendo long para integer
//    public Integer longParaInteger(Long valorLong) {
//        String valorConvertido = Conversor.formatarBytes(valorLong);
//        String valorString = valorConvertido.replace(",", ".");
//        valorString = valorString.replace("GiB", "");
//        Integer valorInteger = Integer.parseInt(valorString);
//        return valorInteger;
//    }
//
//    //Convertendo long para float
//    public Double longParaFloatMemDisponivel(Long valorLong) {
//        double converted = (double) valorLong;
//        return converted;
//    }
//
//    public Double longParaDoubleEmUso(Long valorLong) {
//        double converted = (double) valorLong;
//        return converted;
//    }

    // ----- .toString() -----
    @Override
    public String toString() {
        return String.format("\n------ Medida CPU ----- \nId medida CPU: %s"
                + "\nVelocidade CPU: %s GHz\nProcessos: %s\nData Hora: %s\n"
                + "Id totem: %d\n",
                idMedidaCpu, tempoCpu, processosCpu, dataHoraMedidaCpu,fkTotem);
    }

}
