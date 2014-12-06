import play.*;

import models.Meta;
import models.dao.GenericDAO;
import play.Application;
import play.db.jpa.JPA;


public class Global extends GlobalSettings {

    private static GenericDAO dao = new GenericDAO();

    @Override
    public void onStart(Application app) {
        Logger.info("Aplicação inicializada...");

        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {

                Meta minhaNovaMeta1 = new Meta("Terminar o lab2 de SI1","Alta","1ª semana");
                Meta minhaNovaMeta2 = new Meta("Estudar Cálculo II","Baixa","1ª semana");
                Meta minhaNovaMeta3 = new Meta("Estudar Lógica","Baixa","2ª semana");
                Meta minhaNovaMeta4 = new Meta("Estudar Cálculo II","Média","2ª semana");
                Meta minhaNovaMeta5 = new Meta("Estudar PLP","Média","2ª semana");
                Meta minhaNovaMeta6 = new Meta("Fazer trabalho de INFOSOC","Alta","2ª semana");
                Meta minhaNovaMeta7 = new Meta("Estudar Cálculo II","Alta","3ª semana");
                Meta minhaNovaMeta8 = new Meta("Estudar Lógica","Alta","3ª semana");
                Meta minhaNovaMeta9 = new Meta("Estuda PLP","Alta","3ª semana");
                Meta minhaNovaMeta10 = new Meta("Estudar SI","Alta","3ª semana");

                dao.persist(minhaNovaMeta1);
                dao.persist(minhaNovaMeta2);
                dao.persist(minhaNovaMeta3);
                dao.persist(minhaNovaMeta4);
                dao.persist(minhaNovaMeta5);
                dao.persist(minhaNovaMeta6);
                dao.persist(minhaNovaMeta7);
                dao.persist(minhaNovaMeta8);
                dao.persist(minhaNovaMeta9);
                dao.persist(minhaNovaMeta10);

                dao.merge(minhaNovaMeta1);
                dao.merge(minhaNovaMeta2);
                dao.merge(minhaNovaMeta3);
                dao.merge(minhaNovaMeta4);
                dao.merge(minhaNovaMeta5);
                dao.merge(minhaNovaMeta6);
                dao.merge(minhaNovaMeta7);
                dao.merge(minhaNovaMeta8);
                dao.merge(minhaNovaMeta9);
                dao.merge(minhaNovaMeta10);

                dao.flush();

                controllers.Application.contabilizaSemanasDasMetasInicias();
            }
        });
    }
}
