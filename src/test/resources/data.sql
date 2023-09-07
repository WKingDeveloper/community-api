INSERT INTO `Board` (`id`, `name`, `parentBoardId`, `indexNo`, `status`)
VALUES (1, '지식', NULL, 0, 1),
       (2, '이벤트', NULL, 1, 1),
       (3, '커뮤니티', NULL, 2, 1),
       (4, '팁', NULL, 1, 1),
       (5, '리뷰', NULL, 1, 1),
       (6, '행사', 1, 2, 1),
       (7, '광고', 1, 2, 1);

INSERT INTO `Post` (`id`, `title`, `content`, `boardId`, `likes`, `views`, `status`)
VALUES (1, '글 제목', '글 내용', 6, 0, 0, 1),
       (2, '자유 글 하나 추가', '홀롤롤ㄹ로', 7, 0, 0, 1);

INSERT INTO `User` (`id`, `email`, `password`, `authorityLevel`, `status`)
VALUES (1, 'user@wkd.com', 'user12!@', 'USER', 1),
       (2, 'admin', 'admin12!@', 'ADMIN', 1);
