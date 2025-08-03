package cl.billy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
            var outJson = new FileReaderUtil().readFile(inputFile);
            var wrapper = new ObjectMapper().readValue(outJson, DocumentWrapper.class);
            var documents = wrapper.getDocuments();
            var invoicesWrapper = new InvoicesWrapper();
            var listInvoices = new ArrayList<Invoice>();
            for (Document doc : documents) {
                var decompressed = new JsonExtractor().extract(doc.getContentBase64());
                var invoice = new ReadJsonFromString().read(decompressed);
                listInvoices.add(invoice);
            }
            invoicesWrapper.setInvoices(listInvoices);
            new InvoiceXmlWriter().createXml(invoicesWrapper);
            var groupPaymentMethods = invoicesWrapper.getInvoices().stream()
                    .collect(Collectors.groupingBy(Invoice::getMedioPago, Collectors.counting()));
            var totalPaymentMethods = invoicesWrapper.getInvoices().stream()
                    .collect(Collectors.groupingBy(
                            Invoice::getMedioPago,
                            Collectors.summingDouble(inv -> Double.parseDouble(inv.getTotalAPagar()))
                    ));
            var totalSum = invoicesWrapper.getInvoices().stream()
                    .mapToDouble(inv -> Double.parseDouble(inv.getTotalAPagar()))
                    .sum();
            new Reporter().createReport(groupPaymentMethods, totalPaymentMethods, totalSum);
        } catch (IOException e) {
            System.out.println("Error ejecutando el proceso: ");
            e.printStackTrace();
        }
        System.out.println("Tiempo final: " + LocalDateTime.now());
    }

}

