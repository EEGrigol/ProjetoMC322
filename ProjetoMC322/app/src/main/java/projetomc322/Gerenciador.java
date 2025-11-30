package projetomc322;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "gerenciador")
public class Gerenciador {

    private List<CampoDespesa> camposDespesa = new ArrayList<>();
    private List<CampoReceita> camposReceita = new ArrayList<>();
    //private float valorLimiteGeral;

    public Gerenciador() {}

    //Getters e Setters
    @XmlElement(name = "campoDespesa") //se não tiver isso ele quebra
    public List<CampoDespesa> getCamposDespesa() {
        if (camposDespesa == null) {
            camposDespesa = new ArrayList<>();
        }
        return camposDespesa;
    }

    public void setCamposDespesa(List<CampoDespesa> camposDespesa) {
        this.camposDespesa = camposDespesa;
    }

    @XmlElement(name = "campoReceita") //se não tiver isso ele quebra
    public List<CampoReceita> getCamposReceita() {
        if (camposReceita == null) {
            camposReceita = new ArrayList<>();
        }
        return camposReceita;
    }

    public void setCamposReceita(List<CampoReceita> camposReceita) {
        this.camposReceita = camposReceita;
    }

    public float getSaldoFinal(){
        float totalReceitas = 0;
        float totalDespesas = 0;

        for(CampoReceita cr : camposReceita){
            totalReceitas += cr.calculaValorTotal();
        }

        for(CampoDespesa cd : camposDespesa){
            totalDespesas += cd.calculaValorTotal();
        }

        return totalReceitas - totalDespesas;
    }
}