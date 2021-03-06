/*
 * Copyright (c) 2015-2016 Dzikoysk
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

package org.panda_lang.panda.implementation.element.struct.variant;

import org.panda_lang.panda.implementation.element.field.Field;
import org.panda_lang.panda.implementation.element.method.Method;

import java.util.HashMap;
import java.util.Map;

public class PandaClassPrototypeBuilder {

    private String className;
    private Map<String, Field> fields;
    private Map<String, Method> methods;

    public PandaClassPrototypeBuilder() {
        this.fields = new HashMap<>();
        this.methods = new HashMap<>();
    }

    public PandaClassPrototypeBuilder className(String className) {
        this.className = className;
        return this;
    }

    public PandaClassPrototypeBuilder method(Method method) {
        methods.put(method.getName(), method);
        return this;
    }

    public PandaClassPrototype build() {
        return new PandaClassPrototype(className, fields, methods);
    }

}
