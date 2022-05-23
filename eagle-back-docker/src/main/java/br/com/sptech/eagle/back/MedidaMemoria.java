package br.com.sptech.eagle.back;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.util.Conversor;
import java.time.LocalDateTime;

public class MedidaMemoria {

    Looca looca = new Looca();
    Memoria memoria = new Memoria();
    LocalDateTime DateTime = LocalDateTime.now();

    // ----- ATRIBUTOS MEDIDA MEMÓRIA -----
    private Integer idMedidaMemoria;
    private Double usoRam;
    private Double ramLivre;
    private LocalDateTime dataHoraMedidaMemoria;
    private Integer fkTotem;
    private Integer fkComponente;

    // ----- GET & SET -----
    public Integer getIdMedidaMemoria() {
        return idMedidaMemoria;
    }

    public void setIdMedidaMemoria(Integer idMedidaMemoria) {
        this.idMedidaMemoria = idMedidaMemoria;
    }

    public Double getUsoRam() {
        return usoRam;
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

    
    public void setUsoRam(Double usoRam) {
        this.usoRam = usoRam;
    }

    public Double getRamLivre() {
        return ramLivre;
    }

    public void setRamLivre(Double ramLivre) {
        this.ramLivre = ramLivre;
    }

    public LocalDateTime getDataHoraMedidaMemoria() {
        return DateTime;
    }

    public void setDataHoraMedidaMemoria(LocalDateTime dataHoraMedidaMemoria) {
        this.dataHoraMedidaMemoria = dataHoraMedidaMemoria;
    }

    // ----- NOSSOS MÉTODOS API LOOCA -----
    // ---------- MEMÓRIA ---------- 
    public Double buscarMemoriaEmUso() {
        Double usoRam = longParaDoubleEmUso(memoria.getEmUso());
//        Essa divisão converte os valores --> 1 GB = 1.024 MB = 1.048.576 KB
//        Neste caso usamos 1024 porque o valor fica mais próximo do real da máquina
        Double usoRamGB = (usoRam / 1024) / 1024 / 1024;
        return usoRamGB;
    }

    public Double buscarMemoriaDisponivel() {
        Double ramLivre = longParaFloatMemDisponivel(memoria.getDisponivel());
//        Essa divisão converte os valores --> 1 GB = 1.024 MB = 1.048.576 KB
//          Usamos 1000 para facilitar
        Double ramLivreGB = (ramLivre / 1000) / 1000 / 1000;
        return ramLivreGB;
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

    @Override
    public String toString() {
        return String.format("\n------ Medida Memoria ----- \nId medida memoria: %s"
                + "\nUso de RAM: %.1f GB\nRAM Livre: %.1f GB\nData Hora: %s\n"
                 + "Id totem: %d\n",
                idMedidaMemoria, usoRam, ramLivre, dataHoraMedidaMemoria,fkTotem);
    }
}
