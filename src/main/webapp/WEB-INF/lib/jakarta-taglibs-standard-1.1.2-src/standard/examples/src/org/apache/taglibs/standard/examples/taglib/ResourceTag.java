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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.examples.util.IOBean;
import org.apache.taglibs.standard.examples.util.ServletResponseWrapperForWriter;

/**
 * <p>Tag handler for &lt;resource&gt;
 *
 * @author Pierre Delisle
 * @version $Revision: 1.3 $ $Date: 2004/02/28 01:01:41 $
 */
public class ResourceTag extends TagSupport {
    
    //*********************************************************************
    // Instance variables
    
    private String id;
    private String resource;
    
    private Reader reader;
    
    //*********************************************************************
    // Constructors
    
    public ResourceTag() {
        super();
        init();
    }
    
    private void init() {
        id = null;
        resource = null;
    }
    
    //*********************************************************************
    // Tag's properties
    
    /**
     * Tag's 'id' attribute
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Tag's 'resource' attribute
     */
    public void setResource(String resource) {
        this.resource = resource;
    }
    
    //*********************************************************************
    // TagSupport methods
    
    public int doStartTag() throws JspException {
        reader = getReaderFromResource(resource);
        exposeVariable(reader);
        return EVAL_BODY_INCLUDE;
    }
    
    public int doEndTag() throws JspException {
        try {
            reader.close();
        } catch (IOException ex) {}
        reader = null;
        return EVAL_PAGE;
    }
    
    /**
     * Releases any resources we may have (or inherit)
     */
    public void release() {
        super.release();
        init();
    }
    
    //*********************************************************************
    // Tag's scific behavior methods
    
    private Reader getReaderFromResource(String name) throws JspException {
        HttpServletRequest request =
        (HttpServletRequest)pageContext.getRequest();
        HttpServletResponse response =
        (HttpServletResponse)pageContext.getResponse();
        RequestDispatcher rd = null;
        
        // The response of the local URL becomes the reader that
        // we export. Need temporary storage.
        IOBean ioBean = new IOBean();
        Writer writer = ioBean.getWriter();
        ServletResponseWrapper responseWrapper =
        new ServletResponseWrapperForWriter(
        response, new PrintWriter(writer));
        rd = pageContext.getServletContext().getRequestDispatcher(name);
        try {
            rd.include(request, responseWrapper);
            return ioBean.getReader();
        } catch (Exception ex) {
            throw new JspException(ex);
        }
    }
    
    //*********************************************************************
    // Utility methods
        
    private void exposeVariable(Reader reader) {
        if (id != null) {
            pageContext.setAttribute(id, reader);
        }
    }
}
