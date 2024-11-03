package de.group2.supermarket.bonprintextended;

import javax.print.PrintService;

public interface SupermarketPrinter {

    void print(POSDocument document, PrintService printerService);
}
