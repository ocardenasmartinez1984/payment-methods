package cl.billy.utils;

import cl.billy.entities.InvoicesWrapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InvoiceXmlWriterUtil {

    public static void createXml(InvoicesWrapper wrapper) {
        var outputDir = new File("../output");
        if (!outputDir.exists()) outputDir.mkdirs();
        var outputFile = new File(outputDir, "dte.xml");
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             var writer = new OutputStreamWriter(fos, StandardCharsets.ISO_8859_1)) {
            var context = JAXBContext.newInstance(InvoicesWrapper.class);
            var marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            writer.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>\n");
            marshaller.marshal(wrapper, writer);
            System.out.println("Archivo xml DTE generado en: " + outputFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}


