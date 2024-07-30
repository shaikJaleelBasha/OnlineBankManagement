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
import javax.servlet.jsp.JspException;

/**
 * <p>String repository for Reader/Writer.
 *
 * @author Pierre Delisle
 * @version $Revision: 1.3 $ $Date: 2004/02/28 01:01:41 $
 */
public class IOBean {
    StringWriter stringWriter = null;
    String content = null;

    public Reader getReader() throws JspException {
	//p("getReader()");
	if (content == null) {
	    if (stringWriter == null) {
		throw new JspException(
		    "content must first be added to the bean via the writer");
	    }
	    content = stringWriter.toString();
	}
	return new StringReader(content);
    }

    public Writer getWriter() {
	//p("getWriter()");
	content = null;
	stringWriter = new StringWriter();
	return stringWriter;
    }

    public void release() {
	stringWriter = null;
	content = null;
    }

    private void p(String s) {
	System.out.println("[IOBean] " + s);
    }
}
