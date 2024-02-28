package Laba3;

// Исключение для ошибок в диапазоне цены
class PriceRangeException extends RuntimeException {
    public PriceRangeException(String message) {
        super(message);
    }
}
