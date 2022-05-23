package br.com.sptech.eagle.back;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.util.Conversor;
import java.time.LocalDateTime;

public class MedidaDisco {

    // ---------- ESSE É O DISCO C: ----------
    Looca looca = new Looca();
    DiscosGroup disco = new DiscosGroup();
    LocalDateTime DateTime = LocalDateTime.now();

    // ----- ATRIBUTOS MEDIDA DISCO -----
    private Integer idMedidaDisco;
    private Double usoDeDisco;
    private Double discoLivre;
    private LocalDateTime dataHoraMedidaDisco;
    private Integer fkTotem;
    private Integer fkComponente;

    // ----- GET & SET -----
    public Integer getIdMedidaDisco() {
        return idMedidaDisco;
    }

    public void setIdMedidaDisco(Integer idMedidaDisco) {
        this.idMedidaDisco = idMedidaDisco;
    }

    public Double getUsoDeDisco() {
        return usoDeDisco;
    }

    public void setUsoDeDisco(Double usoDeDisco) {
        this.usoDeDisco = usoDeDisco;
    }

    public Double getDiscoLivre() {
        return discoLivre;
    }

    public void setDiscoLivre(Double discoLivre) {
        this.discoLivre = discoLivre;
    }

    public LocalDateTime getDataHoraMedidaDisco() {
        return DateTime;
    }

    public void setDataHoraMedidaDisco(LocalDateTime dataHoraMedidaDisco) {
        this.dataHoraMedidaDisco = dataHoraMedidaDisco;
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

    // ----- NOSSOS MÉTODOS API LOOCA -----
    // ---------- DISCO ---------- 
    public Double buscarEspacoOcupadoDisco() {
        Double discoTotal = longParaDouble(looca.getGrupoDeDiscos().getVolumes().get(0).getTotal());
        Double discoLivre = longParaDouble(looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel());
        Double usoDeDisco = discoTotal - discoLivre;
        return usoDeDisco;
    }

    public Double buscarEspacoLivreDisco() {
        Double discoLivre = longParaDouble(looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel());
        return discoLivre;
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

    // ----- .toString() -----
    @Override
    public String toString() {
        return String.format("\n------ Medida Disco ----- \nId medida disco: %s"
                + "\nUso de Disco: %.2f GB\nDisco Livre: %.2f GB\nData Hora: %s\n"
                + "Id totem: %d\n",
                idMedidaDisco, usoDeDisco, discoLivre, dataHoraMedidaDisco, fkTotem);
    }
}
