/*
 * Copyright 1999-2004 The Apache Software Foundation.
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

package org.apache.taglibs.standard.examples.util;

import java.io.*;
import javax.servlet.jsp.*;

/**
 * <p>Utility class for examples webapp.
 *
 * @author Pierre Delisle
 * @version $Revision: 1.3 $ $Date: 2004/02/28 01:01:41 $
 */
public class Util {

    public static Writer castToWriter(Object obj) throws JspException {
        if (obj instanceof OutputStream) {
            return new OutputStreamWriter((OutputStream)obj);
        } else if (obj instanceof Writer) {
            return (Writer)obj;
            /*@@@
        } else if (obj instanceof String) {
            return new StringWriter();
             */
        }
        throw new JspException("Invalid type '" + obj.getClass().getName() +
			       "' for castToWriter()");
    }
    
    public static Reader castToReader(Object obj) throws JspException {
        if (obj instanceof InputStream) {
            return new InputStreamReader((InputStream)obj);
        } else if (obj instanceof Reader) {
            return (Reader)obj;
        } else if (obj instanceof String) {
            return new StringReader((String)obj);
        }
        throw new JspException("Invalid type '" + obj.getClass().getName() +
			       "' for castToReader()");
    }
}

