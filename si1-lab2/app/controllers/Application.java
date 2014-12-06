package controllers;

import models.Meta;
import models.Semana;
import models.dao.GenericDAO;
import org.hibernate.annotations.DynamicInsert;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application extends Controller {


    private static final GenericDAO dao = new GenericDAO();
    private static List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
    private static final int INCREMENTA=1,DECREMENTA=-1;
    private static Semana semana1 = new Semana();
    private static Semana semana2 = new Semana();
    private static Semana semana3 = new Semana();
    private static Semana semana4 = new Semana();
    private static Semana semana5 = new Semana();
    private static Semana semana6 = new Semana();
    private static String mensagem = "";

    @Transactional
    public static Result index() {
        metas = dao.findAllByClassName(Meta.class.getName());
        Collections.sort(metas);
        return ok(index.render(metas,mensagem,semana1,semana2,semana3,semana4,semana5,semana6));
    }

    @Transactional
    public static Result criaMeta(){
        DynamicForm form = Form.form().bindFromRequest();

        String semana = form.get("Semana");
        String descricao = form.get("Descricao");
        String prioridade = form.get("Prioridade");
        contabilizaMetasPorSemana(semana, INCREMENTA);
        contabilizaMetasPendentesPorSemana(semana);

        Meta meta = new Meta(descricao, prioridade, semana);
        dao.persist(meta);

        mensagem = "Sua meta foi adicionada!";
        return redirect(routes.Application.index());
    }

    @Transactional
    public static Result excluiMeta(Long id){
        Meta meta = dao.findByEntityId(Meta.class,id);
        contabilizaMetasPorSemana(meta.getSemana(), DECREMENTA);
        if(meta.isConcluded()) {
            contabilizaMetasConcluidasPorSemana(meta.getSemana(),DECREMENTA);
        }
        contabilizaMetasPendentesPorSemana(meta.getSemana());
        dao.removeById(Meta.class,id);

        mensagem = "A meta foi excluída!";
        return redirect(routes.Application.index());
    }

    @Transactional
    public static Result alteraStatus(Long id){
        Meta meta = dao.findByEntityId(Meta.class,id);
        meta.changeStatus();
        contabilizaMetasConcluidasPorSemana(meta.getSemana(),INCREMENTA);
        contabilizaMetasPendentesPorSemana(meta.getSemana());

        mensagem = "A meta foi atualizada!";
        return redirect(routes.Application.index());
    }

    public static void contabilizaSemanasDasMetasInicias(){
        for(Meta meta: metas){
            contabilizaMetasPorSemana(meta.getSemana(),INCREMENTA);
            contabilizaMetasPendentesPorSemana(meta.getSemana());
        }
    }

    private static void contabilizaMetasPorSemana(String semana,int definido) {
        if(semana.equals("1ª semana")){
            semana1.ajustaQuantidade(definido);
        }else if(semana.equals("2ª semana")){
            semana2.ajustaQuantidade(definido);
        }else if(semana.equals("3ª semana")){
            semana3.ajustaQuantidade(definido);
        }else if(semana.equals("4ª semana")){
            semana4.ajustaQuantidade(definido);
        }else if(semana.equals("5ª semana")){
            semana5.ajustaQuantidade(definido);
        }else if(semana.equals("6ª semana")){
            semana6.ajustaQuantidade(definido);
        }
    }

    private static void contabilizaMetasConcluidasPorSemana(String semana, int definido){
        if(semana.equals("1ª semana")){
            semana1.ajustaMetasConcluidas(definido);
        }else if(semana.equals("2ª semana")){
            semana2.ajustaMetasConcluidas(definido);
        }else if(semana.equals("3ª semana")){
            semana3.ajustaMetasConcluidas(definido);
        }else if(semana.equals("4ª semana")){
            semana4.ajustaMetasConcluidas(definido);
        }else if(semana.equals("5ª semana")){
            semana5.ajustaMetasConcluidas(definido);
        }else if(semana.equals("6ª semana")){
            semana6.ajustaMetasConcluidas(definido);
        }
    }

    private static void contabilizaMetasPendentesPorSemana(String semana){
        if(semana.equals("1ª semana")){
            semana1.ajustaMetasPendentes();
        }else if(semana.equals("2ª semana")){
            semana2.ajustaMetasPendentes();
        }else if(semana.equals("3ª semana")){
            semana3.ajustaMetasPendentes();
        }else if(semana.equals("4ª semana")){
            semana4.ajustaMetasPendentes();
        }else if(semana.equals("5ª semana")){
            semana5.ajustaMetasPendentes();
        }else if(semana.equals("6ª semana")){
            semana6.ajustaMetasPendentes();
        }
    }
}