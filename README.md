(과제 설명)
- JPA를 사용하여 게시판을 구현합니다.
- 게시판에는 게시글과 댓글이 있습니다.
    - 게시글: 제목과 본문이 있으며, 제목과 본문 모두 Text 만 지원합니다.
    - 댓글: 게시글에 댓글을 달 수 있으며, 대댓글은 고려하지 않습니다.
(API 요구사항)
1. 게시글 등록

   a. 단 건의 게시글을 등록 할 수 있어야 합니다.

2. 게시글 삭제

   a. 단 건의 게시글을 삭제 할 수 있어야 합니다.

    - 게시글의 삭제 방법은 soft delete 로 진행합니다.
    - 게시글 삭제시 댓글도 삭제가 되어야 합니다.
3. 게시글 수정

   a. 단 건의 게시글을 수정 할 수 있어야 합니다.

    - 삭제된 게시글은 수정 할 수 없습니다.
4. 게시글 목록 조회

   a. 등록된 게시글의 목록을 조회합니다.

    - 응답에는 본문이 포함되지 않습니다.

   b. 페이징이 가능해야 합니다.

    - 페이징의 방식은 offset 기반 페이징과 cursor 기반 페이징이 존재하는데 각 구현방식의 차이점을 학습하고, 비교, 선택하여 구현합니다.
    - 정렬 순서는, 최신 글이 우선순위가 가장 높습니다.
5. 댓글 등록

   a. 게시글에 댓글을 등록합니다.

6. 댓글 수정

   a. 게시글에 댓글을 수정합니다.

7. 댓글 삭제

   a. 게시글에 댓글을 삭제 합니다.

   b. 삭제는 soft delete 로 진행합니다.

8. 게시글 단 건 조회

   a. 게시글의 단 건을 조회합니다.

    - 게시글의 제목과 본문 모두 응답에 포함되어야 합니다.
    - 해당 게시 글에 등록된 댓글 리스트도 같이 응답에 포함되어야 합니다.
        - 댓글 중에 삭제된 댓글은 포함하지 않습니다.
        - 댓글리스트도 페이징이 필요하나, 해당 과제에서는 무시합니다.
- (수행기간)
    - 4일
- (결과물)
    - DB스키마와 소스코드
- (하위과제)
    - N + 1 문제가 무엇인지 학습하고, 해당 문제가 일어나고 있으면 개선합니다.
    - 조회에서 성능 개선을 하기위해서 DB 인덱스를 추가해 봅니다.
        - 문제가 발견될 경우 개선해보도록 합시다.