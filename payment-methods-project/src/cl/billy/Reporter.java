package cl.billy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Reporter {

    public void createReport(Map<String, Long> groupPaymentMethods, Map<String, Double> totalPaymentMethodsIn, double sumaTotal) throws IOException {
        var paymethods = new StringBuilder();
        var totalDocuments = new StringBuilder();
        var totalPaymentMethods = new StringBuilder();
        var sumDocument = 0L;
        for (Map.Entry<String, Long> entry : groupPaymentMethods.entrySet()) {
            paymethods.append("<th>");
            paymethods.append(entry.getKey());
            paymethods.append("</th>");
            totalDocuments.append("<td>");
            totalDocuments.append(entry.getValue());
            totalDocuments.append("</td>");
            sumDocument += entry.getValue();
        }
        for (Map.Entry<String, Double> entry : totalPaymentMethodsIn.entrySet()) {
            totalPaymentMethods.append("<td>");
            totalPaymentMethods.append(entry.getValue());
            totalPaymentMethods.append("</td>");
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
                paymethods +
                "        <th>Totales</th>\n" +
                "      </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n" +
                "      <tr>\n" +
                "        <td>Cantidad Docs</td>\n" +
                totalDocuments +
                "        <td>" + sumDocument + "</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td>Total a Pagar</td>\n" +
                totalPaymentMethods +
                "        <td>" + sumaTotal + "</td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>\n";
        var path = Paths.get("../output/reporte.html");
        Files.write(path, report.getBytes(StandardCharsets.UTF_8));
        System.out.println("Reporte generado en: " + path);
    }

}
