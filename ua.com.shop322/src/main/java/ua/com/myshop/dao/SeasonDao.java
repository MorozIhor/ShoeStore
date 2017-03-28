package ua.com.myshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.myshop.entity.Season;

public interface SeasonDao  extends JpaRepository<Season, Integer> {

}
