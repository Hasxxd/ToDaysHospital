--boardMvc 게시판 테이블 생성
create table boardMvc(
 board_no number(38) primary key --게시물 번호
 ,board_name varchar2(100) not null --작성자
 ,board_title varchar2(200) not null --제목
 ,board_pwd varchar2(20) not null --비번
 ,board_cont varchar2(4000) not null --내용
 ,board_hit number(38) default 0--조회수
 ,board_ref number(38) --원본글과 답변글을 묶어주는 그룹번호역할
 ,board_step number(38) --원본글이면 0,첫번째 답변글 1. 원본글과
 --답변글을 구분하는 번호값,몇번째 답변글인가를 구분하는 번호값
 ,board_level number(38) --답변글 정렬 순서
 ,board_date date --등록날짜
);

select * from boardMvc order by board_no desc;
select * from boardMvc where board_no=28;
delete boardMVC where board_no = 117;

insert into boardMvc (board_no,board_name,board_title, board_pwd,board_cont,board_ref,board_step,board_level,board_date)
      values(boardMvc_no_seq.nextval,'글쓴이','글제목','aa','글내용',boardMvc_no_seq.nextval,0,0,sysdate)
      
select count(board_no) from boardMvc;

commit;

--board_no_seq 시퀀스 생성
create sequence boardMvc_no_seq
start with 1 --1부터 시작
increment by 1 --1씩 증가옵션
nocache;

drop sequence board_no_seq;

select boardMvc_no_seq.nextval from dual;
