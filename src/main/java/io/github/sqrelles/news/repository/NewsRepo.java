package io.github.sqrelles.news.repository;

import io.github.sqrelles.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface NewsRepo extends JpaRepository<News, Integer> {
    @Query("SELECT n FROM News n WHERE " +
            "FUNCTION('YEAR', n.date) = :year AND " +
            "FUNCTION('MONTH', n.date) = :month AND " +
            "FUNCTION('DAY', n.date) = :day")
    List<News> findAllByDate(@Param("year") int year, @Param("month") int month, @Param("day") int day);



    @Query("SELECT n FROM News n WHERE n.date BETWEEN :startDate AND :endDate")
    List <News> findAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

   /* @Transactional
    @Modifying
    @Query(value = "ALTER TABLE news MODIFY COLUMN image LONGBLOB", nativeQuery = true)
    void alterImageColumn();*/

}
