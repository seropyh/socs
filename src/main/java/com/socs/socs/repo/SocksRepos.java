package com.socs.socs.repo;

import com.socs.socs.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SocksRepos extends JpaRepository<Socks,Long> {
    @Query ("select quantity from Socks  where color =:color and cottonPart = :cottonPart")
    int getCountSocks(@Param ("color") String color,  @Param("cottonPart") int cottonPart);
    @Modifying
    @Query ("update Socks set quantity = :quantity where color =:color and cottonPart = :cottonPart")
    int SetCountSocks(@Param ("color") String color, @Param("cottonPart") int cottonPart, @Param("quantity") int quantity);
    @Query ("select count(*) from Socks  where color =:color and cottonPart = :cottonPart")
    int exists(@Param ("color") String color, @Param("cottonPart") int cottonPart);
    @Query ("select sum(quantity) from Socks  where color = :color and cottonPart = :cottonPart")
    Integer operEqual(@Param ("color")  String color, @Param("cottonPart")  int cottonPart);
    @Query ("select sum(quantity) from Socks  where color = :color and cottonPart < :cottonPart")
    Integer operLessThen(@Param ("color")  String color, @Param("cottonPart")  int cottonPart);
    @Query ("select sum(quantity) from Socks  where color = :color and cottonPart > :cottonPart")
    Integer operMoreThen(@Param ("color")  String color, @Param("cottonPart")  int cottonPart);

}
