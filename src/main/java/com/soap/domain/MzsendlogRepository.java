package com.soap.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 기본 CRUD 메소드 정의된 클래스 상속 및 클래스 엔티티(테이블명) 설정
 */
public interface MzsendlogRepository extends JpaRepository<Mzsendlog, String> {
}
