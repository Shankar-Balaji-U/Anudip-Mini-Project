package com.anudipgroupproject.socialize.models.fields.annotations;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;


@Target(FIELD)
@Retention(RUNTIME)
public @interface FileColumn {
	
    String folder() default "uploads";
    
    String name() default "";
    
    int length() default 255;
    
    boolean nullable() default true;
}