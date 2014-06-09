package eu.pmsoft.tutorial.parts.part2;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import eu.pmsoft.tutorial.guice.api.direct.LoggingContract;
import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.LoggerLevel1;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.LoggerLevel2;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.ModelWarsjava;
import eu.pmsoft.tutorial.guice.apiImplementation.direct.SimpleLogger;
import eu.pmsoft.tutorial.guice.model.AdminUser;
import eu.pmsoft.tutorial.guice.utils.annot.MyWorkshopCustomAnnotation;

/**
 * User: paweld2
 * Date: 2014-06-09
 */
public class DirectModelModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(LoggingContract.class).to(SimpleLogger.class);

        bind(LoggingContract.class).annotatedWith(Names.named("level1")).to(LoggerLevel1.class);
        bind(LoggingContract.class).annotatedWith(Names.named("level2")).to(LoggerLevel2.class);
        bind(ModelContract.class).to(ModelWarsjava.class).in(Singleton.class);
        bind(LoggingContract.class).annotatedWith(Names.named("modelLogger")).toInstance(ModelWarsjava
                .modelInternalLogger);
        bind(AdminUser.class).in(Singleton.class);
        bind(LoggingContract.class).annotatedWith(MyWorkshopCustomAnnotation.class).toInstance(new LoggingContract() {

            @Override
            public void log(String message) {
                System.out.println("MyWorkshopCustomAnnotation:" + message);
            }

            @Override
            public int getLevel() {
                return 100;
            }
        });

    }
}
