package cl.billy.entities;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "DTE")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoicesWrapper {

    @XmlAttribute
    private String version = "1.0";

    @XmlElementWrapper(name = "Documentos")
    @XmlElement(name = "Documento")
    private List<Invoice> invoices;

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
