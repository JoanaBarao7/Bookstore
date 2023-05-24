package com.solvd.bookstore.entities;

import com.solvd.bookstore.interfaces.IPaymentMethod;

public enum PaymentMethod {
    CREDIT_CARD(Creditcard.class),
    PAYPAL(Paypal.class);

    private Class<? extends IPaymentMethod> paymentMethodClass;

    PaymentMethod(Class<? extends IPaymentMethod> paymentMethodClass) {
        this.paymentMethodClass = paymentMethodClass;
    }

    public Class<? extends IPaymentMethod> getPaymentMethodClass() {
        return paymentMethodClass;
    }
}
