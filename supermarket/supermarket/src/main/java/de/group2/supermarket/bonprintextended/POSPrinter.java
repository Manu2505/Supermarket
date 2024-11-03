package de.group2.supermarket.bonprintextended;

import de.group2.supermarket.entity.logging.Logger;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.PrintException;

public class POSPrinter implements SupermarketPrinter{

    public void print(POSDocument document, PrintService printerService)  {
        // Convert the POSDocument to byte array for printing
    	
    	byte[] emptyBufferSpace = new byte[100];  // You can increase the size of the buffer space
        // Fill with spaces or use '\n' for line feeds
        Arrays.fill(emptyBufferSpace, (byte) ' ');
        
        byte[] printData = concat(
            new byte[]{POS.Command.ESC, POS.Command.INIT},   // Printer initialization command
            emptyBufferSpace,
            document.toBytes(),                             // The document data
            new byte[]{POS.Command.LINE_FEED},              // The document data
            POS.Command.ESC_CUT);                          // Cut the page at the end

        // Send the data to the printer
        DocPrintJob job = printerService.createPrintJob();
        Doc doc = new SimpleDoc(new ByteArrayInputStream(printData), DocFlavor.INPUT_STREAM.AUTOSENSE, null);

        try {
            job.print(doc, null);
        } catch (PrintException e) {
            Logger l = Logger.getInstance();
            l.log("Error during printing: " + e.getMessage());
        }
    }

    // Helper method to concatenate multiple byte arrays
    private static byte[] concat(byte[]... arrays) {
        int totalLength = 0;
        for (byte[] array : arrays) {
            totalLength += array.length;
        }

        byte[] result = new byte[totalLength];
        int currentPos = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, result, currentPos, array.length);
            currentPos += array.length;
        }

        return result;
    }
}