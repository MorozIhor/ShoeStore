package ua.com.myshop.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.myshop.dto.filter.CommodityFilter;
import ua.com.myshop.entity.Commodity;

public class CommoditySpecification implements Specification<Commodity>{

	private  CommodityFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private static final Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");

	public CommoditySpecification(CommodityFilter filter) {
		this.filter = filter;
		if(REG.matcher(filter.getMax()).matches()){
			filter.setMaxValue(Double.valueOf(filter.getMax().replace(',', '.')));
		}
		if(REG.matcher(filter.getMin()).matches()){
			filter.setMinValue(Double.valueOf(filter.getMin().replace(',', '.')));
		}
	}

	private void findByBrand(Root<Commodity> root, CriteriaQuery<?> query){
		if(!filter.getBrandId().isEmpty()){
			predicates.add(root.get("brand").in(filter.getBrandId()));
		}
	}
	
	private void findByGender(Root<Commodity> root, CriteriaQuery<?> query){
		if(!filter.getGenderId().isEmpty()){
			predicates.add(root.get("gender").in(filter.getGenderId()));
		}
	}
	
	private void findBySeason(Root<Commodity> root, CriteriaQuery<?> query){
		if(!filter.getSeasonId().isEmpty()){
			predicates.add(root.get("season").in(filter.getSeasonId()));
		}
	}
	
	private void findByTypeOfShoes(Root<Commodity> root, CriteriaQuery<?> query){
		if(!filter.getTypeOfShoesId().isEmpty()){
			predicates.add(root.get("typeOfShoes").in(filter.getTypeOfShoesId()));
		}
	}
	
	private void findByPrice(Root<Commodity> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxValue()!=0){
			predicates.add(cb.le(root.get("price"), filter.getMaxValue()));
		}
		if(filter.getMinValue()!=0){
			predicates.add(cb.ge(root.get("price"), filter.getMinValue()));
		}
	}
	
	private void fetch(Root<Commodity> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			query.distinct(true);
			root.fetch("brand");
			root.fetch("gender");
			root.fetch("season");
			root.fetch("typeOfShoes");
		}
	}
	
	
	@Override
	public Predicate toPredicate(Root<Commodity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		findByBrand(root, query);
		findByGender(root, query);
		findBySeason(root, query);
		findByTypeOfShoes(root, query);
		findByPrice(root, query, cb);
		if(predicates.isEmpty()) return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}
	
	
}
