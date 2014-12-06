import static org.fest.assertions.Assertions.*;

import models.Meta;
import models.dao.GenericDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;
import scala.Option;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by lucas on 20/11/14.
 */
public class MetasTest {

    private GenericDAO dao = new GenericDAO();

    @Test
    public void deveIniciarSemMetas() throws Exception {
        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(0);
    }

    @Test
    public void deveSalvarMetaNoBD() throws Exception {
        Meta meta = new Meta("Terminar lab2 de SI1", "Alta", "4ª semana");
        dao.persist(meta);

        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(1);
        assertThat(metas.get(0).getDescricao()).isEqualTo("Terminar lab2 de SI1");
        assertThat(metas.get(0).getPrioridade()).isEqualTo("Alta");
        assertThat(metas.get(0).getSemana()).isEqualTo("4ª semana");
    }

    @Test
    public void deveExcluirMetaDoBD() throws Exception{
        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(0);

        Meta novaMeta = new Meta("Terminar lab2 de SI1", "Alta", "4ª semana");
        dao.persist(novaMeta);
        metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(1);

        controllers.Application.excluiMeta(novaMeta.getId());
        metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(0);

    }

    @Test
    public void deveAtualizarMetaMasManterNoBD() throws Exception{
        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(0);

        Meta novaMeta = new Meta("Terminar lab2 de SI1", "Alta", "4ª semana");
        dao.persist(novaMeta);
        metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(1);

        controllers.Application.alteraStatus(novaMeta.getId());
        metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(1);
    }

    @Test
    public void deveAtualizarMetaEExcluirDoBD() throws Exception{
        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(0);

        Meta novaMeta = new Meta("Terminar lab2 de SI1", "Alta", "4ª semana");
        dao.persist(novaMeta);
        metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(1);

        controllers.Application.alteraStatus(novaMeta.getId());
        metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(1);

        controllers.Application.excluiMeta(novaMeta.getId());
        metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(0);
    }



    public EntityManager em;

    @Before
    public void setUp() {
        FakeApplication app = Helpers.fakeApplication(new GlobalSettings());
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }

}
