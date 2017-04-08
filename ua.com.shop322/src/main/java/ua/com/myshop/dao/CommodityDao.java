package ua.com.myshop.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;





import ua.com.myshop.entity.Commodity;


public interface CommodityDao extends JpaRepository <Commodity, Integer>, JpaSpecificationExecutor<Commodity> {

	@Query("SELECT c FROM Commodity c LEFT JOIN FETCH c.brand LEFT JOIN FETCH c.gender "
			+ "LEFT JOIN FETCH c.season LEFT JOIN FETCH c.typeOfShoes")
	List<Commodity> findAll();
	@Query("SELECT c FROM Commodity c LEFT JOIN FETCH c.brand LEFT JOIN FETCH c.gender "
			+ "LEFT JOIN FETCH c.season LEFT JOIN FETCH c.typeOfShoes WHERE c.id=?1")
	Commodity findOne(int id);
	@Query("SELECT c FROM Commodity c WHERE c.brand.id=?1 and c.gender.id=?2 and c.season.id=?3 "
			+ "and c.typeOfShoes.id=?4 and c.price=?5")
	Commodity findUnique(int brandId, int genderId, int seasonId,
			int typeOfShoesId, double price);
	@Query(value="SELECT c FROM Commodity c LEFT JOIN FETCH c.brand LEFT JOIN FETCH c.gender "
			+ "LEFT JOIN FETCH c.season LEFT JOIN FETCH c.typeOfShoes", 
			countQuery="SELECT count(c.id) FROM Commodity c")
	Page<Commodity> findAll(Pageable pageable);
	

}
