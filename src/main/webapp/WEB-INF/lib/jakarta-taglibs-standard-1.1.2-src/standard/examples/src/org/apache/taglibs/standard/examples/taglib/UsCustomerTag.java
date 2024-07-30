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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

import org.apache.taglibs.standard.examples.beans.Customer;

/**
 * <p>Tag handler for &lt;usCustomer&gt;
 *
 * @author Pierre Delisle
 * @version $Revision: 1.3 $ $Date: 2004/02/28 01:01:41 $
 */
public class UsCustomerTag extends ConditionalTagSupport {
    
    //*********************************************************************
    // Instance Variables
    
    /** Holds value of property customer. */
    private Customer customer;
    
    //*********************************************************************
    // Constructor and lusCustomerecycle management
    
    public UsCustomerTag() {
        super();
        init();
    }
    
    private void init() {
        customer = null;
    }
    
    //*********************************************************************
    // TagSupport methods
    
    public void release() {
        super.release();
        init();
    }
    
    //*********************************************************************
    // ConditionalTagSupport methods
    
    protected boolean condition() throws JspTagException {
        try {
            if (customer == null) {
                throw new NullAttributeException("usCustomer", "test");
            } else {
                //System.out.println("country: " + customer.getAddress().getCountry());
                return (customer.getAddress().getCountry().equalsIgnoreCase("USA"));
            }
        } catch (JspException ex) {
            throw new JspTagException(ex.toString());
        }
    }
    
    //*********************************************************************
    // Accessors
    
    /**
     * Getter for property customer.
     * @return Value of property customer.
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Setter for property customer.
     * @param customer New value of property customer.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
