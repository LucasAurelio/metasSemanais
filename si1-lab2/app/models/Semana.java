package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 30/11/2014.
 */
public class Semana {

    private int quantidadeMetas;
    private int quantidadeMetasConcluidas;
    private int quantidadeMetasPendentes;

    public Semana(){
        quantidadeMetas = 0;
        quantidadeMetasConcluidas = 0;
        quantidadeMetasPendentes = 0;
    }

    public void ajustaQuantidade(int i){
        quantidadeMetas = quantidadeMetas + i;
    }

    public int getQuantidadeMetas(){
        return quantidadeMetas;
    }

    public void ajustaMetasConcluidas(int i){
        quantidadeMetasConcluidas = quantidadeMetasConcluidas + i;
    }

    public int getMetasConcluidas(){
        return quantidadeMetasConcluidas;
    }

    public void ajustaMetasPendentes(){
        quantidadeMetasPendentes = quantidadeMetas - quantidadeMetasConcluidas;
    }

    public int getMetasPendentes(){
        return quantidadeMetasPendentes;
    }

}
