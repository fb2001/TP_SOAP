package org.examples;

public class CarteBancaire {
    private long cartecredit;  // Suppression de 'static'

    public long getCartecredit() {
        return cartecredit;
    }

    public void setCartecredit(long cartecredit) {
        this.cartecredit = cartecredit;
    }

    public CarteBancaire(long cartecredit) {
        this.cartecredit = cartecredit;
    }
}
