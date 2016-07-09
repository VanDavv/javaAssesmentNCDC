package vandavv;

import dao.BookDAO;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vandavv.resources.AddResource;
import vandavv.resources.ListResource;

public class JavaAssesment extends Application<JavaAssesmentConfiguration> {

    private static final String APP_NAME = "JavaAssesment";
    private static final String HEALTH_CHECK_DATABASE_NAME = "database";
    private static final Logger LOG = LoggerFactory.getLogger(JavaAssesment.class);

    public static void main(String[] args) throws Exception {
        new JavaAssesment().run(args);
    }

    @Override
    public String getName() {
        return APP_NAME;
    }

    @Override
    public void initialize(Bootstrap<JavaAssesmentConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new MigrationsBundle<JavaAssesmentConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(JavaAssesmentConfiguration configuration) {
                return configuration.getDatabase();
            }
        });

        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(JavaAssesmentConfiguration configuration, Environment environment) throws Exception {
        LOG.info("Server started");
        LOG.debug("Initializing JDBI");
        final DBI jdbi = new DBIFactory().build(environment, configuration.getDatabase(), HEALTH_CHECK_DATABASE_NAME);

        LOG.debug("Creating BookDAO from JDBI");
        final BookDAO dao = jdbi.onDemand(BookDAO.class);

        LOG.debug("Registering ListResource");
        environment.jersey().register(new ListResource(dao));
        LOG.debug("Registering AddResource");
        environment.jersey().register(new AddResource(dao));

        LOG.info("Server setup finished");
    }
}
