package com.portfolio.postproject.board.repository;

import com.portfolio.postproject.board.entity.DiaryPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DiaryPostRepository extends JpaRepository<DiaryPost, Long> {

    //main 페이지 - 오름차순, 조회
    @Query("select dp from DiaryPost dp where dp.diaryUser.id = :paramId and dp.postContent like %:searchText% order by dp.id asc")
    List<DiaryPost> findAllByUserIdAndDateAscInMain(@Param("paramId") String paramId,
                                                              @Param("searchText") String searchText);

    //main 페이지 - 내림차순, 조회
    @Query("select dp from DiaryPost dp where dp.diaryUser.id = :paramId and dp.postContent like %:searchText% order by dp.id desc")
    List<DiaryPost> findAllByUserIdAndDateDescInMain(@Param("paramId") String paramId,
                                                               @Param("searchText") String searchText);

    //calendar 페이지 - 오름차순, 조회
    @Query("select dp from DiaryPost dp where dp.diaryUser.id = :paramId and dp.postDate between :startDate and :endDate and dp.postTitle like %:searchText% order by dp.id asc")
    Page<DiaryPost> findAllByUserIdAndDateAscInCalendar(@Param("paramId") String paramId,
                                                                  @Param("startDate") LocalDate startDate,
                                                                  @Param("endDate") LocalDate endDate,
                                                                  @Param("searchText") String searchText, Pageable pageable);

    //calendar 페이지 - 내림차순, 조회
    @Query("select dp from DiaryPost dp where dp.diaryUser.id = :paramId and dp.postDate between :startDate and :endDate and dp.postTitle like %:searchText% order by dp.id desc")
    Page<DiaryPost> findAllByUserIdAndDateDescInCalendar(@Param("paramId") String paramId,
                                                                  @Param("startDate") LocalDate startDate,
                                                                  @Param("endDate") LocalDate endDate,
                                                                  @Param("searchText") String searchText, Pageable pageable);
}
