package cl.billy;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {

    @JsonProperty("DocType")
    private String DocType;
    @JsonProperty("NroDocInterno")
    private String NroDocInterno;
    @JsonProperty("ContentBase64")
    private String ContentBase64;

    public String getDocType() {
        return DocType;
    }
    public void setDocType(String docType) {
        this.DocType = docType;
    }

    public String getNroDocInterno() {
        return NroDocInterno;
    }
    public void setNroDocInterno(String nroDocInterno) {
        this.NroDocInterno = nroDocInterno;
    }

    public String getContentBase64() {
        return ContentBase64;
    }
    public void setContentBase64(String contentBase64) {
        this.ContentBase64 = contentBase64;
    }
}
