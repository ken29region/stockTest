package ken.stockTest.repositories;

public interface Repository<T>{

    Long add(T t);

    T getById(Long id);

    void set(T t);

    void delete(T t);
}
