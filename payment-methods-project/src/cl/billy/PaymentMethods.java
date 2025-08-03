package cl.billy;

import cl.billy.entities.Document;
import cl.billy.entities.DocumentWrapper;
import cl.billy.entities.Invoice;
import cl.billy.entities.InvoicesWrapper;
import cl.billy.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PaymentMethods {
    public static void main(String[] args) {
        System.out.println("Tiempo de inicio: " + LocalDateTime.now());
        var inputFile = "";
        try {
            inputFile = args[0];
        }catch (IndexOutOfBoundsException e) {
            System.out.println("No se definio archivo de entrada, pruebe de esta forma: ");
            System.out.println("java -jar payment-methods.jar: ../entrega.json");
            return;
        }
        try {
            var outJson = FileReaderUtil.readFile(inputFile);
            var wrapper = new ObjectMapper().readValue(outJson, DocumentWrapper.class);
            var documents = wrapper.getDocuments();
            var invoicesWrapper = new InvoicesWrapper();
            var listInvoices = new ArrayList<Invoice>();
            for (Document doc : documents) {
                var decompressed = JsonExtractorUtil.extract(doc.getContentBase64());
                var invoice = ReadJsonFromStringUtil.read(decompressed);
                listInvoices.add(invoice);
            }
            invoicesWrapper.setInvoices(listInvoices);
            InvoiceXmlWriterUtil.createXml(invoicesWrapper);
            ReporterUtil.createReport(invoicesWrapper);
        } catch (IOException e) {
            System.out.println("Error ejecutando el proceso: ");
            e.printStackTrace();
        }
        System.out.println("Tiempo final: " + LocalDateTime.now());
    }

}

