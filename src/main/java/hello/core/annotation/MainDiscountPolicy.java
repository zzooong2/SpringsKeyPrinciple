package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/* 어노테이션을 제작하여 사용
 * Qualifier 괄호 안에 텍스트를 잘못 기입하여도 컴파일에서는 오류를 찾아내지 못함
 * 하지만 어노테이션을 제작하고, Qualifier에 부여된 어노테이션을 동일하게 붙여준다면
 * 제작한 어노테이션 사용하여 Qualifier와 동일하게 사용할 수 있다
 * */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
