package fr.syrql.botentry.bots.provider;

public interface IProvider<K, V> {

    void provide(K key, V value);

    void remove(K key);

    V get(K key);
}
