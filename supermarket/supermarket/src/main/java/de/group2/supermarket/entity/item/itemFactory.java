package de.group2.supermarket.entity.item;

public class ItemFactory {

    public static Item createReducedTaxItem(String name, String category, double price) {
        validateInputs(name, category, price);
        return new Item(name, category, price, true); // isReduced = true für reduzierten Steuersatz
    }

    public static Item createStandardTaxItem(String name, String category, double price) {
        validateInputs(name, category, price);
        return new Item(name, category, price, false); // isReduced = false für normalen Steuersatz
    }

    // Methode zur Validierung der Eingabewerte
    private static void validateInputs(String name, String category, double price) {
        if (name == null || name.isEmpty() || name.length() < 2) {
            throw new IllegalArgumentException("Name darf nicht null oder leer sein. Name muss mindestens 2 Buchstaben lang sein");
        }
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Kategorie darf nicht null oder leer sein.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Preis muss größer als 0 sein.");
        }
    }
}
