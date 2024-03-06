package Laba3_4;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Product[] products = getUserInput(scanner);

            System.out.println("Полная информация о всех объектах массива:");
            for (Product product : products) {
                System.out.println(product);
            }
            System.out.println();

            // Тестирование работы с потоками
            testStreamMethods(products);

            // Тестирование сериализации
            testSerialization(products);
        } catch (InvalidPriceException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Product[] getUserInput(Scanner scanner) throws InvalidPriceException {
        System.out.println("Введите количество продуктов:");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Product[] products = new Product[count];

        for (int i = 0; i < count; i++) {
            System.out.println("Выберите тип продукта (1 - ProductItem, 2 - Goods):");
            int type = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            System.out.println("Введите название продукта:");
            String name = scanner.nextLine();

            System.out.println("Введите цену продукта:");
            int price = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            System.out.println("Введите количество компонентов:");
            int componentCount = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            String[] components = new String[componentCount];
            for (int j = 0; j < componentCount; j++) {
                System.out.println("Введите компонент " + (j + 1) + ":");
                components[j] = scanner.nextLine();
            }

            if (type == 1) {
                products[i] = new ProductItem(components, name, price);
            } else if (type == 2) {
                products[i] = new Goods(components, name, price);
            } else {
                System.out.println("Некорректный тип продукта. Используется тип ProductItem.");
                products[i] = new ProductItem(components, name, price);
            }
        }

        return products;
    }

    private static void testStreamMethods(Product[] products) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            for (Product product : products) {
                objectOutputStream.writeObject(product);
                objectOutputStream.reset(); // Сброс состояния потока
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            Product[] deserializedProducts = new Product[products.length];
            for (int i = 0; i < products.length; i++) {
                deserializedProducts[i] = (Product) objectInputStream.readObject();
            }

            System.out.println("Тестирование методов работы с потоками успешно завершено.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testSerialization(Product[] products) {
        try {
            for (int i = 0; i < products.length; i++) {
                serializeProduct(products[i], "product" + (i + 1) + ".ser");
            }

            for (int i = 0; i < products.length; i++) {
                Product deserializedProduct = deserializeProduct("product" + (i + 1) + ".ser");
                System.out.println("Десериализованный продукт " + (i + 1) + ": " + deserializedProduct);
            }

            System.out.println("Тестирование сериализации успешно завершено.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void serializeProduct(Product product, String fileName) throws IOException {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOut.writeObject(product);
            System.out.println("Объект " + product.getClass().getSimpleName() + " сериализован в файл " + fileName);
        }
    }

    public static Product deserializeProduct(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Product product = (Product) objectInputStream.readObject();
            System.out.println("Объект из файла " + fileName + " успешно десериализован");
            return product;
        }
    }
}