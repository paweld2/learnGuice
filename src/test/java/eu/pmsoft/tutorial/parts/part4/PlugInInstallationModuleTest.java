package eu.pmsoft.tutorial.parts.part4;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.util.Modules;
import eu.pmsoft.tutorial.guice.api.direct.ModelContract;
import eu.pmsoft.tutorial.guice.api.direct.ModelEnvironment;
import eu.pmsoft.tutorial.guice.api.direct.ModelPlugin;
import eu.pmsoft.tutorial.parts.part2.EnvironmentModule;
import eu.pmsoft.tutorial.parts.part3.ModelModule;
import eu.pmsoft.tutorial.parts.part3.TaskModelModule;
import org.testng.annotations.Test;
import eu.pmsoft.tutorial.guice.model.ModelPluginRegistry;

import java.util.LinkedList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class PlugInInstallationModuleTest {

    @Test
    public void testPlugInInstalation() {
//		Install this plugins on the model
        List<ModelPlugin> plugins1 = pluginsToInstall("provider1");
        List<ModelPlugin> plugins2 = pluginsToInstall("provider2");

        ModelEnvironment testEnvironment = new ModelEnvironment("test");
        Module allModel = Modules.combine(new ModelModule(), new TaskModelModule(),
                new EnvironmentModule(testEnvironment));
        Module pluginsModules = Modules.combine(new PlugInInstallationModule(plugins1),
                new PlugInInstallationModule(plugins2));

        Injector injector = Guice.createInjector(Modules.combine(allModel, pluginsModules));
        assertNotNull(injector);
        ModelContract model = injector.getInstance(ModelContract.class);
        assertEquals(10, model.getNrOfPluginInstalled());
    }

    private List<ModelPlugin> pluginsToInstall(final String provider) {
        List<ModelPlugin> list = new LinkedList<ModelPlugin>();
        list.add(new ModelPlugin() {
            @Override
            public String getName() {
                return provider + "-plugin1";
            }
        });
        list.add(new ModelPlugin() {
            @Override
            public String getName() {
                return provider + "-plugin2";
            }
        });
        list.add(new ModelPlugin() {
            @Override
            public String getName() {
                return provider + "-plugin3";
            }
        });
        list.add(new ModelPlugin() {
            @Override
            public String getName() {
                return provider + "-plugin4";
            }
        });
        list.add(new ModelPlugin() {
            @Override
            public String getName() {
                return provider + "-plugin5";
            }
        });
        return list;
    }




    @Test
    public void testPlugInInstalationWithMultibinding(){
        final List<ModelPlugin> plugins = pluginsToInstall("provider1");

        ModelEnvironment testEnvironment = new ModelEnvironment("test");
        Module allModel = Modules.combine(new ModelModule(),new TaskModelModule(), new EnvironmentModule(testEnvironment));
        Module pluginsModules = Modules.combine(new AbstractModule(){
            @Override
            protected void configure() {
                Multibinder<ModelPlugin> multibinder = Multibinder.newSetBinder(binder(), ModelPlugin.class);
                for (ModelPlugin modelPlugin : plugins) {
                    multibinder.addBinding().toInstance(modelPlugin);
                }
                multibinder.permitDuplicates();
                MapBinder<String, ModelPlugin> mapMiltibinder = MapBinder.newMapBinder(binder(), String.class, ModelPlugin.class);
                for (ModelPlugin modelPlugin : plugins) {
                    mapMiltibinder.addBinding(modelPlugin.getName()).toInstance(modelPlugin);
                }
            }
        });

        Injector injector = Guice.createInjector(Modules.combine(allModel,pluginsModules));
        assertNotNull(injector);
        ModelPluginRegistry registry = injector.getInstance(ModelPluginRegistry.class);
        assertEquals(5, registry.getPluginCounter());
    }
}