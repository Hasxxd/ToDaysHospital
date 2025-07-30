--tbl_newbbs 자료실 테이블 생성
create table tbl_newbbs(
 bbs_no number(38) primary key --게시물번호
 ,bbs_name varchar2(100) not null --작성자
 ,bbs_title varchar2(200) not null --제목
 ,bbs_pwd varchar2(20) not null --비밀번호
 ,bbs_cont varchar2(4000) not null --내용
 ,bbs_hit number(38) default 0 --조회수
 ,bbs_ref number(38) --글 그룹번호
 ,bbs_step number(38) -- 첫번째 답변글 1,두번째 답변글 2,즉
 --원본글과 답변글을 구분하는 번호값,몇번째 답변글인가를 알려줌
 ,bbs_level number(38) -- 답변글 정렬순서
 ,bbs_date date --글 등록날짜 
);

ALTER TABLE tbl_newbbs
ADD bbs_attached_file NUMBER(38); --첨부파일이 있는 경우만 7정숫값을 저장하기 위한 컬럼 추가

drop table tbl_newbbs;

select * from tbl_newbbs order by bbs_no desc;

delete from tbl_newbbs;

--newbbs_no_seq라는 시퀀스 생성
create sequence newbbs_no_seq
/* start with 1 -- 1부터 시작 옵션, 기본값이라서 생략가능
 * increment by 1 -- 1씩 증가 옵션, 기본값이라서 생략가능
 * nocycle --시퀀스 최대값 또는 최소값 도달시 다시 처음부터 반복하지 않음. 기본값 생략가능
 */
nocache; --임시 메모리 사용안함

--newbbs_no_seq 시퀀스 다음 번호값 확인
select newbbs_no_seq.nextval as "다음 시퀀스 번호값" from dual;

--업로드 되는 파일정보를 저장하는 종속테이블 tbl_newbbs_file 설계
create table tbl_newbbs_file(
 bbs_file_no number(38) primary key --번호
 ,bbs_no number(38) --FK(외래키)
 ,bbs_original_name VARCHAR2(255) --첨부된 원본파일명
 ,bbs_stored_name  VARCHAR2(255) --중복방지를 위해서 변경된 파일명
 ,bbs_file_path   VARCHAR2(1000) --첨부파일 경로
 ,bbs_file_size   number(38) --첨부된 파일 크기 
 ,upload_date   date default sysdate --파일첨부되는 등록날짜
 ,foreign key (bbs_no) references tbl_newbbs(bbs_no) on delete cascade --외래키 컬럼인 bbs_no가 주인테이블 tbl_newbbs의 기본키 컬럼 bbs_no
 --를 가리키고 있고 **ON DELETE CASCADE**는 부모 테이블(tbl_newsbbs)의 레코드가 삭제될 때,자식 테이블(tbl_newbbs_file)의 관련 레코드도 자동으로 삭제되도록 설정하는
 -- 외래 키 옵션 
);

drop table tbl_newbbs_file;

select * from tbl_newbbs_file order by bbs_file_no desc;

delete from tbl_newbbs_file;
commit;

--file_no_seq 시퀀스 생성
create sequence file_no_seq
nocache;

--file_no_seq 시퀀스 번호값 확인
select file_no_seq.nextval as "file_no_seq시퀀스번호값" from dual;
