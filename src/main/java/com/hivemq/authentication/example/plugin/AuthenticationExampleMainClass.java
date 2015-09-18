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

import com.hivemq.spi.PluginEntryPoint;
import com.hivemq.spi.callback.registry.CallbackRegistry;
import com.hivemq.authentication.example.callbacks.ClientConnect;
import com.hivemq.authentication.example.callbacks.authentication.AuthWithCertCallback;
import com.hivemq.authentication.example.callbacks.authentication.AuthWithUsernamePasswordCallback;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * This is the main class of the plugin, which is instanciated during the HiveMQ start up process.
 * @author Florian Limpoeck
 */
public class AuthenticationExampleMainClass extends PluginEntryPoint {

    private final ClientConnect clientConnect;
    private final AuthWithUsernamePasswordCallback authWithUsernamePasswordCallback;
    private AuthWithCertCallback authWithCertCallback;

    @Inject
    public AuthenticationExampleMainClass(final ClientConnect clientConnect,
                                          final AuthWithUsernamePasswordCallback authWithUsernamePasswordCallback,
                                          final AuthWithCertCallback authWithCertCallback) {
        this.clientConnect = clientConnect;
        this.authWithUsernamePasswordCallback = authWithUsernamePasswordCallback;
        this.authWithCertCallback = authWithCertCallback;
    }

    /**
     * This method is executed after the instanciation of the whole class. It is used to initialize
     * the implemented callbacks and make them known to the HiveMQ core.
     */
    @PostConstruct
    public void postConstruct() {

        CallbackRegistry callbackRegistry = getCallbackRegistry();
        callbackRegistry.addCallback(authWithUsernamePasswordCallback);
        callbackRegistry.addCallback(authWithCertCallback);
        callbackRegistry.addCallback(clientConnect);

    }

}
