package cl.billy;

import cl.billy.entities.*;
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
            var documents = new ObjectMapper().readValue(FileReaderUtil.readFile(inputFile), DocumentWrapper.class).getDocuments();
            var invoicesWrapper = new InvoicesWrapper();
            var listInvoices = new ArrayList<Invoice>();
            for (Document docs : documents)
                listInvoices.add(ReadJsonFromStringUtil.read(JsonExtractorUtil.extract(docs.getContentBase64())));
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

