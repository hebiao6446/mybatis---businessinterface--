package com.common;

import java.sql.Types;

import org.hibernate.dialect.MySQLDialect;

public class DialectForHibernate  extends MySQLDialect {  
    public DialectForHibernate() {  
        super();  
        registerHibernateType(Types.LONGVARCHAR, 65535, "text");  
    }  
}
