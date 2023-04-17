package  dbaas.demo.mongodbapp.repository;



import dbaas.demo.mongodbapp.model.Fruit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FruitRepository extends MongoRepository<Fruit, String> {
    List<Fruit> findAll();

    Optional<Fruit> findById(String id);

    void deleteById(String id);
}
