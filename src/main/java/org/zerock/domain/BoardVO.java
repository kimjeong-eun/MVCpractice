package org.zerock.domain;

import java.util.Date;

import lombok.Data;


@Data  //setter getter toString equals
public class BoardVO {

	private Long bno ; 
	private String title;
	private String content;
	private String writer;
	private Date regidate;
	private Date updatedate;
	
	
}
