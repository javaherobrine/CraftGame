package io.github.javaherobrine.chemistry;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.*;
@Documented
@Retention(RUNTIME)
@Target({ METHOD, CONSTRUCTOR })
public @interface ReactionMethod {

}
