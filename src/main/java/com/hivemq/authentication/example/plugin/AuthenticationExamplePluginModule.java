/*
 * Copyright 2015 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.authentication.example.plugin;

import com.dcsquare.hivemq.spi.HiveMQPluginModule;
import com.dcsquare.hivemq.spi.PluginEntryPoint;
import com.dcsquare.hivemq.spi.plugin.meta.Information;
import com.google.inject.Provider;
import org.apache.commons.configuration.AbstractConfiguration;

import static com.dcsquare.hivemq.spi.config.Configurations.noConfigurationNeeded;


/**
 * This is the plugin module class, which handles the initialization and configuration
 * of the plugin. Each plugin need to have a class, which is extending {@link HiveMQPluginModule}.
 * Also the fully qualified name of the class should be present in a file named
 * com.dcsquare.hivemq.spi.HiveMQPluginModule, which has to be located in META-INF/services.
 *
 * @author Christian Goetz
 */
@Information(name = "HiveMQ Authentication Example Plugin", author = "Christian Goetz", version = "1.0-SNAPSHOT")
public class AuthenticationExamplePluginModule extends HiveMQPluginModule {


    /**
     * This method can be used to add own configuration items for the plugin. The method accepts an
     * AbstractConfiguration from Apache Commons as return value, which gives great flexibility in
     * adding custom configurations. Some helper methods for basic configurations can be found in
     * {@link com.dcsquare.hivemq.spi.config.Configurations}. For acme
     * {@link com.dcsquare.hivemq.spi.config.Configurations.noConfigurationNeeded()} returns an
     * empty configuration, if the plugin does not need one.
     * <p/>
     * The configuration file need to be located in the plugin folder!
     *
     * @return Any AbstractConfiguration from Apache Commons, or the return value of the helper methods
     *         in {@link com.dcsquare.hivemq.spi.config.Configurations}
     */
    @Override
    public Provider<Iterable<? extends AbstractConfiguration>> getConfigurations() {
        return noConfigurationNeeded();
    }

    /**
     * This method is provided to execute some custom plugin configuration stuff. Is is the place
     * to execute Google Guice bindings,etc if needed.
     */
    @Override
    protected void configurePlugin() {

    }

    /**
     * This method needs to return the main class of the plugin.
     *
     * @return callback priority
     */
    @Override
    protected Class<? extends PluginEntryPoint> entryPointClass() {
        return AuthenticationExampleMainClass.class;
    }
}
