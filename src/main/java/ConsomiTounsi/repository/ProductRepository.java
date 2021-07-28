package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import ConsomiTounsi.entities.TypeCategory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    /*
    @Query(value="select * from product where order_id_order=?1",nativeQuery = true)
    List<Product> FindByOrder(Long id);

     */

    List<Product> findProductByName(String name);
    List<Product> findAllByOrderByRatingDesc();
    List<Product> findAllByOrderByPriceDesc();
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findProductByCategory(String category);
    Optional<Product> findTopByOrderByIdDesc() ;

    @Query(value="select * from Product where shelf_id_shelf=?1",nativeQuery = true)
    List<Product> FindProductByIdShelf(long id);

    @Query(value="SELECT COUNT(p.name_product) FROM product p WHERE p.shelf_id_shelf=:idshelf",nativeQuery = true)
    int getNbProductsByshelf(long idshelf);

    @Modifying
    @Query (value="UPDATE Product p SET p.quantity_product=:qn WHERE p.id=:id",nativeQuery = true)
    int AddQuantity(@Param("qn") long qn, @Param("id") long id);

    @Modifying
    @Query(value="UPDATE Product P SET P.quantity_product=0 WHERE p.id=:id" ,nativeQuery= true )
    int resetQuantity(@Param("id") long id);
	




 /*   @Query("select id from product order by id desc  limit 1 \n")
    Optional<Product> findLastItemAdde () ;*/

	
	@Query("SELECT p FROM Product p WHERE p.categoryProduct like 'makeUp%' or p.categoryProduct like 'Stationery%' " )
	List<Product> findByCategoryProduct();
	
	
	@Query("SELECT p FROM Product p WHERE p.categoryProduct like 'ShavingTools%' or p.categoryProduct like 'Stationery%' " )
	List<Product> findByCategoryProduct2();
	
	@Query("SELECT p FROM Product p WHERE p.categoryProduct like 'Tools%' " )
	List<Product> findByCategoryProduct3();
	
	@Query("SELECT p FROM Product p WHERE p.categoryProduct like 'makeUp%' " )
	List<Product> findByCategoryProduct4();
	
	@Query("SELECT p FROM Product p WHERE p.categoryProduct like 'Cosmetics%' or  p.categoryProduct like 'ShavingTools%' " )
	List<Product> findByCategoryProduct5();
	
	@Query("SELECT p FROM Product p WHERE p.categoryProduct like 'Toys%' " )
	List<Product> findByCategoryProduct6();

}


