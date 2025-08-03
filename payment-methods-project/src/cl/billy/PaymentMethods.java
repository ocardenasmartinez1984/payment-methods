package cl.billy;

import cl.billy.entities.Document;
import cl.billy.entities.DocumentWrapper;
import cl.billy.entities.Invoice;
import cl.billy.entities.InvoicesWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static cl.billy.utils.FileReaderUtil.readFile;
import static cl.billy.utils.InvoiceXmlWriterUtil.createXml;
import static cl.billy.utils.JsonExtractorUtil.extractJson;
import static cl.billy.utils.ReadJsonFromStringUtil.jsonToInvoice;
import static cl.billy.utils.ReporterUtil.createReport;

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
            var documents = new ObjectMapper().readValue(readFile(inputFile), DocumentWrapper.class).getDocuments();
            var invoicesWrapper = new InvoicesWrapper();
            var listInvoices = new ArrayList<Invoice>();
            for (Document docs : documents)
                listInvoices.add(jsonToInvoice(extractJson(docs.getContentBase64())));
            invoicesWrapper.setInvoices(listInvoices);
            createXml(invoicesWrapper);
            createReport(invoicesWrapper);
        } catch (IOException e) {
            System.out.println("Error ejecutando el proceso: ");
            e.printStackTrace();
        }
        System.out.println("Tiempo final: " + LocalDateTime.now());
    }

}

