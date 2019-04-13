package org.jellyfin.apiclient.model.registration;

/**
 * Created by Eric on 10/16/2015.
 */
public class AppstoreRegRequest {
    private String store;
    private String application;
    private String product;
    private String feature;
    private String type;
    private String storeId;
    private String storeToken;
    private String email;
    private String amt;

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreToken() {
        return storeToken;
    }

    public void setStoreToken(String storeToken) {
        this.storeToken = storeToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
