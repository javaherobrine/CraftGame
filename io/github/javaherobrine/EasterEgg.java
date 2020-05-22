package io.github.javaherobrine;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.*;
@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD })
public @interface EasterEgg {
}
