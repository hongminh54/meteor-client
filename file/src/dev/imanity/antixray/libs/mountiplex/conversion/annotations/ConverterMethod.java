// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.libs.mountiplex.conversion.annotations;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConverterMethod {
    String input() default "";
    
    String output() default "";
    
    boolean acceptsNull() default false;
    
    boolean optional() default false;
    
    int cost() default 1;
}
