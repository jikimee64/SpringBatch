package com.soap.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 기본 CRUD 메소드 정의된 클래스 상속 및 클래스 엔티티(테이블명) 설정
 */
public interface MzsendtranRepository extends JpaRepository<Mzsendtran, String> {

    /**
     * MZSENDTRAN 데이터 삭제
     * @Transactional : @Modifying 사용시 필수 설정
     * => @Modifying는 명시만할뿐 실사용을 위해선 @Transactional를 선언해야함
     * @Modifying : select 쿼리가 아님을 명시(SELECT 쿼리 아닐시 필수)
     * @Query : SQL 직접 작성
     */
    @Transactional
    @Modifying
    @Query(value="delete from Mzsendtran mz", nativeQuery = true)
    void deleteAllBySnInQuery();
}
