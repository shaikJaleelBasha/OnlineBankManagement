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

package org.apache.taglibs.standard.examples.taglib;

import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.jstl.core.LoopTagSupport;

/**
 * <p>Tag handler for &lt;locales&gt;
 *
 * @author Felipe Leme <jstl@felipeal.net>
 * @version $Revision: 1.3 $ $Date: 2004/02/28 01:01:41 $
 */
public class LocalesTag extends LoopTagSupport {

    private static final Locale[] locales = Locale.getAvailableLocales();
    private int pointer; 
    private String varTotal;

    public void setVarTotal( String value ) {
	varTotal = value;
    }

    public void prepare() {
	pointer = 0;
	if ( varTotal!=null && varTotal.length()>0 ) {
	    pageContext.setAttribute( varTotal, new Integer(locales.length) );
	}
    
    } 

    public boolean hasNext() {
	return pointer < locales.length;
    }  

    public Object next() {
	return locales[ pointer++ ];
    }
  
    public void setBegin( int value ) {
  	super.begin = value;
    }
  
    public void setEnd( int value ) {
  	super.end = value;
    }                     
}
