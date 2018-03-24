/*
 * Copyright 2017 Volker Berlin (i-net software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.inetsoftware.jwebassembly.runtime;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.webassembly.annotation.Export;

import de.inetsoftware.jwebassembly.ScriptEngine;
import de.inetsoftware.jwebassembly.WasmRule;

/**
 * @author Volker Berlin
 */
@RunWith(Parameterized.class)
public class CallFunctions extends AbstractBaseTest {
    
    @ClassRule
    public static WasmRule rule = new WasmRule( TestClass.class ); 

    public CallFunctions( ScriptEngine script, String method, Object[] params ) {
        super( rule, script, method, params );
    }

    @Parameters(name="{0}-{1}")
    public static Collection<Object[]> data() {
        ArrayList<Object[]> list = new ArrayList<>();
        for( Object[] val : ScriptEngine.testParams() ) {
            ScriptEngine script = (ScriptEngine)val[0];
            addParam( list, script, "intCall" );
        }
        return list;
    }

    static class TestClass {

        @Export
        static int intCall() {
            return intConst();
        }

        static int intConst() {
            return -42;
        }

    }
}
