package de.group2.supermarket.entity.receipt;

import java.util.Arrays;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import de.group2.supermarket.bonprintextended.POS;
import de.group2.supermarket.bonprintextended.POSPrinter;
import de.group2.supermarket.bonprintextended.POSQRCode;
import de.group2.supermarket.bonprintextended.POSReceipt;
import de.group2.supermarket.bonprintextended.POSStyle;
import de.group2.supermarket.entity.ItemList;
import de.group2.supermarket.entity.ItemPosition;
import de.group2.supermarket.entity.item.Item;

public class ReceiptPrintJob {

    public static void main(String[] args) {
        ReceiptPrintJob receiptPrintJob = new ReceiptPrintJob();
        receiptPrintJob.printReceipt(new ItemList(Arrays.asList(new ItemPosition(new Item("Test", "category", 0.19, true), 1)), 1.0));
    }   


    public void printReceipt(ItemList itemList) {  
        Receipt receipt = new Receipt("Hello", "this is the time", "Bob", itemList);
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

            // Create a new receiptPrint
            POSReceipt receiptPrint = new POSReceipt();
            receiptPrint.setTitle("Supermarket Group 2");
            receiptPrint.setAddress("Erzbergerstrasse 121 \n76133 Karlsruhe");
            receiptPrint.setPhone("0123456789");

            // Add empty space
            receiptPrint.addFeed(2);

            // Add some items to the receiptPrint
            try{
                addItemsToReceiptTest(receiptPrint, receipt);
            } catch (Exception e) {
                e.printStackTrace();
            }

            receiptPrint.setTotal(receipt.getItemList().getTotalPrice());

            /*Create and add a barcode to the receiptPrint
            POSBarcode barcode = new POSBarcode(4012345678901L, POS.BarcodeType.JAN13_EAN13);
            barcode.setHeight(162);
            barcode.setWidth(POS.BarWidth.DEFAULT);
            receiptPrint.addBarcode(barcode);
            */

            POSQRCode qrcode = new POSQRCode("www.woistmanuel.de", POS.ErrorCorrection.PERCENT_15, POS.QrCodeSize.EXTRA_LARGE);
            receiptPrint.addQRCode(qrcode);
            
            // Set a footer for the receiptPrint
            receiptPrint.setFooterLine("Thank you for shopping!");

            // Print the receiptPrint using the POSPrinter
            posPrinter.print(receiptPrint, printerService);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addItemsToReceipt(POSReceipt receiptPrint, Receipt receipt) {
        for (ItemPosition itemPosition : receipt.getItemList().getItemPositions()) {
            receiptPrint.addItem(itemPosition);
        }
    }

    private void addItemsToReceiptTest(POSReceipt receiptPrint, Receipt receipt) {
        for (ItemPosition itemPosition : receipt.getItemList().getItemPositions()) {
            receiptPrint.addText(itemPosition.getItem().getName());
            //receiptPrint.addItem(itemPosition.getItem().getName(), (itemPosition.getItem().getPrice() * ((100 + itemPosition.getItem().getTaxRate())/100)));
            receiptPrint.addItem("Amount: " + itemPosition.getAmount(), (itemPosition.getItem().getPrice() * ((100 + itemPosition.getItem().getTaxRate())/100)));
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