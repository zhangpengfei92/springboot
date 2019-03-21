package com.jcl.gycms.uitl.validation.number;



import com.jcl.gycms.uitl.validation.ValidationUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 数字校验
 */
public class NumberValidationImpl implements ConstraintValidator<NumberValidation, Object> {

    private boolean required;

    private int verifyType;

    @Override
    public void initialize(NumberValidation constraintAnnotation) {
        this.required = constraintAnnotation.required();
        this.verifyType = constraintAnnotation.verifyType();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (required) {
            return ValidationUtil.isNumber(value, verifyType);
        } else {
            if (value == null) {
                return true;
            } else {
                return ValidationUtil.isNumber(value, verifyType);
            }
        }
    }
}
