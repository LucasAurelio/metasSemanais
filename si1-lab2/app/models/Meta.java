package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lucas on 20/11/2014.
 */

@Entity
public class Meta implements Comparable<Meta>{

    @Id
    @GeneratedValue
    private Long id;
    private String descricao;
    private String prioridade;
    private String semana;
    private Boolean concluida;

    public Meta(String descricao, String prioridade, String semana) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.semana = semana;
        this.concluida = false;
    }

    public Meta(){}

    public String getDescricao() {
        return descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public String getSemana() {
        return semana;
    }

    public Long getId(){
        return id;
    }

    @Override
    public int compareTo(Meta o) {

        if (prioridade.equals("Alta") && o.getPrioridade().equals("Alta")){
            return 0;
        }else if(prioridade.equals("Alta")){
            return -1;
        }else if(prioridade.equals("Média") && o.getPrioridade().equals("Baixa")){
            return -1;
        }else if(prioridade.equals("Média") && o.getPrioridade().equals("Média")){
            return 0;
        }else if(prioridade.equals("Média") && o.getPrioridade().equals("Alta")){
            return 1;
        }else if(prioridade.equals("Baixa") && o.getPrioridade().equals("Baixa")){
            return 0;
        }else if(prioridade.equals("Baixa")){
            return 1;
        }else{
            return 0;
        }
    }

    public void changeStatus(){
        concluida = true;
    }

    public boolean isConcluded(){
        return concluida;
    }
}
