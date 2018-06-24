/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.geronimo.microprofile.openapi.impl.model.codec;

import static java.util.Locale.ROOT;

import javax.enterprise.inject.Vetoed;
import javax.json.bind.adapter.JsonbAdapter;

import org.eclipse.microprofile.openapi.models.media.Schema;
import org.eclipse.microprofile.openapi.models.parameters.Parameter;

@Vetoed
public final class Serializers {

    private Serializers() {
        // no-op
    }

    @Vetoed
    private static abstract class EnumSerializer<E extends Enum<E>> implements JsonbAdapter<E, String> {

        private final Class<E> type;

        protected EnumSerializer(final Class<E> type) {
            this.type = type;
        }

        @Override
        public String adaptToJson(final E obj) {
            return obj.toString();
        }

        @Override
        public E adaptFromJson(final String obj) {
            return Enum.valueOf(type, obj.toUpperCase(ROOT));
        }
    }

    @Vetoed
    public static class InSerializer extends EnumSerializer<Parameter.In> implements JsonbAdapter<Parameter.In, String> {

        public InSerializer() {
            super(Parameter.In.class);
        }
    }

    @Vetoed
    public static class SchemaTypeSerializer extends EnumSerializer<Schema.SchemaType>
            implements JsonbAdapter<Schema.SchemaType, String> {

        public SchemaTypeSerializer() {
            super(Schema.SchemaType.class);
        }
    }
}