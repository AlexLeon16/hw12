package ru.netology.stats;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ShopRepositoryTest {

    @Test
    public void testRemoveExistingProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);
        Product product3 = new Product(3, "Product 3", 300);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        // Act
        repository.removeById(2);

        // Assert
        Product[] result = repository.findAll();
        Assertions.assertEquals(2, result.length);
        Assertions.assertEquals(product1, result[0]);
        Assertions.assertEquals(product3, result[1]);
        Assertions.assertNull(repository.findById(2));
    }

    @Test
    public void testRemoveNonExistingProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        repository.add(product1);

        // Act & Assert
        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class,
                () -> repository.removeById(999)
        );

        Assertions.assertEquals("Element with id: 999 not found", exception.getMessage());
    }

    @Test
    public void testAddNewProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product = new Product(1, "New Product", 150);

        // Act
        repository.add(product);

        // Assert
        Product[] result = repository.findAll();
        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(product, result[0]);
        Assertions.assertEquals(product, repository.findById(1));
    }

    @Test
    public void testAddExistingProduct() {
        // Arrange
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(1, "Product 2", 200); // Тот же ID

        repository.add(product1);

        // Act & Assert
        AlreadyExistsException exception = Assertions.assertThrows(
                AlreadyExistsException.class,
                () -> repository.add(product2)
        );

        Assertions.assertEquals("Element with id: 1 already exists", exception.getMessage());

        // Проверяем, что оригинальный продукт не изменился
        Product[] result = repository.findAll();
        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(product1, result[0]);
    }
}