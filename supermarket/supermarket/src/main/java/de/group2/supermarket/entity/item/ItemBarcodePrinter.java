package de.group2.supermarket.entity.item;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import de.group2.supermarket.bonprintextended.POS;
import de.group2.supermarket.bonprintextended.POSBarcode;
import de.group2.supermarket.bonprintextended.POSPrinter;
import de.group2.supermarket.bonprintextended.POSQRCode;
import de.group2.supermarket.bonprintextended.POSReceipt;

public class ItemBarcodePrinter {

    public static void printItemBarcode(Item item) {
        try {
            // Find the printer by name
            //PrintService printerService = findPrintService("OLIVETTI PRT80");
            PrintService printerService = findPrintService("POS80 Printer");

            if (printerService == null) {
                System.out.println("Printer not found");
                return;
            }

            // Create a new POSPrinter instance
            POSPrinter posPrinter = new POSPrinter();

            // Create a new receipt
            POSReceipt receipt = new POSReceipt();

            // Add some items to the receipt
            receipt.addItem(item.getName(), item.getPrice());

            // Create and add a barcode to the receipt
            POSBarcode barcode = new POSBarcode(item.getId(), POS.BarcodeType.CODE128);
            barcode.setHeight(162);
            barcode.setWidth(POS.BarWidth.DEFAULT);
            receipt.addBarcode(barcode);
            
            /*
            POSQRCode qrcode = new POSQRCode("www.google.com", POS.ErrorCorrection.PERCENT_15, POS.QrCodeSize.EXTRA_LARGE);
            receipt.addQRCode(qrcode);;
            */

            // Print the receipt using the POSPrinter
            posPrinter.print(receipt, printerService);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to find a printer by name
    public static PrintService findPrintService(String printerName) {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                return service;
            }
        }
        return null;
    }
}
