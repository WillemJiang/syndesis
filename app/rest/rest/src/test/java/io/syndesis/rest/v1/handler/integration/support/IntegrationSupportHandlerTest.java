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
package io.syndesis.rest.v1.handler.integration.support;

import io.syndesis.core.Json;
import io.syndesis.model.ModelExport;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.*;

public class IntegrationSupportHandlerTest {

    @SuppressWarnings({ "PMD.UnusedLocalVariable"})
    @Test
    public void verifyJacksonBehaviorWithSourceStreams() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        // default config closes source stream
        try(InputStream fis = spy(new FileInputStream(new File(classLoader.getResource("model.json").getFile())))){
            ModelExport models = Json.mapper().readValue(fis, ModelExport.class);
            verify(fis, times(1)).close();
        }

        // disabling feature inline, skipt closing source stream
        try(InputStream fis = spy(new FileInputStream(new File(classLoader.getResource("model.json").getFile())))){
            ModelExport models = Json.mapperWithoutSourceAutoclose().readValue(fis, ModelExport.class);
            verify(fis, times(0)).close();
        }
    }
}
