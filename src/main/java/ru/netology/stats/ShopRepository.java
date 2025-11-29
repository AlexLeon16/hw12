package ru.netology.stats;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий с проверкой на дубликаты
     */
    //для задания 2
    public void add(Product product) {
        // Проверяем, нет ли уже товара с таким ID
        Product existingProduct = findById(product.getId());
        if (existingProduct != null) {
            throw new AlreadyExistsException("Element with id: " + product.getId() + " already exists");
        }

        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    /**
     * Метод для поиска товара по ID
     */
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    /**
     * Метод удаления товара по ID с проверкой существования
     */
    public void removeById(int id) {
        Product product = findById(id);
        if (product == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product currentProduct : products) {
            if (currentProduct.getId() != id) {
                tmp[copyToIndex] = currentProduct;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}


