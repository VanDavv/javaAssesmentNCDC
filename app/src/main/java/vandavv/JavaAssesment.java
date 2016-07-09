package vandavv;

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
import vandavv.resources.ListResource;

public class JavaAssesment extends Application<JavaAssesmentConfiguration> {

    private static final String APP_NAME = "JavaAssesment";
    private static final String HEALTH_CHECK_DATABASE_NAME = "database";

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

        final DBI jdbi = new DBIFactory().build(environment, configuration.getDatabase(), HEALTH_CHECK_DATABASE_NAME);

        environment.jersey().register(ListResource.class);
    }
}
