/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package org.apache.kafka.connect.runtime;

public class ConnectorStatus extends AbstractStatus<String> {

    public ConnectorStatus(String connector, State state, String msg, String workerUrl, int generation) {
        super(connector, state, workerUrl, generation, msg);
    }

    public ConnectorStatus(String connector, State state, String workerUrl, int generation) {
        super(connector, state, workerUrl, generation, null);
    }

    public interface Listener {

        /**
         * Invoked after connector has successfully been shutdown.
         * @param connector The connector name
         */
        void onShutdown(String connector);

        /**
         * Invoked from the Connector using {@link org.apache.kafka.connect.connector.ConnectorContext#raiseError(Exception)}.
         * Note that no shutdown event will follow after the task has been failed.
         * @param connector The connector name
         * @param cause Error raised from the connector.
         */
        void onFailure(String connector, Throwable cause);

        /**
         * Invoked after successful startup of the connector.
         * @param connector The connector name
         */
        void onStartup(String connector);

        /**
         * Invoked when the connector is deleted through the REST API.
         * @param connector The connector name
         */
        void onDeletion(String connector);

    }
}
