package cl.billy;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "name", "type", "totalAPagar", "medioPago" })
public class Invoice {

    @XmlAttribute(name = "ID")
    private String id;

    @XmlElement(name = "Cliente")
    private String name;

    @XmlElement(name = "Tipo")
    private String type;

    @XmlElement(name = "TotalAPagar")
    private String totalAPagar;

    @XmlElement(name = "MedioPago")
    private String medioPago;

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getTotalAPagar() {
        return totalAPagar;
    }
    public void setTotalAPagar(String totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public String getMedioPago() {
        return medioPago;
    }
    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }
}
