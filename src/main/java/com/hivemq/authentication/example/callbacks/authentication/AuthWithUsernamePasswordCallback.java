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

package com.hivemq.authentication.example.callbacks.authentication;

import com.hivemq.spi.callback.CallbackPriority;
import com.hivemq.spi.callback.exception.AuthenticationException;
import com.hivemq.spi.callback.security.OnAuthenticationCallback;
import com.hivemq.spi.message.ReturnCode;
import com.hivemq.spi.security.ClientCredentialsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Florian Limpoeck
 */
public class AuthWithUsernamePasswordCallback implements OnAuthenticationCallback {

    private static Logger log = LoggerFactory.getLogger(AuthWithUsernamePasswordCallback.class);

    /**
     * This is an example authentication method, which shows all possible data that is available from the client.
     * The authentication should be replace with your method of choice: database lookup, webservice call, ...
     *
     * @param clientCredentialsData All information from the connecting MQTT client
     * @return true, if the client provides correct username and password, false if credentials are not present or wrong
     * @throws AuthenticationException if the client is not authorized and it should be disconnected immediately
     */

    @Override
    public Boolean checkCredentials(ClientCredentialsData clientCredentialsData) throws AuthenticationException {

        String clientId = clientCredentialsData.getClientId();

        log.info("A new client with id {} requests authentication from the AuthWithUsernamePasswordCallback", clientId);

        if (clientCredentialsData.getUsername().isPresent() && clientCredentialsData.getPassword().isPresent()) {
            String username = clientCredentialsData.getUsername().get();
            String password = clientCredentialsData.getPassword().get();

            log.info("The client provides the following username: {}", username);

            if (isUserValid(username, password)) {
                return true;
            } else {
                throw new AuthenticationException(ReturnCode.REFUSED_NOT_AUTHORIZED);
            }
        }

        return false;
    }

    private boolean isUserValid(String username, String password) {

        // Here goes your custom authentication logic !!!
        // This is just a dummy implementation returning true at dummy/password !!

        // You want to validate username and password against your database or third party service

        if(username.equals("dummy") && password.equals("password")){
            log.warn("This is the HiveMQ authentication example, please customize the authentication logic and then remove this warning!");
            log.info("Username/password is valid!");
            return true;
        }

        else {
            return false;
        }
    }

    /**
     * The priority is used when more than one OnAuthenticationCallback is implemented to determine the order.
     * If there is only one callback, which implements a certain interface, the priority has no effect.
     *
     * @return callback priority
     */
    @Override
    public int priority() {
        return CallbackPriority.CRITICAL;
    }
}
