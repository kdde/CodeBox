package com.green.nowon.domain.entity.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.nowon.domain.dto.board.reply.ReplySaveDTO;

@Repository
public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Long>{

	void save(ReplySaveDTO dto);

}
