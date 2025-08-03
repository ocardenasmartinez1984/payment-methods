package cl.billy.utils;

import cl.billy.entities.Invoice;
import cl.billy.entities.InvoicesWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class ReporterUtil {

    public static void createReport(InvoicesWrapper invoicesWrapper) throws IOException {
        var paymentMethodBuilder = new StringBuilder();
        var totalDocumentsBuilder = new StringBuilder();
        var totalPaymentMethodsBuilder = new StringBuilder();
        var sumDocument = 0L;
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
        for (Map.Entry<String, Long> entry : groupPaymentMethods.entrySet()) {
            paymentMethodBuilder.append("<th>");
            paymentMethodBuilder.append(entry.getKey());
            paymentMethodBuilder.append("</th>");
            totalDocumentsBuilder.append("<td>");
            totalDocumentsBuilder.append(entry.getValue());
            totalDocumentsBuilder.append("</td>");
            sumDocument += entry.getValue();
        }
        for (Map.Entry<String, Double> entry : totalPaymentMethods.entrySet()) {
            totalPaymentMethodsBuilder.append("<td>");
            totalPaymentMethodsBuilder.append(entry.getValue());
            totalPaymentMethodsBuilder.append("</td>");
        }
        var report = "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Reporte documentos</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "      background-color: #f4f6f8;\n" +
                "      padding: 40px;\n" +
                "    }\n" +
                "\n" +
                "    h2 {\n" +
                "      text-align: center;\n" +
                "      color: #333;\n" +
                "    }\n" +
                "\n" +
                "    table {\n" +
                "      width: 80%;\n" +
                "      margin: auto;\n" +
                "      border-collapse: collapse;\n" +
                "      background-color: #fff;\n" +
                "      box-shadow: 0 0 10px rgba(0,0,0,0.1);\n" +
                "      border-radius: 8px;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "\n" +
                "    thead {\n" +
                "      background-color: #4a90e2;\n" +
                "      color: white;\n" +
                "    }\n" +
                "\n" +
                "    th, td {\n" +
                "      padding: 12px 20px;\n" +
                "      text-align: left;\n" +
                "      border-bottom: 1px solid #eaeaea;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <h2>Reporte Documentos</h2>\n" +
                "  <table>\n" +
                "    <thead>\n" +
                "      <tr>\n" +
                "        <th>&nbsp;</th>\n" +
                paymentMethodBuilder +
                "        <th>Totales</th>\n" +
                "      </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n" +
                "      <tr>\n" +
                "        <td>Cantidad Docs</td>\n" +
                totalDocumentsBuilder +
                "        <td>" + sumDocument + "</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td>Total a Pagar</td>\n" +
                totalPaymentMethodsBuilder +
                "        <td>" + totalSum + "</td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>\n";
        var path = Paths.get("../output/reporte.html");
        Files.writeString(path, report);
        System.out.println("Reporte generado en: " + path);
    }

}
