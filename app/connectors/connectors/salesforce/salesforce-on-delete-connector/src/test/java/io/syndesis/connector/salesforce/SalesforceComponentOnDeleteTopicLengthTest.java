/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.connector.salesforce;

import org.apache.camel.CamelContext;
import org.apache.camel.component.salesforce.SalesforceEndpointConfig;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class SalesforceComponentOnDeleteTopicLengthTest {

    private final CamelContext context = new DefaultCamelContext();

    @Test
    public void testTopicLength() throws Exception {
    	SalesforceOnDeleteComponent component = new SalesforceOnDeleteComponent();
    	component.setCamelContext(context);
        Map<String, String> options = new HashMap<String, String>();
        options.put(SalesforceEndpointConfig.SOBJECT_NAME, "superlongvaluevaluevaluevaluevalue");
        options.put("notifyForOperationDelete", "true");
        component.createEndpointUri("salesforce", options);
        assertTrue(options.get("topicName").equalsIgnoreCase("syndesis_superlongvalue_d"));
    }

}