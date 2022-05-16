package Hka.prueba.recibodeventa.entity;

public class Recibos {

    private String nomnbre_cliente;
    private String cedula_cliente;
    private String razon_emisor;
    private String nrif_emisor;
    private String total_items;
    private String Total_recibo;

    public String getNomnbre_cliente() {
        return nomnbre_cliente;
    }

    public void setNomnbre_cliente(String nomnbre_cliente) {
        this.nomnbre_cliente = nomnbre_cliente;
    }

    public String getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(String cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public String getRazon_emisor() {
        return razon_emisor;
    }

    public void setRazon_emisor(String razon_emisor) {
        this.razon_emisor = razon_emisor;
    }

    public String getNrif_emisor() {
        return nrif_emisor;
    }

    public void setNrif_emisor(String nrif_emisor) {
        this.nrif_emisor = nrif_emisor;
    }

    public String getTotal_items() {
        return total_items;
    }

    public void setTotal_items(String total_items) {
        this.total_items = total_items;
    }

    public String getTotal_recibo() {
        return Total_recibo;
    }

    public void setTotal_recibo(String total_recibo) {
        Total_recibo = total_recibo;
    }
}
