package de.group2.supermarket.entity.receipt;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import de.group2.supermarket.bonprintextended.POS;
import de.group2.supermarket.bonprintextended.POSPrinter;
import de.group2.supermarket.bonprintextended.POSQRCode;
import de.group2.supermarket.bonprintextended.POSReceipt;
import de.group2.supermarket.bonprintextended.SupermarketPrinter;
import de.group2.supermarket.entity.itemPosition.ItemPosition;
import de.group2.supermarket.entity.logging.Logger;

public class ReceiptPrintJob {

    public void printReceipt(Receipt receipt) {  
        
        try {
            // Find the printer by name
            //PrintService printerService = findPrintService("OLIVETTI PRT80");
            PrintService printerService = findPrintService("POS80 Printer");

            if (printerService == null) {
                System.out.println("Printer not found");
                return;
            }

            // Create a new POSPrinter instance
            SupermarketPrinter posPrinter = new POSPrinter();

            // Create a new receiptPrint
            POSReceipt receiptPrint = new POSReceipt();
            receiptPrint.setTitle("Supermarket Group 2");
            receiptPrint.setAddress("Erzbergerstrasse 121 \n76133 Karlsruhe");
            receiptPrint.setPhone("0123456789");

            // Add empty space
            receiptPrint.addFeed(2);

            // Add metadata
            receiptPrint.addText(receipt.getDate());
            receiptPrint.addText(receipt.getTime());
            receiptPrint.addText("Cashier: " + receipt.getCashier());

            // Add empty space
            receiptPrint.addFeed(2);

            // Add some items to the receiptPrint
            try{
                addItemsToReceiptTest(receiptPrint, receipt);
            } catch (Exception e) {
                Logger l = Logger.getInstance();
                l.log(e.getMessage());
            }

            // Add empty space
            receiptPrint.addFeed(2);

            // Add Tax
            receiptPrint.setTax(addTotalTax(receiptPrint, receipt));
            // Add Total
            receiptPrint.setTotal(receipt.getItemList().getTotalPrice());

        

            POSQRCode qrcode = new POSQRCode("www.woistmanuel.de", POS.ErrorCorrection.PERCENT_15, POS.QrCodeSize.EXTRA_LARGE);
            receiptPrint.addQRCode(qrcode);
            
            // Set a footer for the receiptPrint
            receiptPrint.setFooterLine("Thank you for shopping!");

            // Print the receiptPrint using the POSPrinter
            posPrinter.print(receiptPrint, printerService);

        } catch (Exception e) {
            Logger l = Logger.getInstance();
            l.log(e.getMessage());
        }
    }

        // Receipt with the ItemPositions which doesnt work, because its probably too long
    /* private void addItemsToReceipt(POSReceipt receiptPrint, Receipt receipt) {
        for (ItemPosition itemPosition : receipt.getItemList().getItemPositions()) {
            receiptPrint.addItem(itemPosition);
        }
    } */

    private double addTotalTax(POSReceipt receiptPrint, Receipt receipt) {
        double totalTax = 0;
        for (ItemPosition itemPosition : receipt.getItemList().getItemPositions()) {
            totalTax += calculateTaxForPosition(itemPosition);
        }
        return totalTax;
    }

    private double calculateTaxForPosition(ItemPosition itemPosition) {
        double netto = itemPosition.getItem().getPrice() * itemPosition.getAmount();
        return netto * (itemPosition.getItem().getTaxRate() / 100);
    }

    private void addItemsToReceiptTest(POSReceipt receiptPrint, Receipt receipt) {
        for (ItemPosition itemPosition : receipt.getItemList().getItemPositions()) {
            receiptPrint.addText(itemPosition.getItem().getName());
            //receiptPrint.addItem(itemPosition.getItem().getName(), (itemPosition.getItem().getPrice() * ((100 + itemPosition.getItem().getTaxRate())/100)));
            receiptPrint.addItem("Amount: " + itemPosition.getAmount(), (itemPosition.getItem().getPrice() * itemPosition.getAmount() * ((100 + itemPosition.getItem().getTaxRate())/100)));
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
